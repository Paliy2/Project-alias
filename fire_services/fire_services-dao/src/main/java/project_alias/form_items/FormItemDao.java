package project_alias.form_items;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import project_alias.security.tokens.persistent.FormItem_CanSave_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link FormItemCo}.
 *
 * @author Developers
 *
 */
@EntityType(FormItem.class)
public class FormItemDao extends CommonEntityDao<FormItem> implements FormItemCo {

    @Inject
    public FormItemDao(final IFilter filter) {
        super(filter);
    }
    
    @Override
    public FormItem new_() {
        return super.new_().setActive(true);
    }
    
    @Override
    @SessionRequired
    @Authorise(FormItem_CanSave_Token.class)
    public FormItem save(FormItem entity) {
        return super.save(entity);
    }

    @Override
    protected IFetchProvider<FormItem> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}