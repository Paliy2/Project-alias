package project_alias.security.tokens.open_simple_master;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import project_alias.roles.Role;
import project_alias.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link Role} to guard Open.
 *
 * @author Project-alias team
 *
 */
public class RoleMaster_CanOpen_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.MASTER_OPEN.forTitle(), Role.ENTITY_TITLE + " Master");
    public final static String DESC = format(Template.MASTER_OPEN.forDesc(), Role.ENTITY_TITLE);
}