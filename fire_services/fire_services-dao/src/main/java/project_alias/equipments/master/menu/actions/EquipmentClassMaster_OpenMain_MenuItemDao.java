package project_alias.equipments.master.menu.actions;

import com.google.inject.Inject;

import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import project_alias.security.tokens.compound_master_menu.EquipmentClassMaster_OpenMain_MenuItem_CanAccess_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link EquipmentClassMaster_OpenMain_MenuItemCo}.
 *
 * @author Developers
 *
 */
@EntityType(EquipmentClassMaster_OpenMain_MenuItem.class)
public class EquipmentClassMaster_OpenMain_MenuItemDao extends CommonEntityDao<EquipmentClassMaster_OpenMain_MenuItem> implements EquipmentClassMaster_OpenMain_MenuItemCo {

    @Inject
    public EquipmentClassMaster_OpenMain_MenuItemDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(EquipmentClassMaster_OpenMain_MenuItem_CanAccess_Token.class)
    public EquipmentClassMaster_OpenMain_MenuItem save(EquipmentClassMaster_OpenMain_MenuItem entity) {
        return super.save(entity);
    }

}