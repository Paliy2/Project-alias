package project_alias.vehicles.ui_actions;

import project_alias.vehicles.VehicleType;
import project_alias.vehicles.Vehicle;
import ua.com.fielden.platform.entity.AbstractFunctionalEntityToOpenCompoundMaster;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.EntityTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Open Master Action entity object.
 *
 * @author Developers
 *
 */
@KeyType(VehicleType.class)
@CompanionObject(OpenVehicleTypeMasterActionCo.class)
@EntityTitle("Vehicle Type Master")
public class OpenVehicleTypeMasterAction extends AbstractFunctionalEntityToOpenCompoundMaster<VehicleType> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(OpenVehicleTypeMasterAction.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    public static final String MAIN = "Main";
    public static final String VEHICLES = Vehicle.ENTITY_TITLE + "s"; // Please adjust manually if the plural form is not standard
}
