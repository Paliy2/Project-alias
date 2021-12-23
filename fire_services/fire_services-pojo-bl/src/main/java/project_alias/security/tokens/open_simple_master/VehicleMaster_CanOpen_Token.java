package project_alias.security.tokens.open_simple_master;

import static java.lang.String.format;

import project_alias.security.tokens.UsersAndPersonnelModuleToken;
import project_alias.vehicles.Vehicle;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link Vehicle} to guard Open.
 *
 * @author Developers
 *
 */
public class VehicleMaster_CanOpen_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.MASTER_OPEN.forTitle(), Vehicle.ENTITY_TITLE + " Master");
    public final static String DESC = format(Template.MASTER_OPEN.forDesc(), Vehicle.ENTITY_TITLE);
}