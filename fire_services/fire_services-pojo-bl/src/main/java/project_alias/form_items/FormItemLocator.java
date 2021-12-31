package project_alias.form_items;

import ua.com.fielden.platform.entity.NoKey;
import static ua.com.fielden.platform.entity.NoKey.NO_KEY;
import project_alias.form_items.FormItem;
import ua.com.fielden.platform.entity.AbstractFunctionalEntityWithCentreContext;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.SkipEntityExistsValidation;

/**
 * Locator entity object.
 *
 * @author Developers
 *
 */
@KeyType(NoKey.class)
@CompanionObject(FormItemLocatorCo.class)
public class FormItemLocator extends AbstractFunctionalEntityWithCentreContext<NoKey> {

    public FormItemLocator() {
        setKey(NO_KEY);
    }

    @IsProperty
    @SkipEntityExistsValidation(skipActiveOnly = true)
    private FormItem formItem;

    @Observable
    public FormItemLocator setFormItem(final FormItem value) {
        this.formItem = value;
        return this;
    }

    public FormItem getFormItem() {
        return formItem;
    }

}
