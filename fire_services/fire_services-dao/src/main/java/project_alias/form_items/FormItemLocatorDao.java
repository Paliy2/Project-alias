package project_alias.form_items;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link FormItemLocatorCo}.
 *
 * @author Developers
 *
 */
@EntityType(FormItemLocator.class)
public class FormItemLocatorDao extends CommonEntityDao<FormItemLocator> implements FormItemLocatorCo {

    @Inject
    public FormItemLocatorDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<FormItemLocator> createFetchProvider() {
        return FETCH_PROVIDER;
    }

}