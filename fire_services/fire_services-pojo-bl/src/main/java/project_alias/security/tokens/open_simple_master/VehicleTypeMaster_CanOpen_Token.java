package project_alias.security.tokens.open_simple_master;

import static java.lang.String.format;

import project_alias.security.tokens.UsersAndPersonnelModuleToken;
import project_alias.vehicles.VehicleType;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link VehicleType} to guard Open.
 *
 * @author Developers
 *
 */
public class VehicleTypeMaster_CanOpen_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.MASTER_OPEN.forTitle(), VehicleType.ENTITY_TITLE + " Master");
    public final static String DESC = format(Template.MASTER_OPEN.forDesc(), VehicleType.ENTITY_TITLE);
}