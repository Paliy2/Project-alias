package project_alias.security.tokens.functional;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import project_alias.forms.actions.FormTypeBatchUpdateForFormClassAction;
import project_alias.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link FormTypeBatchUpdateForFormClassAction} to guard Execute.
 *
 * @author Developers
 *
 */
public class FormTypeBatchUpdateForFormClassAction_CanExecute_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.EXECUTE.forTitle(), FormTypeBatchUpdateForFormClassAction.ENTITY_TITLE);
    public final static String DESC = format(Template.EXECUTE.forDesc(), FormTypeBatchUpdateForFormClassAction.ENTITY_TITLE);
}