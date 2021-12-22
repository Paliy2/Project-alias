package project_alias.equipments;

import ua.com.fielden.platform.entity.DynamicEntityKey;
import project_alias.validators.NoWhiteSpacesValidator;
import ua.com.fielden.platform.entity.AbstractEntity;
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
 * @author Project-alias team.
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Equipment Type")
@CompanionObject(EquipmentTypeCo.class)
@MapEntityTo
@DescTitle("Description")
@DisplayDescription
@DescRequired
@EntityTitle(value = "Equipment Type", desc = "Equipment types used for classification of equipment by types, and represent detailed classification of equipment classes.")
public class EquipmentType extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(EquipmentType.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty
    @MapTo
    @Title(value = "Title", desc = "A unique equipment type title.")
    @CompositeKeyMember(1)
    @BeforeChange({@Handler(NoWhiteSpacesValidator.class)})
    private String title;

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Equipment Class", desc = "An equipment class this equipment type belongs to.")
    private EquipmentClass equipmentClass;

    @Observable
    public EquipmentType setEquipmentClass(final EquipmentClass equipmentClass) {
        this.equipmentClass = equipmentClass;
        return this;
    }

    public EquipmentClass getEquipmentClass() {
        return equipmentClass;
    }

    @Observable
    public EquipmentType setTitle(final String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    @Override
    @Observable
    protected EquipmentType setActive(boolean active) {
        super.setActive(active);
        return this;
    }

    @Override
    @Observable
    public EquipmentType setDesc(String desc) {
        super.setDesc(desc);
        return this;
    }

}
