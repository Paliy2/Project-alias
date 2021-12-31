package project_alias.form_items;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link FormItemLocator}.
 *
 * @author Developers
 *
 */
public interface FormItemLocatorCo extends IEntityDao<FormItemLocator> {

    static final IFetchProvider<FormItemLocator> FETCH_PROVIDER = EntityUtils.fetch(FormItemLocator.class).with("formItem");

}
