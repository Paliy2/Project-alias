package project_alias.persons;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link PersonRole}.
 *
 * @author Project-alias team
 *
 */
public interface PersonRoleCo extends IEntityDao<PersonRole> {

    static final IFetchProvider<PersonRole> FETCH_PROVIDER = EntityUtils.fetch(PersonRole.class)
    		.with("person", "assignedRole", "assignedDate");

}
