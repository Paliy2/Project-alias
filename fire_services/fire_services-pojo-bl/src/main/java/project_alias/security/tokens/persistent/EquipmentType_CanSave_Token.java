package project_alias.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import project_alias.equipments.EquipmentType;
import project_alias.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link EquipmentType} to guard Save.
 *
 * @author Developers
 *
 */
public class EquipmentType_CanSave_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.SAVE.forTitle(), EquipmentType.ENTITY_TITLE);
    public final static String DESC = format(Template.SAVE.forDesc(), EquipmentType.ENTITY_TITLE);
}