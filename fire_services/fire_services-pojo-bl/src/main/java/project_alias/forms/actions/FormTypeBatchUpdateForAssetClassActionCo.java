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
public interface FormTypeBatchUpdateForAssetClassActionCo extends IEntityDao<FormTypeBatchUpdateForAssetClassAction> {
    static final IFetchProvider<FormTypeBatchUpdateForAssetClassAction> FETCH_PROVIDER = EntityUtils.fetch(FormTypeBatchUpdateForAssetClassAction.class).with("formClass");
}
