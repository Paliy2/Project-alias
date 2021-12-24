package project_alias.vehicles;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link VehicleType}.
 *
 * @author Developers
 *
 */
public interface VehicleTypeCo extends IEntityDao<VehicleType> {

    static final IFetchProvider<VehicleType> FETCH_PROVIDER = EntityUtils.fetch(VehicleType.class).with("title", "desc");

}
