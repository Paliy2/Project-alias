package project_alias.security.tokens.persistent;

import static java.lang.String.format;

import project_alias.forms.Status;
import project_alias.security.tokens.UsersAndPersonnelModuleToken;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link Status} to guard Save.
 *
 * @author Developers
 *
 */
public class Status_CanSave_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.SAVE.forTitle(), Status.ENTITY_TITLE);
    public final static String DESC = format(Template.SAVE.forDesc(), Status.ENTITY_TITLE);
}