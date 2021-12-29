package project_alias.vehicles.master.menu.actions;

import project_alias.vehicles.VehicleType;
import ua.com.fielden.platform.entity.AbstractFunctionalEntityForCompoundMenuItem;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.EntityTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object to model the detail menu item of the compound master entity object.
 *
 * @author Developers
 *
 */
@KeyType(VehicleType.class)
@CompanionObject(VehicleTypeMaster_OpenVehicle_MenuItemCo.class)
@EntityTitle("Vehicle Type Master Vehicle Menu Item")
public class VehicleTypeMaster_OpenVehicle_MenuItem extends AbstractFunctionalEntityForCompoundMenuItem<VehicleType> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(VehicleTypeMaster_OpenVehicle_MenuItem.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

}
