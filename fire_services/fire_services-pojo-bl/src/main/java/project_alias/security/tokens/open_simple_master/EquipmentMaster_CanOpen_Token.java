package project_alias.security.tokens.open_simple_master;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import project_alias.equipments.Equipment;
import project_alias.security.tokens.UsersAndPersonnelModuleToken;

/**
 * A security token for entity {@link Equipment} to guard Open.
 *
 * @author Developers
 *
 */
public class EquipmentMaster_CanOpen_Token extends UsersAndPersonnelModuleToken {
    public final static String TITLE = format(Template.MASTER_OPEN.forTitle(), Equipment.ENTITY_TITLE + " Master");
    public final static String DESC = format(Template.MASTER_OPEN.forDesc(), Equipment.ENTITY_TITLE);
}