package project_alias.persons;

import ua.com.fielden.platform.entity.DynamicEntityKey;

import java.util.Date;

import project_alias.roles.Role;
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
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.DisplayDescription;
import ua.com.fielden.platform.entity.annotation.EntityTitle;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 *
 * @author Project-alias team
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Person Role")
@CompanionObject(PersonRoleCo.class)
@MapEntityTo
@DescTitle("Description")
@DisplayDescription
@EntityTitle(value = "Person Role", desc = "Used to represent role the person had at the specific date.")
public class PersonRole extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(PersonRole.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty
    @MapTo
    @Title(value = "Person", desc = "Person which the assignedRole was assigned to at assignedDate")
    @CompositeKeyMember(1)
    private Person person;

    @Observable
    public PersonRole setPerson(final Person person) {
    	this.person = person;
    	return this;
    }

    public Person getPerson() {
    	return person;
    }

    @IsProperty
    @MapTo
    @Title(value = "Assigned role", desc = "Role the person was assigned at specific date")
    @Required
    private Role assignedRole;

    @Observable
    public PersonRole setAssignedRole(final Role assignedRole) {
    	this.assignedRole = assignedRole;
    	return this;
    }

    public Role getAssignedRole() {
    	return assignedRole;
    }

    @IsProperty
    @MapTo
    @Title(value = "Assigned date", desc = "Date when the role was assigned to a person")
    @CompositeKeyMember(2)
    private Date assignedDate;

    @Observable
    public PersonRole setAssignedDate(final Date assignedDate) {
    	this.assignedDate = assignedDate;
    	return this;
    }

    public Date getAssignedDate() {
    	return assignedDate;
    }
}
