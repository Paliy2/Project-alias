package project_alias.form_items;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link FormItem}.
 *
 * @author Developers
 *
 */
public interface FormItemCo extends IEntityDao<FormItem> {

    static final IFetchProvider<FormItem> FETCH_PROVIDER = EntityUtils.fetch(FormItem.class).with(
         "formTypeItem", "accepted");
}
