package project_alias.form_items;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link FormTypeItem}.
 *
 * @author Project-alias team
 *
 */
public interface FormTypeItemCo extends IEntityDao<FormTypeItem> {

    static final IFetchProvider<FormTypeItem> FETCH_PROVIDER = EntityUtils.fetch(FormTypeItem.class)
    		.with("title", "desc");
}
