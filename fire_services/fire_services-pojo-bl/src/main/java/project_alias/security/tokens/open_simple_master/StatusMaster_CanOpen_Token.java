package project_alias.security.tokens.open_simple_master;

import static java.lang.String.format;

import project_alias.forms.Status;
import project_alias.security.tokens.UsersAndPersonnelModuleToken;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link Status} to guard Open.
 *
 * @author Developers
 *
 */
public class StatusMaster_CanOpen_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.MASTER_OPEN.forTitle(), Status.ENTITY_TITLE + " Master");
    public final static String DESC = format(Template.MASTER_OPEN.forDesc(), Status.ENTITY_TITLE);
}