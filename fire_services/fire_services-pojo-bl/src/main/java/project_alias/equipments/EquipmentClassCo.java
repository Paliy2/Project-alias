package project_alias.equipments;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link EquipmentClass}.
 *
 * @author Project-alias Team
 *
 */
public interface EquipmentClassCo extends IEntityDao<EquipmentClass> {

    static final IFetchProvider<EquipmentClass> FETCH_PROVIDER = EntityUtils.fetch(EquipmentClass.class)
    		.with("title", "desc");

}
