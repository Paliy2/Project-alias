package project_alias.equipments;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Equipment}.
 *
 * @author Developers
 *
 */
public interface EquipmentCo extends IEntityDao<Equipment> {

    static final String DEFAULT_KEY_VALUE = "Autogenerated";
    static final String EQUIPMENT_KEY_TITLE = "Equipment No";

    static final IFetchProvider<Equipment> FETCH_PROVIDER = EntityUtils.fetch(Equipment.class)
            .with("number", "title", "equipmentType", "vehicle", "desc");

}
