package project_alias.webapp.config.equipments;

import static java.lang.String.format;
import static project_alias.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;
import static project_alias.common.LayoutComposer.*;
import static ua.com.fielden.platform.web.layout.api.impl.LayoutBuilder.cell;

import java.util.Optional;

import com.google.inject.Injector;

import project_alias.equipments.Equipment;
import project_alias.equipments.EquipmentType;
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
import project_alias.main.menu.equipments.MiEquipment;
import project_alias.vehicles.Vehicle;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;

/**
 * {@link Equipment} Web UI configuration.
 *
 * @author Developers
 *
 */
public class EquipmentWebUiConfig {

    public final EntityCentre<Equipment> centre;
    public final EntityMaster<Equipment> master;

    public static EquipmentWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new EquipmentWebUiConfig(injector, builder);
    }

    private EquipmentWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link Equipment}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<Equipment> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(3, 2);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(Equipment.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Equipment.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(Equipment.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(Equipment.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(Equipment.class, standardEditAction);

        final EntityCentreConfig<Equipment> ecc = EntityCentreBuilder.centreFor(Equipment.class)
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("this").asMulti().autocompleter(Equipment.class).also()
                .addCrit("active").asMulti().bool().also()
                .addCrit("title").asMulti().text().also() 
                .addCrit("equipmentType").asMulti().autocompleter(EquipmentType.class).also()
                .addCrit("vehicle").asMulti().autocompleter(Vehicle.class)
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("this").order(1).asc().minWidth(50)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Equipment.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp("title").minWidth(50).also()
                .addProp("active").width(50).also()
                .addProp("equipmentType").minWidth(50).also()
                .addProp("vehicle").minWidth(50).also()
                .addProp("desc").minWidth(150)
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiEquipment.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link Equipment}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<Equipment> createMaster(final Injector injector) {
        final String layout = cell(
                cell(cell().repeat(2).layoutForEach(CELL_LAYOUT).withGapBetweenCells(MARGIN))
                .cell(cell().repeat(2).layoutForEach(CELL_LAYOUT).withGapBetweenCells(MARGIN))
                .cell(cell(CELL_LAYOUT), FLEXIBLE_ROW), FLEXIBLE_LAYOUT_WITH_PADDING).toString(); 

        final IMaster<Equipment> masterConfig = new SimpleMasterBuilder<Equipment>().forEntity(Equipment.class)
                .addProp("title").asSinglelineText().also()
                .addProp("active").asCheckbox().also()
                .addProp("equipmentType").asAutocompleter().also()
                .addProp("vehicle").asAutocompleter().also()
                .addProp("desc").asMultilineText().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(Equipment.class, masterConfig, injector);
    }
}