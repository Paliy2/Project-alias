package project_alias.forms.actions;

import ua.com.fielden.platform.dao.IEntityDao;
import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;

/**
 * Companion object for entity {@link FormTypeBatchUpdateForAssetClassAction}.
 *
 * @author Developers
 *
 */
public interface FormTypeBatchUpdateForFormClassActionCo extends IEntityDao<FormTypeBatchUpdateForFormClassAction> {
    static final IFetchProvider<FormTypeBatchUpdateForFormClassAction> FETCH_PROVIDER = EntityUtils.fetch(FormTypeBatchUpdateForFormClassAction.class).with("formClass");
}
