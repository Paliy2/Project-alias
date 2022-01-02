package project_alias.forms;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link FormClassLocator}.
 *
 * @author Developers
 *
 */
public interface FormClassLocatorCo extends IEntityDao<FormClassLocator> {

    static final IFetchProvider<FormClassLocator> FETCH_PROVIDER = EntityUtils.fetch(FormClassLocator.class).with("formClass");

}
