package project_alias.form_items;

import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import project_alias.security.tokens.persistent.FormTypeItem_CanSave_Token;
import project_alias.security.tokens.persistent.FormTypeItem_CanDelete_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link FormTypeItemCo}.
 *
 * @author Project-alias team
 *
 */
@EntityType(FormTypeItem.class)
public class FormTypeItemDao extends CommonEntityDao<FormTypeItem> implements FormTypeItemCo {
    
    @Inject
    public FormTypeItemDao(final IFilter filter) {
        super(filter);
    }
    
    @Override
    public FormTypeItem new_() {
        return super.new_().setActive(true);
    }
    
    @Override
    @SessionRequired
    @Authorise(FormTypeItem_CanSave_Token.class)
    public FormTypeItem save(FormTypeItem entity) {
        return super.save(entity);
    }

    @Override
    @SessionRequired
    @Authorise(FormTypeItem_CanDelete_Token.class)
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    @Authorise(FormTypeItem_CanDelete_Token.class)
    public int batchDelete(final List<FormTypeItem> entities) {
        return defaultBatchDelete(entities);
    }

    @Override
    protected IFetchProvider<FormTypeItem> createFetchProvider() {
        return FETCH_PROVIDER;
    }
}