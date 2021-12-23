package project_alias.vehicles;

import project_alias.validators.VehicleNumberValidator;
import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.DynamicEntityKey;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.DescRequired;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.DisplayDescription;
import ua.com.fielden.platform.entity.annotation.EntityTitle;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Required;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.mutator.BeforeChange;
import ua.com.fielden.platform.entity.annotation.mutator.Handler;
import ua.com.fielden.platform.entity.validation.MaxLengthValidator;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * An entity to keep track of vehicles in the fire station
 *
 * @author Developers
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Vehicle")
@CompanionObject(VehicleCo.class)
@MapEntityTo
@DescTitle("Description")
@DisplayDescription
@EntityTitle(value = "Vehicle", desc = "Used for keeping track of vehicles in the fire station")
public class Vehicle extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(Vehicle.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty
    @MapTo
    @Title(value = "Number", desc = "A unique vehicle number")
    @CompositeKeyMember(1)
    @BeforeChange(@Handler(VehicleNumberValidator.class))
    private String number;

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Model", desc = "The model of the vehicle")
    private String model;

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Vehicle Type", desc = "The type of this vehicle")
    private VehicleType vehicleType;

    @IsProperty
    @MapTo
    @Title(value = "Assigned Driver", desc = "The driver who is assigned to this vehicle currently")
    private String assignedDriver;

    @Observable
    public Vehicle setNumber(final String number) {
        this.number = number;
        return this;
    }

    public String getNumber() {
        return number;
    }

    @Observable
    public Vehicle setModel(final String model) {
        this.model = model;
        return this;
    }

    public String getModel() {
        return model;
    }

    @Observable
    public Vehicle setVehicleType(final VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Observable
    public Vehicle setAssignedDriver(final String assignedDriver) {
        this.assignedDriver = assignedDriver;
        return this;
    }

    public String getAssignedDriver() {
        return assignedDriver;
    }

    @Override
    @Observable
    protected Vehicle setActive(boolean active) {
        super.setActive(active);
        return this;
    }
}
