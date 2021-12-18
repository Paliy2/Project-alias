package project_alias.equipments;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import project_alias.security.tokens.persistent.EquipmentClass_CanSave_Token;
import project_alias.security.tokens.persistent.EquipmentClass_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link EquipmentClassCo}.
 *
 * @author Project-alias Team
 *
 */
@EntityType(EquipmentClass.class)
public class EquipmentClassDao extends CommonEntityDao<EquipmentClass> implements EquipmentClassCo {

    @Inject
    public EquipmentClassDao(final IFilter filter) {
        super(filter);
    }
    
    @Override
	public EquipmentClass new_() {
		return super.new_().setActive(true);
	}

    @Override
    @SessionRequired
    @Authorise(EquipmentClass_CanSave_Token.class)
    public EquipmentClass save(EquipmentClass entity) {
        return super.save(entity);
    }

    @Override
    @SessionRequired
    @Authorise(EquipmentClass_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(EquipmentClass_CanDelete_Token.class)
    public int batchDelete(final List<EquipmentClass> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<EquipmentClass> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}