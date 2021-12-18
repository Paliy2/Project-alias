package project_alias.webapp.config.equipments;

import static java.lang.String.format;
import static project_alias.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;
import static project_alias.common.LayoutComposer.*;
import static ua.com.fielden.platform.web.layout.api.impl.LayoutBuilder.cell;

import java.util.Optional;

import com.google.inject.Injector;

import project_alias.equipments.EquipmentClass;
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
import project_alias.main.menu.equipments.MiEquipmentClass;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;

/**
 * {@link EquipmentClass} Web UI configuration.
 *
 * @author Developers
 *
 */
public class EquipmentClassWebUiConfig {

    public final EntityCentre<EquipmentClass> centre;
    public final EntityMaster<EquipmentClass> master;

    public static EquipmentClassWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new EquipmentClassWebUiConfig(injector, builder);
    }

    private EquipmentClassWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link EquipmentClass}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<EquipmentClass> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkGridForCentre(1, 2);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(EquipmentClass.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(EquipmentClass.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(EquipmentClass.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(EquipmentClass.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(EquipmentClass.class, standardEditAction);

        final EntityCentreConfig<EquipmentClass> ecc = EntityCentreBuilder.centreFor(EquipmentClass.class)
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("this").asMulti().autocompleter(EquipmentClass.class).also()
                .addCrit("active").asMulti().bool().also()
                .addCrit("desc").asMulti().text()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("this").order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", EquipmentClass.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp("active").minWidth(100).also()
                .addProp("desc").minWidth(300)
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiEquipmentClass.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link EquipmentClass}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<EquipmentClass> createMaster(final Injector injector) {
    	final String layout = cell(
                cell(cell().repeat(2).layoutForEach(CELL_LAYOUT).withGapBetweenCells(MARGIN))
               .cell(cell(CELL_LAYOUT), FLEXIBLE_ROW), FLEXIBLE_LAYOUT_WITH_PADDING).toString();

        final IMaster<EquipmentClass> masterConfig = new SimpleMasterBuilder<EquipmentClass>().forEntity(EquipmentClass.class)
        		.addProp("title").asSinglelineText().also()
                .addProp("active").asCheckbox().also()
                .addProp("desc").asMultilineText().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 280, Unit.PX))
                .done();

        return new EntityMaster<>(EquipmentClass.class, masterConfig, injector);
    }
}