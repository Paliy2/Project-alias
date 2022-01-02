package project_alias.roles;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import project_alias.security.tokens.persistent.Role_CanSave_Token;
import project_alias.equipments.Equipment;
import project_alias.security.tokens.persistent.Role_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link RoleCo}.
 *
 * @author Project-alias team
 *
 */
@EntityType(Role.class)
public class RoleDao extends CommonEntityDao<Role> implements RoleCo {

    @Inject
    public RoleDao(final IFilter filter) {
        super(filter);
    }
    
    @Override
    public Role new_() {
        return super.new_().setActive(true);
    }

    @Override
    @SessionRequired
    @Authorise(Role_CanSave_Token.class)
    public Role save(Role entity) {
        return super.save(entity);
    }

    @Override
    @SessionRequired
    @Authorise(Role_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(Role_CanDelete_Token.class)
    public int batchDelete(final List<Role> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<Role> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}