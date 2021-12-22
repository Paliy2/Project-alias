package project_alias.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import project_alias.equipments.EquipmentType;
import project_alias.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link EquipmentType} to guard Delete.
 *
 * @author Developers
 *
 */
public class EquipmentType_CanDelete_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.DELETE.forTitle(), EquipmentType.ENTITY_TITLE);
    public final static String DESC = format(Template.DELETE.forDesc(), EquipmentType.ENTITY_TITLE);
}