package project_alias.forms.actions;

import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.from;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.orderBy;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.select;

import java.util.stream.Stream;

import com.google.inject.Inject;

import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import project_alias.forms.FormClass;
import project_alias.forms.FormType;
import project_alias.forms.FormTypeCo;
import project_alias.security.tokens.functional.FormTypeBatchUpdateForAssetClassAction_CanExecute_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
import ua.com.fielden.platform.error.Result;


/**
 * DAO implementation for companion object {@link FormTypeBatchUpdateForAssetClassActionCo}.
 *
 * @author Developers
 *
 */
@EntityType(FormTypeBatchUpdateForAssetClassAction.class)
public class FormTypeBatchUpdateForAssetClassActionDao extends CommonEntityDao<FormTypeBatchUpdateForAssetClassAction> implements FormTypeBatchUpdateForAssetClassActionCo {

    @Inject
    public FormTypeBatchUpdateForAssetClassActionDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(FormTypeBatchUpdateForAssetClassAction_CanExecute_Token.class)
    public FormTypeBatchUpdateForAssetClassAction save(final FormTypeBatchUpdateForAssetClassAction action) {
        action.isValid().ifFailure(Result::throwRuntime);
        
        final var query = select(FormType.class).where().prop("id").in().values(action.getSelectedEntitiesIds().toArray()).model();
        final var qem = from(query).with(FormTypeCo.FETCH_PROVIDER.fetchModel()).model();
        
        final FormTypeCo coFormType = co$(FormType.class);
        try (final Stream<FormType> st = coFormType.stream(qem)) {
            st.forEach(at -> coFormType.save(at.setFormClass(action.getFormClass())));
        }
        
        return super.save(action);
    }
}