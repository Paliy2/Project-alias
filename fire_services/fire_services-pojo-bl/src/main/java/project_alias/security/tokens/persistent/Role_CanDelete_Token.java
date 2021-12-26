package project_alias.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import project_alias.roles.Role;
import project_alias.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link Role} to guard Delete.
 *
 * @author Project-alias team
 *
 */
public class Role_CanDelete_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.DELETE.forTitle(), Role.ENTITY_TITLE);
    public final static String DESC = format(Template.DELETE.forDesc(), Role.ENTITY_TITLE);
}