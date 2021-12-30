package project_alias.webapp.config.vehicles;

import static ua.com.fielden.platform.web.PrefDim.mkDim;
import static project_alias.common.StandardScrollingConfigs.standardEmbeddedScrollingConfig;
import static project_alias.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;
import static project_alias.common.LayoutComposer.CELL_LAYOUT;
import static project_alias.common.LayoutComposer.FLEXIBLE_LAYOUT_WITH_PADDING;
import static project_alias.common.LayoutComposer.FLEXIBLE_ROW;
import static project_alias.common.StandardActions.actionAddDesc;
import static project_alias.common.StandardActions.actionEditDesc;
import static project_alias.common.StandardActionsStyles.MASTER_CANCEL_ACTION_LONG_DESC;
import static project_alias.common.StandardActionsStyles.MASTER_CANCEL_ACTION_SHORT_DESC;
import static project_alias.common.StandardActionsStyles.MASTER_SAVE_ACTION_LONG_DESC;
import static project_alias.common.StandardActionsStyles.MASTER_SAVE_ACTION_SHORT_DESC;
import static java.lang.String.format;
import static ua.com.fielden.platform.dao.AbstractOpenCompoundMasterDao.enhanceEmbededCentreQuery;
import static ua.com.fielden.platform.entity_centre.review.DynamicQueryBuilder.createConditionProperty;

import java.util.Optional;

import com.google.inject.Injector;

import project_alias.vehicles.VehicleType;
import project_alias.vehicles.Vehicle;
import project_alias.main.menu.vehicles.MiVehicleType;
import project_alias.main.menu.vehicles.MiVehicleTypeMaster_Vehicle;
import project_alias.vehicles.master.menu.actions.VehicleTypeMaster_OpenVehicle_MenuItem;
import project_alias.vehicles.ui_actions.OpenVehicleTypeMasterAction;
import project_alias.vehicles.ui_actions.producers.OpenVehicleTypeMasterActionProducer;
import project_alias.vehicles.master.menu.actions.VehicleTypeMaster_OpenMain_MenuItem;
import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.centre.api.EntityCentreConfig;
import ua.com.fielden.platform.web.centre.api.impl.EntityCentreBuilder;
import ua.com.fielden.platform.web.centre.api.actions.EntityActionConfig;
import ua.com.fielden.platform.web.view.master.api.actions.MasterActions;
import ua.com.fielden.platform.web.view.master.api.impl.SimpleMasterBuilder;
import ua.com.fielden.platform.web.view.master.api.compound.Compound;
import ua.com.fielden.platform.web.view.master.api.compound.impl.CompoundMasterBuilder;
import ua.com.fielden.platform.web.view.master.api.IMaster;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import ua.com.fielden.platform.web.PrefDim;
import ua.com.fielden.platform.web.PrefDim.Unit;
import project_alias.common.LayoutComposer;
import project_alias.common.StandardActions;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.action.CentreConfigurationWebUiConfig.CentreConfigActions;
import static ua.com.fielden.platform.web.centre.api.context.impl.EntityCentreContextSelector.context;
import static ua.com.fielden.platform.web.layout.api.impl.LayoutBuilder.cell;

import ua.com.fielden.platform.web.centre.CentreContext;
import ua.com.fielden.platform.web.centre.IQueryEnhancer;
import ua.com.fielden.platform.entity.query.fluent.EntityQueryProgressiveInterfaces.ICompleted;
import ua.com.fielden.platform.entity.query.fluent.EntityQueryProgressiveInterfaces.IWhere0;
import ua.com.fielden.platform.web.view.master.EntityMaster;
/**
 * {@link VehicleType} Web UI configuration.
 *
 * @author Developers
 *
 */
public class VehicleTypeWebUiConfig {

    private final Injector injector;

    public final EntityCentre<VehicleType> centre;
    public final EntityMaster<VehicleType> main;
    public final EntityMaster<OpenVehicleTypeMasterAction> compoundMaster;
    public final EntityActionConfig editVehicleTypeAction; // should be used on embedded centres instead of a standard EDIT action
    public final EntityActionConfig newVehicleTypeWithMasterAction; // should be used on embedded centres instead of a standrad NEW action
    private final EntityActionConfig newVehicleTypeAction;

    public static VehicleTypeWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new VehicleTypeWebUiConfig(injector, builder);
    }

    private VehicleTypeWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        this.injector = injector;

        final PrefDim dims = mkDim(960, 640, Unit.PX);
        editVehicleTypeAction = Compound.openEdit(OpenVehicleTypeMasterAction.class, VehicleType.ENTITY_TITLE, actionEditDesc(VehicleType.ENTITY_TITLE), dims);
        newVehicleTypeWithMasterAction = Compound.openNewWithMaster(OpenVehicleTypeMasterAction.class, "add-circle-outline", VehicleType.ENTITY_TITLE, actionAddDesc(VehicleType.ENTITY_TITLE), dims);
        newVehicleTypeAction = Compound.openNew(OpenVehicleTypeMasterAction.class, "add-circle-outline", VehicleType.ENTITY_TITLE, actionAddDesc(VehicleType.ENTITY_TITLE), dims);
        builder.registerOpenMasterAction(VehicleType.class, editVehicleTypeAction);

        centre = createCentre(injector, builder, editVehicleTypeAction);
        builder.register(centre);
        main = createMain();
        builder.register(main);

        compoundMaster = CompoundMasterBuilder.<VehicleType, OpenVehicleTypeMasterAction>create(injector, builder)
                .forEntity(OpenVehicleTypeMasterAction.class)
                .withProducer(OpenVehicleTypeMasterActionProducer.class)
                .addMenuItem(VehicleTypeMaster_OpenMain_MenuItem.class)
                .icon("icons:picture-in-picture")
                .shortDesc(OpenVehicleTypeMasterAction.MAIN)
                .longDesc(VehicleType.ENTITY_TITLE + " main")
                .withView(main)
                .also()
                .addMenuItem(VehicleTypeMaster_OpenVehicle_MenuItem.class)
                .icon("icons:view-module")
                .shortDesc(OpenVehicleTypeMasterAction.VEHICLES)
                .longDesc(VehicleType.ENTITY_TITLE + " " + OpenVehicleTypeMasterAction.VEHICLES)
                .withView(createVehicleCentre())
                .done();
        builder.register(compoundMaster);
    }

    /**
     * Creates entity master for {@link VehicleType}.
     *
     * @return
     */
    private EntityMaster<VehicleType> createMain() {
        final String layout = cell(
                cell(cell(CELL_LAYOUT)).
                cell(cell(CELL_LAYOUT)).
                cell(cell(CELL_LAYOUT), FLEXIBLE_ROW), FLEXIBLE_LAYOUT_WITH_PADDING).toString();

        final IMaster<VehicleType> masterConfig = new SimpleMasterBuilder<VehicleType>().forEntity(VehicleType.class)
                .addProp("title").asSinglelineText().also()
                .addProp("active").asCheckbox().also()
                .addProp("desc").asMultilineText().also()
                .addAction(MasterActions.REFRESH).shortDesc(MASTER_CANCEL_ACTION_SHORT_DESC).longDesc(MASTER_CANCEL_ACTION_LONG_DESC)
                .addAction(MasterActions.SAVE).shortDesc(MASTER_SAVE_ACTION_SHORT_DESC).longDesc(MASTER_SAVE_ACTION_LONG_DESC)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 380, Unit.PX))
                .done();

        return new EntityMaster<VehicleType>(VehicleType.class, masterConfig, injector);
    }

    private EntityCentre<Vehicle> createVehicleCentre() {
        final Class<Vehicle> root = Vehicle.class;
        final String layout = LayoutComposer.mkVarGridForCentre(2, 2, 1);

        final EntityActionConfig standardNewAction = StandardActions.NEW_WITH_MASTER_ACTION.mkAction(Vehicle.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Vehicle.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_EMBEDDED_CENTRE_ACTION.mkAction(Vehicle.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();

        final EntityCentreConfig<Vehicle> ecc = EntityCentreBuilder.centreFor(root)
                .runAutomatically()
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("this").asMulti().autocompleter(Vehicle.class).also()
                .addCrit("active").asMulti().bool().also()
                .addCrit("model").asMulti().text().also()
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
                .addProp("assignedDriver").minWidth(100).also()
                .addProp("desc").minWidth(100)
                .addPrimaryAction(editVehicleTypeAction)
                .setQueryEnhancer(VehicleTypeMaster_VehicleCentre_QueryEnhancer.class, context().withMasterEntity().build())
                .build();

        return new EntityCentre<>(MiVehicleTypeMaster_Vehicle.class, ecc, injector);
    }

    private static class VehicleTypeMaster_VehicleCentre_QueryEnhancer implements IQueryEnhancer<Vehicle> {
        @Override
        public ICompleted<Vehicle> enhanceQuery(final IWhere0<Vehicle> where, final Optional<CentreContext<Vehicle, ?>> context) {
            return enhanceEmbededCentreQuery(where, createConditionProperty("vehicleType"), context.get().getMasterEntity().getKey());
        }
    }

    /**
     * Creates entity centre for {@link VehicleType}.
     *
     * @param injector
     * @param editVehicleTypeAction 
     * @return created entity centre
     */
    private EntityCentre<VehicleType> createCentre(final Injector injector, final IWebUiBuilder builder, final EntityActionConfig editVehicleTypeAction) {
        final String layout = LayoutComposer.mkGridForCentre(1, 3);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(VehicleType.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(VehicleType.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(VehicleType.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();

        final EntityCentreConfig<VehicleType> ecc = EntityCentreBuilder.centreFor(VehicleType.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("this").asMulti().autocompleter(VehicleType.class).also()
                .addCrit("desc").asMulti().text().also()
                .addCrit("active").asMulti().bool()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("this").order(1).asc().width(75)
                .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", VehicleType.ENTITY_TITLE)).also()
                .addProp("desc").minWidth(100).also()
                .addProp("active").width(25)
                .addPrimaryAction(editVehicleTypeAction)
                .build();

        return new EntityCentre<>(MiVehicleType.class, ecc, injector);
    }
}