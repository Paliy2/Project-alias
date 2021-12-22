package project_alias.equipments;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link EquipmentType}.
 *
 * @author Developers
 *
 */
public interface EquipmentTypeCo extends IEntityDao<EquipmentType> {

    static final IFetchProvider<EquipmentType> FETCH_PROVIDER = EntityUtils.fetch(EquipmentType.class)
            .with("title", "desc", "equipmentClass");

}
