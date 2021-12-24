package project_alias.equipments.ui_actions;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link OpenEquipmentClassMasterAction}.
 *
 * @author Developers
 *
 */
public interface OpenEquipmentClassMasterActionCo extends IEntityDao<OpenEquipmentClassMasterAction> {

    static final IFetchProvider<OpenEquipmentClassMasterAction> FETCH_PROVIDER = EntityUtils.fetch(OpenEquipmentClassMasterAction.class).with(
        // key is needed to be correctly autopopulated by newly saved compound master entity (ID-based restoration of entity-typed key)
        "key");

}
