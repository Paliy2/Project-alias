package project_alias.webapp.config.equipments;

import static ua.com.fielden.platform.web.PrefDim.mkDim;
import static project_alias.common.StandardScrollingConfigs.standardEmbeddedScrollingConfig;
import static project_alias.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;
import static project_alias.common.LayoutComposer.CELL_LAYOUT;
import static project_alias.common.LayoutComposer.FLEXIBLE_LAYOUT_WITH_PADDING;
import static project_alias.common.LayoutComposer.FLEXIBLE_ROW;
import static project_alias.common.LayoutComposer.MARGIN;
import static project_alias.common.StandardActions.actionAddDesc;
import static project_alias.common.StandardActions.actionEditDesc;
import static java.lang.String.format;
import static ua.com.fielden.platform.dao.AbstractOpenCompoundMasterDao.enhanceEmbededCentreQuery;
import static ua.com.fielden.platform.entity_centre.review.DynamicQueryBuilder.createConditionProperty;

import java.util.Optional;

import com.google.inject.Injector;

import project_alias.equipments.EquipmentClass;
import project_alias.equipments.EquipmentType;
import project_alias.main.menu.equipments.MiEquipmentClass;
import project_alias.main.menu.equipments.MiEquipmentClassMaster_EquipmentType;
import project_alias.equipments.master.menu.actions.EquipmentClassMaster_OpenEquipmentType_MenuItem;
import project_alias.equipments.ui_actions.OpenEquipmentClassMasterAction;
import project_alias.equipments.ui_actions.producers.OpenEquipmentClassMasterActionProducer;
import project_alias.equipments.master.menu.actions.EquipmentClassMaster_OpenMain_MenuItem;
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
 * {@link EquipmentClass} Web UI configuration.
 *
 * @author Developers
 *
 */
public class EquipmentClassWebUiConfig {

    private final Injector injector;

    public final EntityCentre<EquipmentClass> centre;
    public final EntityMaster<EquipmentClass> main;
    public final EntityMaster<OpenEquipmentClassMasterAction> compoundMaster;
    public final EntityActionConfig editEquipmentClassAction; // should be used on embedded centres instead of a standard EDIT action
    public final EntityActionConfig newEquipmentClassWithMasterAction; // should be used on embedded centres instead of a standrad NEW action
    private final EntityActionConfig newEquipmentClassAction;

    public static EquipmentClassWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new EquipmentClassWebUiConfig(injector, builder);
    }

    private EquipmentClassWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        this.injector = injector;

        final PrefDim dims = mkDim(960, 640, Unit.PX);
        editEquipmentClassAction = Compound.openEdit(OpenEquipmentClassMasterAction.class, EquipmentClass.ENTITY_TITLE, actionEditDesc(EquipmentClass.ENTITY_TITLE), dims);
        newEquipmentClassWithMasterAction = Compound.openNewWithMaster(OpenEquipmentClassMasterAction.class, "add-circle-outline", EquipmentClass.ENTITY_TITLE, actionAddDesc(EquipmentClass.ENTITY_TITLE), dims);
        newEquipmentClassAction = Compound.openNew(OpenEquipmentClassMasterAction.class, "add-circle-outline", EquipmentClass.ENTITY_TITLE, actionAddDesc(EquipmentClass.ENTITY_TITLE), dims);
        builder.registerOpenMasterAction(EquipmentClass.class, editEquipmentClassAction);

        centre = createCentre(injector, builder, editEquipmentClassAction);
        builder.register(centre);
        
        main = createMain();
        builder.register(main);

        compoundMaster = CompoundMasterBuilder.<EquipmentClass, OpenEquipmentClassMasterAction>create(injector, builder)
            .forEntity(OpenEquipmentClassMasterAction.class)
            .withProducer(OpenEquipmentClassMasterActionProducer.class)
            .addMenuItem(EquipmentClassMaster_OpenMain_MenuItem.class)
                .icon("icons:picture-in-picture")
                .shortDesc(OpenEquipmentClassMasterAction.MAIN)
                .longDesc(EquipmentClass.ENTITY_TITLE + " main")
                .withView(main)
            .also()
            .addMenuItem(EquipmentClassMaster_OpenEquipmentType_MenuItem.class)
                .icon("icons:view-module")
                .shortDesc(OpenEquipmentClassMasterAction.EQUIPMENTTYPES)
                .longDesc(EquipmentClass.ENTITY_TITLE + " " + OpenEquipmentClassMasterAction.EQUIPMENTTYPES)
                .withView(createEquipmentTypeCentre())
            .done();
        builder.register(compoundMaster);
    }

    /**
     * Creates entity master for {@link EquipmentClass}.
     *
     * @return
     */
    private EntityMaster<EquipmentClass> createMain() {
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

    private EntityCentre<EquipmentType> createEquipmentTypeCentre() {
        final Class<EquipmentType> root = EquipmentType.class;
        final String layout = LayoutComposer.mkVarGridForCentre(2);

        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(EquipmentType.class);
        final EntityActionConfig standardNewAction = StandardActions.NEW_WITH_MASTER_ACTION.mkAction(EquipmentType.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(EquipmentType.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_EMBEDDED_CENTRE_ACTION.mkAction(EquipmentType.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();

        final EntityCentreConfig<EquipmentType> ecc = EntityCentreBuilder.centreFor(root)
                .runAutomatically()
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("this").asMulti().autocompleter(EquipmentType.class).also()
                .addCrit("active").asMulti().bool()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("this").order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", EquipmentType.ENTITY_TITLE)).also()
                .addProp("active").width(50).also()
                .addProp("desc").minWidth(200)
                .addPrimaryAction(standardEditAction)
                .setQueryEnhancer(EquipmentClassMaster_EquipmentTypeCentre_QueryEnhancer.class, context().withMasterEntity().build())
                .build();

        return new EntityCentre<>(MiEquipmentClassMaster_EquipmentType.class, ecc, injector);
    }

    private static class EquipmentClassMaster_EquipmentTypeCentre_QueryEnhancer implements IQueryEnhancer<EquipmentType> {
        @Override
        public ICompleted<EquipmentType> enhanceQuery(final IWhere0<EquipmentType> where, final Optional<CentreContext<EquipmentType, ?>> context) {
            return enhanceEmbededCentreQuery(where, createConditionProperty("equipmentClass"), context.get().getMasterEntity().getKey());
        }
    }
    
    /**
     * Creates entity centre for {@link EquipmentClass}.
     *
     * @param injector
     * @param editEquipmentClassAction
     * @return created entity centre
     */
    private EntityCentre<EquipmentClass> createCentre(final Injector injector, final IWebUiBuilder builder, final EntityActionConfig editEquipmentClassAction) {
        final String layout = LayoutComposer.mkGridForCentre(1, 2);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(EquipmentClass.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(EquipmentClass.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(EquipmentClass.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction(); 

        final EntityCentreConfig<EquipmentClass> ecc = EntityCentreBuilder.centreFor(EquipmentClass.class)
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("this").asMulti().autocompleter(EquipmentClass.class).also()
                .addCrit("active").asMulti().bool()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("this").order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", EquipmentClass.ENTITY_TITLE)).also()
                .addProp("active").minWidth(100).also()
                .addProp("desc").minWidth(300)
                .addPrimaryAction(editEquipmentClassAction)
                .build();

        return new EntityCentre<>(MiEquipmentClass.class, ecc, injector);
    }

}