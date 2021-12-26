package project_alias.roles;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Role}.
 *
 * @author Project-alias team
 *
 */
public interface RoleCo extends IEntityDao<Role> {

    static final IFetchProvider<Role> FETCH_PROVIDER = EntityUtils.fetch(Role.class)
    		.with("title", "desc");

}
