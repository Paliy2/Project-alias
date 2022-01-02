package project_alias.webapp.config.forms;

import static java.lang.String.format;
import static project_alias.webui.master.locator.LocatorFactory.mkLocator;
import project_alias.forms.FormClassLocator;
import project_alias.forms.FormType;
import project_alias.forms.Status;
import project_alias.persons.Person;

import static project_alias.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

import project_alias.forms.FormClass;
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
import project_alias.main.menu.forms.MiFormClass;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;

/**
 * {@link FormClass} Web UI configuration.
 *
 * @author Project-alias teams
 *
 */
public class FormClassWebUiConfig {

    public final EntityCentre<FormClass> centre;
    public final EntityMaster<FormClass> master;

    public static FormClassWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new FormClassWebUiConfig(injector, builder);
    }

    private FormClassWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link FormClass}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<FormClass> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkGridForCentre(2, 2);

        final EntityActionConfig locator = mkLocator(builder, injector, FormClassLocator.class, "formClass");
        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(FormClass.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(FormClass.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(FormClass.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(FormClass.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(FormClass.class, standardEditAction);

        final EntityCentreConfig<FormClass> ecc = EntityCentreBuilder.centreFor(FormClass.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction).also()
                .addFrontAction(locator)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(locator).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("this").asMulti().autocompleter(FormClass.class).also()
                .addCrit("status").asMulti().autocompleter(Status.class).also()
//                .addCrit("formType").asMulti().autocompleter(FormType.class).also()
                .addCrit("person").asMulti().autocompleter(Person.class).also()
                .addCrit("date").asRange().dateTime()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("this").order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", FormClass.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp("status").minWidth(100).also()
                .addProp("date").width(150).also()
//                .addProp("formType").minWidth(150).also()
                .addProp("person").minWidth(150)
                //.addProp("prop").minWidth(100).withActionSupplier(builder.getOpenMasterAction(Entity.class)).also()
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiFormClass.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link FormClass}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<FormClass> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkVarGridForMasterFitWidth(2, 1);

        final IMaster<FormClass> masterConfig = new SimpleMasterBuilder<FormClass>().forEntity(FormClass.class)
                .addProp("person").asAutocompleter().also()
                .addProp("status").asAutocompleter().also()
                .addProp("date").asDateTimePicker().also()
//                .addProp("formType").asAutocompleter().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(FormClass.class, masterConfig, injector);
    }
}