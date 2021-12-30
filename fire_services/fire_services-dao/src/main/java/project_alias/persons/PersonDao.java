package project_alias.persons;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import project_alias.security.tokens.persistent.Person_CanSave_Token;
import project_alias.security.tokens.persistent.Person_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link PersonCo}.
 *
 * @author Project-alias team
 *
 */
@EntityType(Person.class)
public class PersonDao extends CommonEntityDao<Person> implements PersonCo {

    @Inject
    public PersonDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(Person_CanSave_Token.class)
    public Person save(Person entity) {
        return super.save(entity);
    }

    @Override
    @SessionRequired
    @Authorise(Person_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(Person_CanDelete_Token.class)
    public int batchDelete(final List<Person> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<Person> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}