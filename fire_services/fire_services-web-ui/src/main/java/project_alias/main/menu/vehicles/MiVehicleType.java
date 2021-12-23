package project_alias.main.menu.vehicles;

import ua.com.fielden.platform.entity.annotation.EntityType;
import ua.com.fielden.platform.ui.menu.MiWithConfigurationSupport;
import project_alias.vehicles.VehicleType;
/**
 * Main menu item representing an entity centre for {@link VehicleType}.
 *
 * @author Developers
 *
 */
@EntityType(VehicleType.class)
public class MiVehicleType extends MiWithConfigurationSupport<VehicleType> {

}
