package project_alias.forms;

import ua.com.fielden.platform.entity.NoKey;
import static ua.com.fielden.platform.entity.NoKey.NO_KEY;
import project_alias.forms.FormClass;
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
@CompanionObject(FormClassLocatorCo.class)
public class FormClassLocator extends AbstractFunctionalEntityWithCentreContext<NoKey> {

    public FormClassLocator() {
        setKey(NO_KEY);
    }

    @IsProperty
    @SkipEntityExistsValidation(skipActiveOnly = true)
    private FormClass formClass;

    @Observable
    public FormClassLocator setFormClass(final FormClass value) {
        this.formClass = value;
        return this;
    }

    public FormClass getFormClass() {
        return formClass;
    }

}
