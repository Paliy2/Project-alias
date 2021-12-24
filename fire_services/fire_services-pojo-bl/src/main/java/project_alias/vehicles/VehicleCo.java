package project_alias.vehicles;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Vehicle}.
 *
 * @author Developers
 *
 */
public interface VehicleCo extends IEntityDao<Vehicle> {

    static final IFetchProvider<Vehicle> FETCH_PROVIDER = EntityUtils.fetch(Vehicle.class).with(
            "number", "model", "vehicleType", "assignedDriver", "desc");

}
