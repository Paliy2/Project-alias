package project_alias.security.tokens.persistent;

import static java.lang.String.format;

import project_alias.security.tokens.UsersAndPersonnelModuleToken;
import project_alias.vehicles.Vehicle;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link Vehicle} to guard Save.
 *
 * @author Developers
 *
 */
public class Vehicle_CanSave_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.SAVE.forTitle(), Vehicle.ENTITY_TITLE);
    public final static String DESC = format(Template.SAVE.forDesc(), Vehicle.ENTITY_TITLE);
}