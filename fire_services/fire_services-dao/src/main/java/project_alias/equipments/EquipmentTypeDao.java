package project_alias.equipments;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import project_alias.security.tokens.persistent.EquipmentType_CanSave_Token;
import project_alias.security.tokens.persistent.EquipmentType_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link EquipmentTypeCo}.
 *
 * @author Developers
 *
 */
@EntityType(EquipmentType.class)
public class EquipmentTypeDao extends CommonEntityDao<EquipmentType> implements EquipmentTypeCo {

    @Inject
    public EquipmentTypeDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(EquipmentType_CanSave_Token.class)
    public EquipmentType save(EquipmentType entity) {
        return super.save(entity).setActive(true);
    }

    @Override
    @SessionRequired
    @Authorise(EquipmentType_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(EquipmentType_CanDelete_Token.class)
    public int batchDelete(final List<EquipmentType> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<EquipmentType> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}