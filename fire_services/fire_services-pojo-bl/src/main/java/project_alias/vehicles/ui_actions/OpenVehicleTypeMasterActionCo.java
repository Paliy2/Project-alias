package project_alias.vehicles.ui_actions;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link OpenVehicleTypeMasterAction}.
 *
 * @author Developers
 *
 */
public interface OpenVehicleTypeMasterActionCo extends IEntityDao<OpenVehicleTypeMasterAction> {

    static final IFetchProvider<OpenVehicleTypeMasterAction> FETCH_PROVIDER = EntityUtils.fetch(OpenVehicleTypeMasterAction.class).with(
        // key is needed to be correctly autopopulated by newly saved compound master entity (ID-based restoration of entity-typed key)
        "key");

}
