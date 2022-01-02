package project_alias.webapp.config.persons;

import static java.lang.String.format;
import static project_alias.common.StandardActionsStyles.MASTER_CANCEL_ACTION_LONG_DESC;
import static project_alias.common.StandardActionsStyles.MASTER_CANCEL_ACTION_SHORT_DESC;
import static project_alias.common.StandardActionsStyles.MASTER_SAVE_ACTION_LONG_DESC;
import static project_alias.common.StandardActionsStyles.MASTER_SAVE_ACTION_SHORT_DESC;
import static project_alias.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

import project_alias.persons.Person;
import project_alias.persons.PersonRole;
import project_alias.roles.Role;
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
import project_alias.main.menu.persons.MiPersonRole;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;

/**
 * {@link PersonRole} Web UI configuration.
 *
 * @author Project-alias Team
 *
 */
public class PersonRoleWebUiConfig {

    public final EntityCentre<PersonRole> centre;
    public final EntityMaster<PersonRole> master;

    public static PersonRoleWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new PersonRoleWebUiConfig(injector, builder);
    }

    private PersonRoleWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link PersonRole}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<PersonRole> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkGridForCentre(2, 2);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(PersonRole.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(PersonRole.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(PersonRole.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(PersonRole.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(PersonRole.class, standardEditAction);

        final EntityCentreConfig<PersonRole> ecc = EntityCentreBuilder.centreFor(PersonRole.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("this").asMulti().autocompleter(PersonRole.class).also()
                .addCrit("person").asMulti().autocompleter(Person.class).also()
                .addCrit("assignedRole").asMulti().autocompleter(Role.class).also()
                .addCrit("assignedDate").asRange().dateTime()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("this").order(1).asc().width(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", PersonRole.ENTITY_TITLE)).also()
                .addProp("person").minWidth(100)
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiPersonRole.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link PersonRole}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<PersonRole> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkGridForMasterFitWidth(3, 1);

        final IMaster<PersonRole> masterConfig = new SimpleMasterBuilder<PersonRole>().forEntity(PersonRole.class)
                .addProp("person").asAutocompleter().also()
                .addProp("assignedRole").asAutocompleter().also()
                .addProp("assignedDate").asDateTimePicker().also()
                .addAction(MasterActions.REFRESH).shortDesc(MASTER_CANCEL_ACTION_SHORT_DESC).longDesc(MASTER_CANCEL_ACTION_LONG_DESC)
                .addAction(MasterActions.SAVE).shortDesc(MASTER_SAVE_ACTION_SHORT_DESC).longDesc(MASTER_SAVE_ACTION_LONG_DESC)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(PersonRole.class, masterConfig, injector);
    }
}