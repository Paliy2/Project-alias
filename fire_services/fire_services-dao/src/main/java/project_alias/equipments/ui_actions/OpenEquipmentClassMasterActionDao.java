package project_alias.equipments.ui_actions;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.AbstractOpenCompoundMasterDao;
import ua.com.fielden.platform.dao.IEntityAggregatesOperations;
import project_alias.equipments.EquipmentType;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link OpenEquipmentClassMasterActionCo}.
 *
 * @author Developers
 *
 */
@EntityType(OpenEquipmentClassMasterAction.class)
public class OpenEquipmentClassMasterActionDao extends AbstractOpenCompoundMasterDao<OpenEquipmentClassMasterAction> implements OpenEquipmentClassMasterActionCo {

    @Inject
    public OpenEquipmentClassMasterActionDao(final IFilter filter, final IEntityAggregatesOperations coAggregates) {
        super(filter, coAggregates);
        addViewBinding(OpenEquipmentClassMasterAction.EQUIPMENTTYPES, EquipmentType.class, "equipmentClass");
    }

    @Override
    protected IFetchProvider<OpenEquipmentClassMasterAction> createFetchProvider() {
        return FETCH_PROVIDER;
    }

}