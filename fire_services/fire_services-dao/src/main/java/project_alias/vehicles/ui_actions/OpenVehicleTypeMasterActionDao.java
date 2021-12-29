package project_alias.vehicles.ui_actions;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.AbstractOpenCompoundMasterDao;
import ua.com.fielden.platform.dao.IEntityAggregatesOperations;
import project_alias.vehicles.Vehicle;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link OpenVehicleTypeMasterActionCo}.
 *
 * @author Developers
 *
 */
@EntityType(OpenVehicleTypeMasterAction.class)
public class OpenVehicleTypeMasterActionDao extends AbstractOpenCompoundMasterDao<OpenVehicleTypeMasterAction> implements OpenVehicleTypeMasterActionCo {

    @Inject
    public OpenVehicleTypeMasterActionDao(final IFilter filter, final IEntityAggregatesOperations coAggregates) {
        super(filter, coAggregates);
        addViewBinding(OpenVehicleTypeMasterAction.VEHICLES, Vehicle.class, "vehicleType");
    }

    @Override
    protected IFetchProvider<OpenVehicleTypeMasterAction> createFetchProvider() {
        return FETCH_PROVIDER;
    }

}