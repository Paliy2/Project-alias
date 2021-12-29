package project_alias.vehicles.producers;

import com.google.inject.Inject;

import project_alias.vehicles.VehicleType;
import project_alias.vehicles.Vehicle;
import ua.com.fielden.platform.entity.DefaultEntityProducerWithContext;
import ua.com.fielden.platform.entity.EntityNewAction;
import ua.com.fielden.platform.entity.factory.EntityFactory;
import ua.com.fielden.platform.entity.factory.ICompanionObjectFinder;
import ua.com.fielden.platform.error.Result;
/**
 * A producer for new instances of entity {@link Vehicle}.
 *
 * @author Developers
 *
 */
public class VehicleProducer extends DefaultEntityProducerWithContext<Vehicle> {

    @Inject
    public VehicleProducer(final EntityFactory factory, final ICompanionObjectFinder coFinder) {
        super(factory, Vehicle.class, coFinder);
    }

    @Override
    protected Vehicle provideDefaultValuesForStandardNew(final Vehicle entityIn, final EntityNewAction masterEntity) {
        final Vehicle newVehicle = super.provideDefaultValuesForStandardNew(entityIn, masterEntity);
        // This producer can be invoked from two places:
        // 1. Standalone centre
        // 2. Centre embedded in VehicleType Master
        // In the second case we want to default the vehicleType and make it read-only
        if (ofMasterEntity().keyOfMasterEntityInstanceOf(VehicleType.class)) {
            final VehicleType shallowVehicleType = ofMasterEntity().keyOfMasterEntity(VehicleType.class);
            // shallowVehicleType has been fetched in OpenVehicleTypeMasterActionProducer with key and desc only
            // It needs to be re-fetched here using a slightly deeper fetch model, as appropriate for CocEntry
            newVehicle.setVehicleType(refetch(shallowVehicleType, "vehicleType"));
            newVehicle.getProperty("vehicleType").validationResult().ifFailure(Result::throwRuntime);
            newVehicle.getProperty("vehicleType").setEditable(false);
        }
        return newVehicle;
    }
}
