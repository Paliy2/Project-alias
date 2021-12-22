package project_alias.forms;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Status}.
 *
 * @author Developers
 *
 */
public interface StatusCo extends IEntityDao<Status> {

    static final IFetchProvider<Status> FETCH_PROVIDER = EntityUtils.fetch(Status.class).with(
            "title", "desc");

}
