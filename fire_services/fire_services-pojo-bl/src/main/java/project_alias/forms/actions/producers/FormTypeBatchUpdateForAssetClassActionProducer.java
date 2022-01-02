package project_alias.forms.actions.producers;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.DefaultEntityProducerWithContext;
import ua.com.fielden.platform.entity.factory.EntityFactory;
import ua.com.fielden.platform.entity.factory.ICompanionObjectFinder;
import ua.com.fielden.platform.error.Result;
import project_alias.forms.actions.FormTypeBatchUpdateForAssetClassAction;
/**
 * A producer for new instances of entity {@link AssetTypeBatchUpdateForAssetClassAction}.
 *
 * @author Developers
 *
 */
public class FormTypeBatchUpdateForAssetClassActionProducer extends DefaultEntityProducerWithContext<FormTypeBatchUpdateForAssetClassAction> {

    @Inject
    public FormTypeBatchUpdateForAssetClassActionProducer(final EntityFactory factory, final ICompanionObjectFinder coFinder) {
        super(factory, FormTypeBatchUpdateForAssetClassAction.class, coFinder);
    }

    @Override
    protected FormTypeBatchUpdateForAssetClassAction provideDefaultValues(final FormTypeBatchUpdateForAssetClassAction action) {
        if (contextNotEmpty()) {
            if (selectedEntitiesEmpty()) {
                throw Result.failure("Please select some asset types to be updated and try again.");
            }
            action.setSelectedEntityIds(selectedEntityIds());
            action.getProperty("selectedEntityIds").validationResult().ifFailure(Result::throwRuntime);
        }
        return super.provideDefaultValues(action);
    }
}
