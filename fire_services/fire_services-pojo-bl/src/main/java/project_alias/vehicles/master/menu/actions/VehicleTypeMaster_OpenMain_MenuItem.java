package project_alias.vehicles.master.menu.actions;

import project_alias.vehicles.VehicleType;
import ua.com.fielden.platform.entity.AbstractFunctionalEntityForCompoundMenuItem;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.EntityTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object to model the main menu item of the compound master entity object.
 *
 * @author Developers
 *
 */
@KeyType(VehicleType.class)
@CompanionObject(VehicleTypeMaster_OpenMain_MenuItemCo.class)
@EntityTitle("Vehicle Type Master Main Menu Item")
public class VehicleTypeMaster_OpenMain_MenuItem extends AbstractFunctionalEntityForCompoundMenuItem<VehicleType> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(VehicleTypeMaster_OpenMain_MenuItem.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

}
