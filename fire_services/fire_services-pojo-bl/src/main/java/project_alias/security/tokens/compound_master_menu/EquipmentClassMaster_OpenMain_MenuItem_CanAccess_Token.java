package project_alias.security.tokens.compound_master_menu;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import project_alias.equipments.master.menu.actions.EquipmentClassMaster_OpenMain_MenuItem;
import project_alias.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link EquipmentClassMaster_OpenMain_MenuItem} to guard Access.
 *
 * @author Developers
 *
 */
public class EquipmentClassMaster_OpenMain_MenuItem_CanAccess_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.MASTER_MENU_ITEM_ACCESS.forTitle(), EquipmentClassMaster_OpenMain_MenuItem.ENTITY_TITLE);
    public final static String DESC = format(Template.MASTER_MENU_ITEM_ACCESS.forDesc(), EquipmentClassMaster_OpenMain_MenuItem.ENTITY_TITLE);
}