package project_alias.webapp.config.equipments;

import static java.lang.String.format;
import static project_alias.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;
import static project_alias.common.LayoutComposer.*;
import static ua.com.fielden.platform.web.layout.api.impl.LayoutBuilder.cell;

import java.util.Optional;

import com.google.inject.Injector;

import project_alias.equipments.EquipmentClass;
import project_alias.equipments.EquipmentType;
import project_alias.equipments.producers.EquipmentTypeProducer;
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
import project_alias.main.menu.equipments.MiEquipmentType;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;

/**
 * {@link EquipmentType} Web UI configuration.
 *
 * @author Developers
 *
 */
public class EquipmentTypeWebUiConfig {

    public final EntityCentre<EquipmentType> centre;
    public final EntityMaster<EquipmentType> master;

    public static EquipmentTypeWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new EquipmentTypeWebUiConfig(injector, builder);
    }

    private EquipmentTypeWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link EquipmentType}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<EquipmentType> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(2, 1);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(EquipmentType.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(EquipmentType.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(EquipmentType.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(EquipmentType.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(EquipmentType.class, standardEditAction);

        final EntityCentreConfig<EquipmentType> ecc = EntityCentreBuilder.centreFor(EquipmentType.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("this").asMulti().autocompleter(EquipmentType.class).also()
                .addCrit("active").asMulti().bool().also()
                .addCrit("equipmentClass").asMulti().autocompleter(EquipmentClass.class)
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("this").order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", EquipmentType.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp("active").width(50).also()
                .addProp("equipmentClass").width(200).also()
                .addProp("desc").minWidth(200)
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiEquipmentType.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link EquipmentType}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<EquipmentType> createMaster(final Injector injector) {
        final String layout = cell(
                cell(cell().repeat(2).layoutForEach(CELL_LAYOUT).withGapBetweenCells(MARGIN))
                .cell(cell(CELL_LAYOUT))
                .cell(cell(CELL_LAYOUT), FLEXIBLE_ROW), FLEXIBLE_LAYOUT_WITH_PADDING).toString(); 

        final IMaster<EquipmentType> masterConfig = new SimpleMasterBuilder<EquipmentType>().forEntity(EquipmentType.class)
                .addProp("title").asSinglelineText().also()
                .addProp("active").asCheckbox().also()
                .addProp("equipmentClass").asAutocompleter().also()
                .addProp("desc").asMultilineText().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(EquipmentType.class, EquipmentTypeProducer.class, masterConfig, injector);
    }
}