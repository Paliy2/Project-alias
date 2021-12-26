package project_alias.equipments.master.menu.actions;

import com.google.inject.Inject;

import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import project_alias.security.tokens.compound_master_menu.EquipmentClassMaster_OpenEquipmentType_MenuItem_CanAccess_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link EquipmentClassMaster_OpenEquipmentType_MenuItemCo}.
 *
 * @author Developers
 *
 */
@EntityType(EquipmentClassMaster_OpenEquipmentType_MenuItem.class)
public class EquipmentClassMaster_OpenEquipmentType_MenuItemDao extends CommonEntityDao<EquipmentClassMaster_OpenEquipmentType_MenuItem> implements EquipmentClassMaster_OpenEquipmentType_MenuItemCo {

    @Inject
    public EquipmentClassMaster_OpenEquipmentType_MenuItemDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(EquipmentClassMaster_OpenEquipmentType_MenuItem_CanAccess_Token.class)
    public EquipmentClassMaster_OpenEquipmentType_MenuItem save(EquipmentClassMaster_OpenEquipmentType_MenuItem entity) {
        return super.save(entity);
    }

}