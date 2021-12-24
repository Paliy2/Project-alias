package project_alias.forms;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import project_alias.security.tokens.persistent.Status_CanSave_Token;
import project_alias.security.tokens.persistent.Status_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link StatusCo}.
 *
 * @author Developers
 *
 */
@EntityType(Status.class)
public class StatusDao extends CommonEntityDao<Status> implements StatusCo {

    @Inject
    public StatusDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(Status_CanSave_Token.class)
    public Status save(Status entity) {
        return super.save(entity);
    }

    @Override
    @SessionRequired
    @Authorise(Status_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(Status_CanDelete_Token.class)
    public int batchDelete(final List<Status> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<Status> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}