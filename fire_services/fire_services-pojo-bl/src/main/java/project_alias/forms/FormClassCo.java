package project_alias.forms;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link FormClass}.
 *
 * @author Developers
 *
 */
public interface FormClassCo extends IEntityDao<FormClass> {

    static final IFetchProvider<FormClass> FETCH_PROVIDER = EntityUtils.fetch(FormClass.class)
            .with("person", "status", "date"); //formType

}
