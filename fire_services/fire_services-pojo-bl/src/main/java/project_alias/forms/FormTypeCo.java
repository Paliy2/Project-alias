package project_alias.forms;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link FormType}.
 *
 * @author Developers
 *
 */
public interface FormTypeCo extends IEntityDao<FormType> {
    static final String DEFAULT_KEY_VALUE = "Autogenerated";

    static final IFetchProvider<FormType> FETCH_PROVIDER = EntityUtils.fetch(FormType.class)
            .with("title", "desc", "assignedRole", "formTypeItems");
}
