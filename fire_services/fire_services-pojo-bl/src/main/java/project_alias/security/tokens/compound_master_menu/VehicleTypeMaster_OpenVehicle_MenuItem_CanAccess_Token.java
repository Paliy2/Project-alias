package project_alias.security.tokens.compound_master_menu;

import static java.lang.String.format;

import project_alias.security.tokens.UsersAndPersonnelModuleToken;
import project_alias.vehicles.master.menu.actions.VehicleTypeMaster_OpenVehicle_MenuItem;
import ua.com.fielden.platform.security.tokens.Template;

/**
 * A security token for entity {@link VehicleTypeMaster_OpenVehicle_MenuItem} to guard Access.
 *
 * @author Developers
 *
 */
public class VehicleTypeMaster_OpenVehicle_MenuItem_CanAccess_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.MASTER_MENU_ITEM_ACCESS.forTitle(), VehicleTypeMaster_OpenVehicle_MenuItem.ENTITY_TITLE);
    public final static String DESC = format(Template.MASTER_MENU_ITEM_ACCESS.forDesc(), VehicleTypeMaster_OpenVehicle_MenuItem.ENTITY_TITLE);
}