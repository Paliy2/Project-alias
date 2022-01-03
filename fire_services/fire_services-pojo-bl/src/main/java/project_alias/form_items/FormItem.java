package project_alias.form_items;

import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.DynamicEntityKey;
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
@KeyTitle("Form Item")
@CompanionObject(FormItemCo.class)
@MapEntityTo
@DescTitle("Description")
@DisplayDescription
@EntityTitle(value = "Form Item", desc = "Form Item is used in forms.")
public class FormItem extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(FormItem.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    @IsProperty
    @MapTo
    @Required
    @CompositeKeyMember(1)
    @Title(value = "Form Type Item", desc = "Record from which form type item this form item is created.")
    private FormTypeItem formTypeItem ;

    @Observable
    public FormItem setFormTypeItem(final FormTypeItem formTypeItem ) {
        this.formTypeItem = formTypeItem;
        return this;
    }

    public FormTypeItem getFormTypeItem() {
        return formTypeItem ;
    }
    
    @IsProperty
    @MapTo
    @Title(value = "Accepted", desc = "Record acceptance status of this form item.")
    private boolean accepted ;

    @Observable
    public FormItem setAccepted(final boolean accepted ) {
        this.accepted = accepted;
        return this;
    }

    public boolean getAccepted() {
        return accepted ;
    }
    
    @Override
    @Observable
    protected FormItem setActive(boolean active) {
        super.setActive(active);
        return this;
    }
}
