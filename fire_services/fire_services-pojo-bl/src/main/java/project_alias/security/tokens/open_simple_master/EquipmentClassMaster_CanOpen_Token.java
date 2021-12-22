package project_alias.security.tokens.open_simple_master;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import project_alias.equipments.EquipmentClass;
import project_alias.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link EquipmentClass} to guard Open.
 *
 * @author Project-alias Team
 *
 */
public class EquipmentClassMaster_CanOpen_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.MASTER_OPEN.forTitle(), EquipmentClass.ENTITY_TITLE + " Master");
    public final static String DESC = format(Template.MASTER_OPEN.forDesc(), EquipmentClass.ENTITY_TITLE);
}