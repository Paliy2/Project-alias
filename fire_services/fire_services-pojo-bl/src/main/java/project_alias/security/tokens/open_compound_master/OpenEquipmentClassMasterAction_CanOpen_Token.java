package project_alias.security.tokens.open_compound_master;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import project_alias.equipments.ui_actions.OpenEquipmentClassMasterAction;
import project_alias.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link OpenEquipmentClassMasterAction} to guard Open.
 *
 * @author Developers
 *
 */
public class OpenEquipmentClassMasterAction_CanOpen_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.MASTER_OPEN.forTitle(), OpenEquipmentClassMasterAction.ENTITY_TITLE);
    public final static String DESC = format(Template.MASTER_OPEN.forDesc(), OpenEquipmentClassMasterAction.ENTITY_TITLE);
}