package project_alias.equipments.producers;

import com.google.inject.Inject;

import project_alias.equipments.EquipmentClass;
import project_alias.equipments.EquipmentType;
import ua.com.fielden.platform.entity.DefaultEntityProducerWithContext;
import ua.com.fielden.platform.entity.EntityNewAction;
import ua.com.fielden.platform.entity.factory.EntityFactory;
import ua.com.fielden.platform.entity.factory.ICompanionObjectFinder;
import ua.com.fielden.platform.error.Result;
/**
 * A producer for new instances of entity {@link EquipmentType}.
 *
 * @author Developers
 *
 */
public class EquipmentTypeProducer extends DefaultEntityProducerWithContext<EquipmentType> {

    @Inject
    public EquipmentTypeProducer(final EntityFactory factory, final ICompanionObjectFinder coFinder) {
        super(factory, EquipmentType.class, coFinder);
    }

    @Override
    protected EquipmentType provideDefaultValuesForStandardNew(final EquipmentType entityIn, final EntityNewAction masterEntity) {
        final EquipmentType newEquipmentType = super.provideDefaultValuesForStandardNew(entityIn, masterEntity);
        // This producer can be invoked from two places:
        // 1. Standalone centre
        // 2. Centre embedded in EquipmentClass Master
        // In the second case we want to default the equipmentClass and make it read-only
        if (ofMasterEntity().keyOfMasterEntityInstanceOf(EquipmentClass.class)) {
            final EquipmentClass shallowEquipmentClass = ofMasterEntity().keyOfMasterEntity(EquipmentClass.class);
            // shallowEquipmentClass has been fetched in OpenEquipmentClassMasterActionProducer with key and desc only
            // It needs to be re-fetched here using a slightly deeper fetch model, as appropriate for CocEntry
            newEquipmentType.setEquipmentClass(refetch(shallowEquipmentClass, "equipmentClass"));
            newEquipmentType.getProperty("equipmentClass").validationResult().ifFailure(Result::throwRuntime);
            newEquipmentType.getProperty("equipmentClass").setEditable(false);
        }
        return newEquipmentType;
    }
}
