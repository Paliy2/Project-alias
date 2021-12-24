package project_alias.security.tokens.persistent;

import static java.lang.String.format;

import project_alias.security.tokens.UsersAndPersonnelModuleToken;
import project_alias.vehicles.VehicleType;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link VehicleType} to guard Delete.
 *
 * @author Developers
 *
 */
public class VehicleType_CanDelete_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.DELETE.forTitle(), VehicleType.ENTITY_TITLE);
    public final static String DESC = format(Template.DELETE.forDesc(), VehicleType.ENTITY_TITLE);
}