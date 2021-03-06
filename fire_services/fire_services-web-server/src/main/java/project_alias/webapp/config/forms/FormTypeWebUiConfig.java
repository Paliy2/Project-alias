package project_alias.webapp.config.forms;

import ua.com.fielden.platform.web.minijs.JsCode;
import static ua.com.fielden.platform.web.centre.api.context.impl.EntityCentreContextSelector.context;
import static ua.com.fielden.platform.web.centre.api.actions.impl.EntityActionBuilder.action;
import static java.lang.String.format;
import static project_alias.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

import project_alias.forms.FormType;
import project_alias.forms.FormClass;
import project_alias.forms.actions.FormTypeBatchUpdateForAssetClassAction;
import project_alias.forms.actions.producers.FormTypeBatchUpdateForAssetClassActionProducer;
import project_alias.forms.producers.FormTypeProducer;
import project_alias.common.LayoutComposer;
import project_alias.common.StandardActions;
import project_alias.common.StandardActionsStyles;
import static project_alias.common.StandardActionsStyles.MASTER_CANCEL_ACTION_LONG_DESC;
import static project_alias.common.StandardActionsStyles.MASTER_CANCEL_ACTION_SHORT_DESC;
import static project_alias.common.StandardActionsStyles.MASTER_SAVE_ACTION_LONG_DESC;
import static project_alias.common.StandardActionsStyles.MASTER_SAVE_ACTION_SHORT_DESC;
import project_alias.form_items.FormTypeItem;
import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.action.CentreConfigurationWebUiConfig.CentreConfigActions;
import ua.com.fielden.platform.web.centre.api.EntityCentreConfig;
import ua.com.fielden.platform.web.centre.api.actions.EntityActionConfig;
import ua.com.fielden.platform.web.centre.api.impl.EntityCentreBuilder;
import ua.com.fielden.platform.web.view.master.api.actions.MasterActions;
import ua.com.fielden.platform.web.view.master.api.impl.SimpleMasterBuilder;
import ua.com.fielden.platform.web.view.master.api.IMaster;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import project_alias.main.menu.forms.MiFormType;
import project_alias.roles.Role;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;

/**
 * {@link FormType} Web UI configuration.
 *
 * @author Developers
 *
 */
public class FormTypeWebUiConfig {

    public final EntityCentre<FormType> centre;
    public final EntityMaster<FormType> master;

    public static FormTypeWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new FormTypeWebUiConfig(injector, builder);
    }

    private FormTypeWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
        
        builder.register(createAssetTypeBatchUpdateForAssetClassActionMaster(injector));
    }

    /**
     * Creates entity centre for {@link FormType}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<FormType> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(2, 2);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(FormType.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(FormType.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(FormType.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(FormType.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(FormType.class, standardEditAction);
        
        final EntityActionConfig topActionToBatchUpdateFormClasses = action(FormTypeBatchUpdateForAssetClassAction.class)
                .withContext(context().withSelectedEntities().build())
                .postActionSuccess(() -> new JsCode("self.$.egi.clearPageSelection()"))
                .icon("icons:check-circle")
                .withStyle(StandardActionsStyles.CUSTOM_ACTION_COLOUR)
                .shortDesc("Batch update for From Types")
                .longDesc("Batch update property from class for selected types")
                .build();
        
        final EntityCentreConfig<FormType> ecc = EntityCentreBuilder.centreFor(FormType.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction).also()
                .addTopAction(topActionToBatchUpdateFormClasses)
                .addCrit("title").asMulti().text().also()
                .addCrit("desc").asMulti().text().also()
//                .addCrit("this").asMulti().autocompleter(FormType.class).also()
                .addCrit("formClass").asMulti().autocompleter(FormClass.class).also()

//                .addCrit("formTypeItems").asMulti().autocompleter(FormTypeItem.class).also()
                .addCrit("assignedRole").asMulti().autocompleter(Role.class)

                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("this").order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", FormType.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp("formClass").minWidth(100).also()
                .addProp("title").minWidth(50).also()
//                .addProp("formTypeItems").minWidth(100).also()
                .addProp("assignedRole").minWidth(100).also()
                .addProp("desc").minWidth(150)

                //.addProp("prop").minWidth(100).withActionSupplier(builder.getOpenMasterAction(Entity.class)).also()
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiFormType.class, ecc, injector);
    }

    /**
     * Creates entity master for {@link FormType}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<FormType> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkVarGridForMasterFitWidth(2, 1, 1);

        final IMaster<FormType> masterConfig = new SimpleMasterBuilder<FormType>().forEntity(FormType.class)
                .addProp("title").asSinglelineText().also()
                .addProp("assignedRole").asAutocompleter().also()
                .addProp("formClass").asAutocompleter().also()
                .addProp("desc").asSinglelineText().also()
//                .addProp("formTypeItems").asAutocompleter().also()

                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(FormType.class, FormTypeProducer.class, masterConfig, injector);
    }
    
    private EntityMaster<FormTypeBatchUpdateForAssetClassAction> createAssetTypeBatchUpdateForAssetClassActionMaster(final Injector injector) {
        final String layout = LayoutComposer.mkGridForMasterFitWidth(1, 1);

        final var masterConfig = new SimpleMasterBuilder<FormTypeBatchUpdateForAssetClassAction>().forEntity(FormTypeBatchUpdateForAssetClassAction.class)
                .addProp("formClass").asAutocompleter().also()
                .addAction(MasterActions.REFRESH).shortDesc(MASTER_CANCEL_ACTION_SHORT_DESC).longDesc(MASTER_CANCEL_ACTION_LONG_DESC)
                .addAction(MasterActions.SAVE).shortDesc(MASTER_SAVE_ACTION_SHORT_DESC).longDesc(MASTER_SAVE_ACTION_LONG_DESC)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(300, 200, Unit.PX))
                .done();

        return new EntityMaster<>(FormTypeBatchUpdateForAssetClassAction.class, FormTypeBatchUpdateForAssetClassActionProducer.class, masterConfig, injector);
    }
}