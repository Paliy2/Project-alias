package project_alias.forms.actions;

import com.google.inject.Inject;

import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import project_alias.security.tokens.functional.FormTypeBatchUpdateForFormClassAction_CanExecute_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;

/**
 * DAO implementation for companion object {@link FormTypeBatchUpdateForFormClassActionCo}.
 *
 * @author Developers
 *
 */
@EntityType(FormTypeBatchUpdateForFormClassAction.class)
public class FormTypeBatchUpdateForFormClassActionDao extends CommonEntityDao<FormTypeBatchUpdateForFormClassAction> implements FormTypeBatchUpdateForFormClassActionCo {

    @Inject
    public FormTypeBatchUpdateForFormClassActionDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(FormTypeBatchUpdateForFormClassAction_CanExecute_Token.class)
    public FormTypeBatchUpdateForFormClassAction save(FormTypeBatchUpdateForFormClassAction entity) {
        return super.save(entity);
    }

}