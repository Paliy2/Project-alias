package project_alias.security.tokens.persistent;

import static java.lang.String.format;

import project_alias.security.tokens.UsersAndPersonnelModuleToken;
import project_alias.vehicles.VehicleType;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link VehicleType} to guard Save.
 *
 * @author Developers
 *
 */
public class VehicleType_CanSave_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.SAVE.forTitle(), VehicleType.ENTITY_TITLE);
    public final static String DESC = format(Template.SAVE.forDesc(), VehicleType.ENTITY_TITLE);
}