package project_alias.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import project_alias.equipments.EquipmentClass;
import project_alias.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link EquipmentClass} to guard Save.
 *
 * @author Project-alias Team
 *
 */
public class EquipmentClass_CanSave_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.SAVE.forTitle(), EquipmentClass.ENTITY_TITLE);
    public final static String DESC = format(Template.SAVE.forDesc(), EquipmentClass.ENTITY_TITLE);
}