package project_alias.security.tokens;

import project_alias.config.Modules;
import ua.com.fielden.platform.security.ISecurityToken;

/**
 * Top level security token for all security tokens that belong to module {@link Modules#USERS_AND_PERSONNEL};
 *
 * @author Generated
 */

public class UsersAndPersonnelModuleToken implements ISecurityToken {
    public static final String TITLE = Modules.USERS_AND_PERSONNEL.title;
    public static final String DESC = Modules.USERS_AND_PERSONNEL.desc;
}
