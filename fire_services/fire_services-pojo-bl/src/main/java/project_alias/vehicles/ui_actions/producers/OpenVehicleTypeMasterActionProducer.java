package project_alias.vehicles.ui_actions.producers;

import com.google.inject.Inject;

import project_alias.vehicles.VehicleType;
import project_alias.vehicles.ui_actions.OpenVehicleTypeMasterAction;
import ua.com.fielden.platform.security.Authorise;
import project_alias.security.tokens.open_compound_master.OpenVehicleTypeMasterAction_CanOpen_Token;
import ua.com.fielden.platform.entity.AbstractProducerForOpenEntityMasterAction;
import ua.com.fielden.platform.entity.factory.EntityFactory;
import ua.com.fielden.platform.entity.factory.ICompanionObjectFinder;

/**
 * A producer for new instances of entity {@link OpenVehicleTypeMasterAction}.
 *
 * @author Developers
 *
 */
public class OpenVehicleTypeMasterActionProducer extends AbstractProducerForOpenEntityMasterAction<VehicleType, OpenVehicleTypeMasterAction> {

    @Inject
    public OpenVehicleTypeMasterActionProducer(final EntityFactory factory, final ICompanionObjectFinder companionFinder) {
        super(factory, VehicleType.class, OpenVehicleTypeMasterAction.class, companionFinder);
    }

    @Override
    @Authorise(OpenVehicleTypeMasterAction_CanOpen_Token.class)
    protected OpenVehicleTypeMasterAction provideDefaultValues(OpenVehicleTypeMasterAction openAction) {
        return super.provideDefaultValues(openAction);
    }
}
