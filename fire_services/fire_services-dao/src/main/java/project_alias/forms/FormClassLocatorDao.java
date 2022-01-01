package project_alias.forms;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link FormClassLocatorCo}.
 *
 * @author Developers
 *
 */
@EntityType(FormClassLocator.class)
public class FormClassLocatorDao extends CommonEntityDao<FormClassLocator> implements FormClassLocatorCo {

    @Inject
    public FormClassLocatorDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<FormClassLocator> createFetchProvider() {
        return FETCH_PROVIDER;
    }

}