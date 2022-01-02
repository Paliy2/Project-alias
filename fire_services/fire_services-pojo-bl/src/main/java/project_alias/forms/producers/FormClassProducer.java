package project_alias.forms.producers;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.DefaultEntityProducerWithContext;
import ua.com.fielden.platform.entity.factory.EntityFactory;
import ua.com.fielden.platform.entity.factory.ICompanionObjectFinder;

import project_alias.forms.FormClass;
/**
 * A producer for new instances of entity {@link FormClass}.
 *
 * @author Developers
 *
 */
public class FormClassProducer extends DefaultEntityProducerWithContext<FormClass> {

    @Inject
    public FormClassProducer(final EntityFactory factory, final ICompanionObjectFinder coFinder) {
        super(factory, FormClass.class, coFinder);
    }

    @Override
    protected FormClass provideDefaultValues(final FormClass entity) {
        // TODO Context from producers should always be captured as entity properties.
        //      Producers provide context decomposition API - refer IContextDecomposer.
        //      For example to capture selected entities it is best to use method "selectedEntityIds",
        //      which returns a set of Long values and is much faster for marshaling than the fully fledged entities.
        return super.provideDefaultValues(entity);
    }
}
