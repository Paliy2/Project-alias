package project_alias.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import project_alias.equipments.Equipment;
import project_alias.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link Equipment} to guard Delete.
 *
 * @author Developers
 *
 */
public class Equipment_CanDelete_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.DELETE.forTitle(), Equipment.ENTITY_TITLE);
    public final static String DESC = format(Template.DELETE.forDesc(), Equipment.ENTITY_TITLE);
}