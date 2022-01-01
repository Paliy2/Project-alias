package project_alias.forms;

import java.util.Date;

import project_alias.persons.Person;
import project_alias.roles.Role;
import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.DynamicEntityKey;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.DisplayDescription;
import ua.com.fielden.platform.entity.annotation.EntityTitle;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Required;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 *
 * @author Projet-alias team
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Form Class")
@CompanionObject(FormClassCo.class)
@MapEntityTo
@DescTitle("Description")
@DisplayDescription
@EntityTitle(value = "Form Class", desc = "Used to make different templates for forms.")
public class FormClass extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(FormClass.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    @IsProperty
    @MapTo
    @Required
    @CompositeKeyMember(1)
    @Title(value = "Person", desc = "Form owner.")
    private Person person;

    @Observable
    public FormClass setPerson(final Person person) {
        this.person = person;
        return this;
    }

    public Person getPerson() {
        return person;
    }    
    
    @IsProperty
    @MapTo
    @CompositeKeyMember(2)
    @Title(value = "Date", desc = "Datetime the form was created.")
    private Date date;

    @Observable
    public FormClass setDate(final Date date) {
        this.date = date;
        return this;
    }

    public Date getDate() {
        return date;
    }    

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Status", desc = "Record form owner.")
    private Status status;

    @Observable
    public FormClass setStatus(final Status status) {
        this.status = status;
        return this;
    }

    public Status getStatus() {
        return status;
    }    
    
//    @IsProperty
//    @MapTo
//    @Required
//    @Title(value = "Form Type", desc = "Record the form type.")
//    private FormType formType;
//
//    @Observable
//    public FormClass setFormType(final FormType formType) {
//        this.formType = formType;
//        return this;
//    }
//
//    public FormType getFormType() {
//        return formType;
//    }   
//    
    
}
