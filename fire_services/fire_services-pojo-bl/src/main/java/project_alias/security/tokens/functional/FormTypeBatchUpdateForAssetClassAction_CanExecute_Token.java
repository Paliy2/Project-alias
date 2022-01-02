package project_alias.security.tokens.functional;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import project_alias.forms.actions.FormTypeBatchUpdateForAssetClassAction;
import project_alias.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link FormTypeBatchUpdateForAssetClassAction} to guard Execute.
 *
 * @author Developers
 *
 */
public class FormTypeBatchUpdateForAssetClassAction_CanExecute_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.EXECUTE.forTitle(), FormTypeBatchUpdateForAssetClassAction.ENTITY_TITLE);
    public final static String DESC = format(Template.EXECUTE.forDesc(), FormTypeBatchUpdateForAssetClassAction.ENTITY_TITLE);
}