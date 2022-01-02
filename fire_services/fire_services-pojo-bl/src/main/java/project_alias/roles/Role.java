    package project_alias.roles;

import ua.com.fielden.platform.entity.DynamicEntityKey;
import project_alias.equipments.Equipment;
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
 * @author Project-alias team
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Role")
@CompanionObject(RoleCo.class)
@MapEntityTo
@DescTitle("Description")
@DisplayDescription
@DescRequired
@EntityTitle(value = "Role", desc = "Used to represent role of a person")
public class Role extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(Role.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    @IsProperty
    @MapTo
    @Required
    @Title(value = "Title", desc = "A unique role title.")
    @CompositeKeyMember(1)
    @BeforeChange({@Handler(NoWhiteSpacesValidator.class)})
    private String title;
    
    @Observable
    public Role setTitle(final String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }
    
    @Override
    @Observable
    public Role setDesc(String desc) {
        super.setDesc(desc);
        return this;
    }
    
    @Override
    @Observable
    protected Role setActive(boolean active) {
        super.setActive(active);
        return this;
    }
}
