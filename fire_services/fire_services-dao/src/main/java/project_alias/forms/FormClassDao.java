package project_alias.forms;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import project_alias.form_items.FormTypeItem;
import project_alias.security.tokens.persistent.FormClass_CanSave_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link FormClassCo}.
 *
 * @author Developers
 *
 */
@EntityType(FormClass.class)
public class FormClassDao extends CommonEntityDao<FormClass> implements FormClassCo {

    @Inject
    public FormClassDao(final IFilter filter) {
        super(filter);
    }
    
    @Override
    public FormClass new_() {
        return super.new_().setActive(true);
    }
    
    @Override
    @SessionRequired
    @Authorise(FormClass_CanSave_Token.class)
    public FormClass save(FormClass entity) {
        return super.save(entity);
    }

    @Override
    protected IFetchProvider<FormClass> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}