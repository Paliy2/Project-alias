package project_alias.persons;

import ua.com.fielden.platform.entity.DynamicEntityKey;
import project_alias.validators.NoWhiteSpacesValidator;
import project_alias.validators.PhoneNumberValidator;
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
import ua.com.fielden.platform.property.validator.EmailValidator;
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
@KeyTitle("Person")
@CompanionObject(PersonCo.class)
@MapEntityTo
@DescTitle("Description")
@DisplayDescription
@EntityTitle(value = "Person", desc = "Used to represent persons in the fire service.")
public class Person extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(Person.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    @IsProperty(length = 20)
    @MapTo
    @Required
    @Title(value = "Name", desc = "First name of a person")
    @CompositeKeyMember(1)
    @BeforeChange({@Handler(NoWhiteSpacesValidator.class)})
    private String name;

    @Observable
    public Person setName(final String name) {
    	this.name = name;
    	return this;
    }

    public String getName() {
    	return name;
    }

    @IsProperty(length = 40)
    @MapTo
    @Required
    @Title(value = "Surname", desc = "Last name of a person")
    @CompositeKeyMember(2)
    @BeforeChange({@Handler(NoWhiteSpacesValidator.class)})
    private String surname;

    @Observable
    public Person setSurname(final String surname) {
    	this.surname = surname;
    	return this;
    }

    public String getSurname() {
    	return surname;
    }

    @IsProperty
    @MapTo
    @Title(value = "Phone number", desc = "Person's phone number")
    @BeforeChange(@Handler(PhoneNumberValidator.class))
    private String phoneNumber;

    @Observable
    public Person setPhoneNumber(final String phoneNumber) {
    	this.phoneNumber = phoneNumber;
    	return this;
    }

    public String getPhoneNumber() {
    	return phoneNumber;
    }

    @IsProperty
    @CompositeKeyMember(3)
    @MapTo
    @Required
    @Title(value = "Email", desc = "Persons's e-mail")
    @BeforeChange(@Handler(EmailValidator.class))
    private String email;
	
    @Observable
    public Person setEmail(final String email) {
        this.email = email;
        return this;
    }

    public String getEmail() {
        return email;
    }
}
