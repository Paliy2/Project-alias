package project_alias.webapp.config.vehicles;

import static java.lang.String.format;
import static project_alias.common.StandardActionsStyles.MASTER_CANCEL_ACTION_LONG_DESC;
import static project_alias.common.StandardActionsStyles.MASTER_CANCEL_ACTION_SHORT_DESC;
import static project_alias.common.StandardActionsStyles.MASTER_SAVE_ACTION_LONG_DESC;
import static project_alias.common.StandardActionsStyles.MASTER_SAVE_ACTION_SHORT_DESC;
import static project_alias.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;
import static ua.com.fielden.platform.web.PrefDim.mkDim;

import java.util.Optional;

import com.google.inject.Injector;

import project_alias.common.LayoutComposer;
import project_alias.common.StandardActions;
import project_alias.main.menu.vehicles.MiVehicle;
import project_alias.vehicles.Vehicle;
import project_alias.vehicles.VehicleType;
import project_alias.vehicles.producers.VehicleProducer;
import ua.com.fielden.platform.web.PrefDim.Unit;
import ua.com.fielden.platform.web.action.CentreConfigurationWebUiConfig.CentreConfigActions;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.centre.api.EntityCentreConfig;
import ua.com.fielden.platform.web.centre.api.actions.EntityActionConfig;
import ua.com.fielden.platform.web.centre.api.impl.EntityCentreBuilder;
import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import ua.com.fielden.platform.web.view.master.api.IMaster;
import ua.com.fielden.platform.web.view.master.api.actions.MasterActions;
import ua.com.fielden.platform.web.view.master.api.impl.SimpleMasterBuilder;

/**
 * {@link Vehicle} Web UI configuration.
 *
 * @author Developers
 *
 */
public class VehicleWebUiConfig {

    public final EntityCentre<Vehicle> centre;
    public final EntityMaster<Vehicle> master;

    public static VehicleWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new VehicleWebUiConfig(injector, builder);
    }

    private VehicleWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link Vehicle}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<Vehicle> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkGridForCentre(3, 2);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(Vehicle.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Vehicle.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(Vehicle.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(Vehicle.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(Vehicle.class, standardEditAction);

        final EntityCentreConfig<Vehicle> ecc = EntityCentreBuilder.centreFor(Vehicle.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("this").asMulti().autocompleter(Vehicle.class).also()
                .addCrit("active").asMulti().bool().also()
                .addCrit("model").asMulti().text().also()
                .addCrit("vehicleType").asMulti().autocompleter(VehicleType.class).also()
                .addCrit("assignedDriver").asMulti().text().also()
                .addCrit("desc").asMulti().text()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("this").order(1).asc().width(130)
                .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Vehicle.ENTITY_TITLE)).also()
                .addProp("active").width(70).also()
                .addProp("model").minWidth(100).also()
                .addProp("vehicleType").minWidth(100).also()
                .addProp("assignedDriver").minWidth(100).also()
                .addProp("desc").minWidth(100)
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiVehicle.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link Vehicle}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<Vehicle> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkGridForMasterFitWidth(3, 2);

        final IMaster<Vehicle> masterConfig = new SimpleMasterBuilder<Vehicle>().forEntity(Vehicle.class)
                .addProp("number").asSinglelineText().also()
                .addProp("active").asCheckbox().also()
                .addProp("model").asMultilineText().also()
                .addProp("vehicleType").asAutocompleter().also()
                .addProp("assignedDriver").asMultilineText().also()
                .addProp("desc").asMultilineText().also()
                .addAction(MasterActions.REFRESH).shortDesc(MASTER_CANCEL_ACTION_SHORT_DESC).longDesc(MASTER_CANCEL_ACTION_LONG_DESC)
                .addAction(MasterActions.SAVE).shortDesc(MASTER_SAVE_ACTION_SHORT_DESC).longDesc(MASTER_SAVE_ACTION_LONG_DESC)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 440, Unit.PX))
                .done();

        return new EntityMaster<>(Vehicle.class, VehicleProducer.class, masterConfig, injector);
    }
}