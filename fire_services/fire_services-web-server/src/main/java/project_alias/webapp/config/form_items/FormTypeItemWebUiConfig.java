package project_alias.webapp.config.form_items;

import static java.lang.String.format;
import static project_alias.common.StandardActionsStyles.MASTER_CANCEL_ACTION_LONG_DESC;
import static project_alias.common.StandardActionsStyles.MASTER_CANCEL_ACTION_SHORT_DESC;
import static project_alias.common.StandardActionsStyles.MASTER_SAVE_ACTION_LONG_DESC;
import static project_alias.common.StandardActionsStyles.MASTER_SAVE_ACTION_SHORT_DESC;
import static project_alias.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

import project_alias.form_items.FormTypeItem;
import project_alias.common.LayoutComposer;
import project_alias.common.StandardActions;

import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.action.CentreConfigurationWebUiConfig.CentreConfigActions;
import ua.com.fielden.platform.web.centre.api.EntityCentreConfig;
import ua.com.fielden.platform.web.centre.api.actions.EntityActionConfig;
import ua.com.fielden.platform.web.centre.api.impl.EntityCentreBuilder;
import ua.com.fielden.platform.web.view.master.api.actions.MasterActions;
import ua.com.fielden.platform.web.view.master.api.impl.SimpleMasterBuilder;
import ua.com.fielden.platform.web.view.master.api.IMaster;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import project_alias.main.menu.form_items.MiFormTypeItem;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;

/**
 * {@link FormTypeItem} Web UI configuration.
 *
 * @author Project-alias team
 *
 */
public class FormTypeItemWebUiConfig {

    public final EntityCentre<FormTypeItem> centre;
    public final EntityMaster<FormTypeItem> master;

    public static FormTypeItemWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new FormTypeItemWebUiConfig(injector, builder);
    }

    private FormTypeItemWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link FormTypeItem}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<FormTypeItem> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkGridForCentre(2, 1);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(FormTypeItem.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(FormTypeItem.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(FormTypeItem.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(FormTypeItem.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(FormTypeItem.class, standardEditAction);

        final EntityCentreConfig<FormTypeItem> ecc = EntityCentreBuilder.centreFor(FormTypeItem.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
//                .addCrit("this").asMulti().autocompleter(FormTypeItem.class).also()
                .addCrit("title").asMulti().text().also()
                .addCrit("desc").asMulti().text()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
//                .addProp("this").order(1).asc().minWidth(150)
//                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", FormTypeItem.ENTITY_TITLE)).also()
                .addProp("title").minWidth(150).also()
                .addProp("desc").minWidth(300)

                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiFormTypeItem.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link FormTypeItem}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<FormTypeItem> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkGridForMasterFitWidth(2, 1);

        final IMaster<FormTypeItem> masterConfig = new SimpleMasterBuilder<FormTypeItem>().forEntity(FormTypeItem.class)
                .addProp("title").asSinglelineText().also()
                .addProp("desc").asMultilineText().also()
                .addAction(MasterActions.REFRESH).shortDesc(MASTER_CANCEL_ACTION_SHORT_DESC).longDesc(MASTER_CANCEL_ACTION_LONG_DESC)
                .addAction(MasterActions.SAVE).shortDesc(MASTER_SAVE_ACTION_SHORT_DESC).longDesc(MASTER_SAVE_ACTION_LONG_DESC)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 280, Unit.PX))
                .done();

        return new EntityMaster<>(FormTypeItem.class, masterConfig, injector);
    }
}