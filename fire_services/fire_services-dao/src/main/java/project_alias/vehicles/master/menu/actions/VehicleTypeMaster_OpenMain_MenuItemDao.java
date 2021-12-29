package project_alias.vehicles.master.menu.actions;

import com.google.inject.Inject;

import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import project_alias.security.tokens.compound_master_menu.VehicleTypeMaster_OpenMain_MenuItem_CanAccess_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link VehicleTypeMaster_OpenMain_MenuItemCo}.
 *
 * @author Developers
 *
 */
@EntityType(VehicleTypeMaster_OpenMain_MenuItem.class)
public class VehicleTypeMaster_OpenMain_MenuItemDao extends CommonEntityDao<VehicleTypeMaster_OpenMain_MenuItem> implements VehicleTypeMaster_OpenMain_MenuItemCo {

    @Inject
    public VehicleTypeMaster_OpenMain_MenuItemDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(VehicleTypeMaster_OpenMain_MenuItem_CanAccess_Token.class)
    public VehicleTypeMaster_OpenMain_MenuItem save(VehicleTypeMaster_OpenMain_MenuItem entity) {
        return super.save(entity);
    }

}