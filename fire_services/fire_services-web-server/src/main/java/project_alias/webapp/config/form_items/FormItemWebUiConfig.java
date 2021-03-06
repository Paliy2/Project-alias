package project_alias.webapp.config.form_items;

import static java.lang.String.format;
import static project_alias.webui.master.locator.LocatorFactory.mkLocator;
import project_alias.form_items.FormItemLocator;
import project_alias.form_items.FormTypeItem;

import static project_alias.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

import project_alias.form_items.FormItem;
import project_alias.common.LayoutComposer;
import project_alias.common.StandardActions;
import project_alias.equipments.Equipment;
import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.action.CentreConfigurationWebUiConfig.CentreConfigActions;
import ua.com.fielden.platform.web.centre.api.EntityCentreConfig;
import ua.com.fielden.platform.web.centre.api.actions.EntityActionConfig;
import ua.com.fielden.platform.web.centre.api.impl.EntityCentreBuilder;
import ua.com.fielden.platform.web.view.master.api.actions.MasterActions;
import ua.com.fielden.platform.web.view.master.api.impl.SimpleMasterBuilder;
import ua.com.fielden.platform.web.view.master.api.IMaster;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import project_alias.main.menu.form_items.MiFormItem;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;

/**
 * {@link FormItem} Web UI configuration.
 *
 * @author Developers
 *
 */
public class FormItemWebUiConfig {

    public final EntityCentre<FormItem> centre;
    public final EntityMaster<FormItem> master;

    public static FormItemWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new FormItemWebUiConfig(injector, builder);
    }

    private FormItemWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link FormItem}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<FormItem> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(2, 1);

        final EntityActionConfig locator = mkLocator(builder, injector, FormItemLocator.class, "formItem");
        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(FormItem.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(FormItem.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(FormItem.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(FormItem.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(FormItem.class, standardEditAction);

        final EntityCentreConfig<FormItem> ecc = EntityCentreBuilder.centreFor(FormItem.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction).also()
                .addFrontAction(locator)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(locator).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("this").asMulti().autocompleter(FormItem.class).also()
                .addCrit("formTypeItem").asMulti().autocompleter(FormTypeItem.class).also()
                .addCrit("accepted").asMulti().bool()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("this").order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", FormItem.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp("accepted").minWidth(100).also()
                .addProp("formTypeItem").minWidth(100)
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiFormItem.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link FormItem}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<FormItem> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkGridForMasterFitWidth(2, 1);

        final IMaster<FormItem> masterConfig = new SimpleMasterBuilder<FormItem>().forEntity(FormItem.class)
//                .addProp("this").asAutocompleter().also()
                .addProp("formTypeItem").asAutocompleter().also()
                .addProp("accepted").asCheckbox().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(FormItem.class, masterConfig, injector);
    }
}