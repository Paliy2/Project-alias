package project_alias.vehicles;

import project_alias.equipments.EquipmentClass;
import project_alias.validators.NoWhiteSpacesValidator;
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
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.mutator.BeforeChange;
import ua.com.fielden.platform.entity.annotation.mutator.Handler;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * An entity for classification of vehicles
 *
 * @author Developers
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Vehicle Type")
@CompanionObject(VehicleTypeCo.class)
@MapEntityTo
@DescTitle("Description")
@DisplayDescription
@DescRequired
@EntityTitle(value = "Vehicle Type", desc = "Used for classification of vehicles.")
public class VehicleType extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(VehicleType.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    @IsProperty
	@MapTo
	@Title(value = "Title", desc = "A unique vehicle type name")
	@CompositeKeyMember(1)
    @BeforeChange(@Handler(NoWhiteSpacesValidator.class))
	private String title;

	@Observable
	public VehicleType setTitle(final String title) {
		this.title = title;
		return this;
	}

	public String getTitle() {
		return title;
	}
	
	@Override
    @Observable
    protected VehicleType setActive(boolean active) {
        super.setActive(active);
        return this;
    }
}
