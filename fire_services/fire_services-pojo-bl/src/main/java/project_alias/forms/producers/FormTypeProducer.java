package project_alias.forms.producers;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.DefaultEntityProducerWithContext;
import ua.com.fielden.platform.entity.factory.EntityFactory;
import ua.com.fielden.platform.entity.factory.ICompanionObjectFinder;
import ua.com.fielden.platform.entity.EntityNewAction;
import ua.com.fielden.platform.error.Result;
import project_alias.forms.FormClass;
import project_alias.forms.FormType;
/**
 * A producer for new instances of entity {@link FormType}.
 *
 * @author Project-alias team
 *
 */
public class FormTypeProducer extends DefaultEntityProducerWithContext<FormType> {

    @Inject
    public FormTypeProducer(final EntityFactory factory, final ICompanionObjectFinder coFinder) {
        super(factory, FormType.class, coFinder);
    }

    @Override
    protected FormType provideDefaultValuesForStandardNew(final FormType entityIn, final EntityNewAction masterEntity) {
        final FormType newAssetType = super.provideDefaultValuesForStandardNew(entityIn, masterEntity);
        // This producer can be invoked from two places:
        // 1. Standalone centre
        // 2. Centre embedded in AssetClass Master
        // In the second case we want to default the assetClass and make it read-only
        if (ofMasterEntity().keyOfMasterEntityInstanceOf(FormClass.class)) {
            final FormClass shallowAssetClass = ofMasterEntity().keyOfMasterEntity(FormClass.class);
            // shallowAssetClass has been fetched in OpenAssetClassMasterActionProducer with key and desc only
            // It needs to be re-fetched here using a slightly deeper fetch model, as appropriate for CocEntry
            newAssetType.setFormClass(refetch(shallowAssetClass, "formClass"));
            newAssetType.getProperty("formClass").validationResult().ifFailure(Result::throwRuntime);
            newAssetType.getProperty("formClass").setEditable(false);
        }
        return newAssetType;
    }
}
