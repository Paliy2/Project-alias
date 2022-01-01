package project_alias.forms;

import ua.com.fielden.platform.entity.DynamicEntityKey;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import project_alias.equipments.Equipment;
import project_alias.form_items.FormTypeItem;
import project_alias.roles.Role;
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
 * @author Developers
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Form Type")
@CompanionObject(FormTypeCo.class)
@MapEntityTo
@DescTitle("Description")
@DisplayDescription
@EntityTitle(value = "Form Type", desc = "Used to make different templates for forms.")
public class FormType extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(FormType.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    @IsProperty
    @MapTo
    @Required
    @CompositeKeyMember(1)
    @BeforeChange(@Handler(NoWhiteSpacesValidator.class))
    @Title(value = "Title", desc = "Record a unique form type title")
    private String title;

    @Observable
    public FormType setTitle(final String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }
    
    @IsProperty
    @MapTo
//    @Required
    @Title(value = "Assigned Role", desc = "Record a role this form type belongs to.")
    private Role assignedRole;

    @Observable
    public FormType setAssignedRole(final Role assignedRole) {
        this.assignedRole = assignedRole;
        return this;
    }

    public Role getAssignedRole() {
        return assignedRole;
    }    
    
//    @IsProperty(FormTypeItem.class)
//    @Required
//    @Title(value = "Form Type Items", desc = "Keep track of form items necessary for checking in this specific form type.")
//    private Set<FormTypeItem> formTypeItems = new HashSet<>();
//
//    @Observable
//    protected FormType setFormTypeItems (final Set<FormTypeItem> formTypeItems) {
//        this.formTypeItems.clear();
//        this.formTypeItems.addAll(formTypeItems);
//        return this;
//    }
//
//    public Set<FormTypeItem> getFormTypeItems () {
//        return Collections.unmodifiableSet(formTypeItems);
//    }
    
//    @Override
//    @Observable
//    protected FormType setActive(boolean active) {
//        super.setActive(active);
//        return this;
//    }

    @Override
    @Observable
    public FormType setDesc(String desc) {
        super.setDesc(desc);
        return this;
    }

    @IsProperty
    @MapTo
    @Required
    @Title(value = "Form Class", desc = "Associated Form Class")
    private FormClass formClass;

    @Observable
    public FormType setFormClass(final FormClass formClass) {
        this.formClass = formClass;
        return this;
    }
    
    public FormClass getFormClass() {
        return formClass;
    }
}
