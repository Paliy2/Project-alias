package project_alias.security.tokens.persistent;

import static java.lang.String.format;

import project_alias.forms.Status;
import project_alias.security.tokens.UsersAndPersonnelModuleToken;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link Status} to guard Delete.
 *
 * @author Developers
 *
 */
public class Status_CanDelete_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.DELETE.forTitle(), Status.ENTITY_TITLE);
    public final static String DESC = format(Template.DELETE.forDesc(), Status.ENTITY_TITLE);
}