package project_alias.security.tokens.open_compound_master;

import static java.lang.String.format;

import project_alias.security.tokens.UsersAndPersonnelModuleToken;
import project_alias.vehicles.ui_actions.OpenVehicleTypeMasterAction;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link OpenVehicleTypeMasterAction} to guard Open.
 *
 * @author Developers
 *
 */
public class OpenVehicleTypeMasterAction_CanOpen_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.MASTER_OPEN.forTitle(), OpenVehicleTypeMasterAction.ENTITY_TITLE);
    public final static String DESC = format(Template.MASTER_OPEN.forDesc(), OpenVehicleTypeMasterAction.ENTITY_TITLE);
}