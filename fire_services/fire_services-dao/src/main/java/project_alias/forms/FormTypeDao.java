package project_alias.forms;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import project_alias.security.tokens.persistent.FormType_CanSave_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link FormTypeCo}.
 *
 * @author Developers
 *
 */
@EntityType(FormType.class)
public class FormTypeDao extends CommonEntityDao<FormType> implements FormTypeCo {

    @Inject
    public FormTypeDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(FormType_CanSave_Token.class)
    public FormType save(FormType entity) {
        return super.save(entity);
    }

    @Override
    protected IFetchProvider<FormType> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}