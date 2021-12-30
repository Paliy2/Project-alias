package project_alias.persons;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Person}.
 *
 * @author Project-alias team
 *
 */
public interface PersonCo extends IEntityDao<Person> {

    static final IFetchProvider<Person> FETCH_PROVIDER = EntityUtils.fetch(Person.class)
    		.with("name", "surname", "phoneNumber", "email");
}
