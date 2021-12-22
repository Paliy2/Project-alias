package project_alias.equipments;

import ua.com.fielden.platform.entity.DynamicEntityKey;
import project_alias.validators.NoWhiteSpacesValidator;
import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Required;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.mutator.BeforeChange;
import ua.com.fielden.platform.entity.annotation.mutator.Handler;
import ua.com.fielden.platform.entity.validation.annotation.Final;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.DisplayDescription;
import ua.com.fielden.platform.entity.annotation.EntityTitle;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.DescRequired;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 *
 * @author Project-alias Team.
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Equipment")
@CompanionObject(EquipmentCo.class)
@MapEntityTo
@DescTitle("Description")
@DisplayDescription
@DescRequired
@EntityTitle(value = "Equipment", desc = "Used for describing equipments.")
public class Equipment extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(Equipment.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty
    @MapTo
    @Title(value = "Number", desc = "A unique auto-generated equipment number.")
    @CompositeKeyMember(1)
    @Final
    private String number;

    @IsProperty
    @MapTo
    @Title(value = "Title", desc = "An equipment title.")
    @BeforeChange({@Handler(NoWhiteSpacesValidator.class)})
    private String title;

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Equipment Type", desc = "An equipment type this equipment belongs to.")
    private EquipmentType equipmentType;

    @IsProperty
    @MapTo
    @Title(value = "Vehicle", desc = "A vehicle this equipment belongs to.")
    private String vehicle;

    @Observable
    public Equipment setVehicle(final String vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public String getVehicle() {
        return vehicle;
    }

    @Observable
    public Equipment setEquipmentType(final EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
        return this;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    @Observable
    public Equipment setTitle(final String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    @Observable
    public Equipment setNumber(final String number) {
        this.number = number;
        return this;
    }

    public String getNumber() {
        return number;
    }

    @Override
    @Observable
    protected Equipment setActive(boolean active) {
        super.setActive(active);
        return this;
    }

    @Override
    @Observable
    public Equipment setDesc(String desc) {
        super.setDesc(desc);
        return this;
    }

}
