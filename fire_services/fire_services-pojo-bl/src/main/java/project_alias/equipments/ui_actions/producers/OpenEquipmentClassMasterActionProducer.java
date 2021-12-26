package project_alias.equipments.ui_actions.producers;

import com.google.inject.Inject;

import project_alias.equipments.EquipmentClass;
import project_alias.equipments.ui_actions.OpenEquipmentClassMasterAction;
import ua.com.fielden.platform.security.Authorise;
import project_alias.security.tokens.open_compound_master.OpenEquipmentClassMasterAction_CanOpen_Token;
import ua.com.fielden.platform.entity.AbstractProducerForOpenEntityMasterAction;
import ua.com.fielden.platform.entity.factory.EntityFactory;
import ua.com.fielden.platform.entity.factory.ICompanionObjectFinder;

/**
 * A producer for new instances of entity {@link OpenEquipmentClassMasterAction}.
 *
 * @author Developers
 *
 */
public class OpenEquipmentClassMasterActionProducer extends AbstractProducerForOpenEntityMasterAction<EquipmentClass, OpenEquipmentClassMasterAction> {

    @Inject
    public OpenEquipmentClassMasterActionProducer(final EntityFactory factory, final ICompanionObjectFinder companionFinder) {
        super(factory, EquipmentClass.class, OpenEquipmentClassMasterAction.class, companionFinder);
    }

    @Override
    @Authorise(OpenEquipmentClassMasterAction_CanOpen_Token.class)
    protected OpenEquipmentClassMasterAction provideDefaultValues(OpenEquipmentClassMasterAction openAction) {
        return super.provideDefaultValues(openAction);
    }
}
