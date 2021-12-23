package project_alias.main.menu.equipments;

import ua.com.fielden.platform.entity.annotation.EntityType;
import ua.com.fielden.platform.ui.menu.MiWithConfigurationSupport;
import project_alias.equipments.Equipment;
/**
 * Main menu item representing an entity centre for {@link Equipment}.
 *
 * @author Developers
 *
 */
@EntityType(Equipment.class)
public class MiEquipment extends MiWithConfigurationSupport<Equipment> {

}
