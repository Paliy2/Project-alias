package project_alias.webapp.config.vehicles;

import static java.lang.String.format;
import static project_alias.common.LayoutComposer.CELL_LAYOUT;
import static project_alias.common.LayoutComposer.FLEXIBLE_LAYOUT_WITH_PADDING;
import static project_alias.common.LayoutComposer.FLEXIBLE_ROW;
import static project_alias.common.StandardActionsStyles.MASTER_CANCEL_ACTION_LONG_DESC;
import static project_alias.common.StandardActionsStyles.MASTER_CANCEL_ACTION_SHORT_DESC;
import static project_alias.common.StandardActionsStyles.MASTER_SAVE_ACTION_LONG_DESC;
import static project_alias.common.StandardActionsStyles.MASTER_SAVE_ACTION_SHORT_DESC;
import static project_alias.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

import project_alias.vehicles.VehicleType;
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
import project_alias.main.menu.vehicles.MiVehicleType;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import static ua.com.fielden.platform.web.layout.api.impl.LayoutBuilder.cell;

import ua.com.fielden.platform.web.PrefDim.Unit;

/**
 * {@link VehicleType} Web UI configuration.
 *
 * @author Developers
 *
 */
public class VehicleTypeWebUiConfig {

    public final EntityCentre<VehicleType> centre;
    public final EntityMaster<VehicleType> master;

    public static VehicleTypeWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new VehicleTypeWebUiConfig(injector, builder);
    }

    private VehicleTypeWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link VehicleType}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<VehicleType> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkGridForCentre(1, 3);
    	
        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(VehicleType.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(VehicleType.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(VehicleType.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(VehicleType.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(VehicleType.class, standardEditAction);

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
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiVehicleType.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link VehicleType}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<VehicleType> createMaster(final Injector injector) {
        // final String layout = LayoutComposer.mkGridForMasterFitWidth(1, 2);
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

        return new EntityMaster<>(VehicleType.class, masterConfig, injector);
    }
}