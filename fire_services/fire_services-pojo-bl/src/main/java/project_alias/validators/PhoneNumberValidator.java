package project_alias.validators;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.entity.meta.impl.AbstractBeforeChangeEventHandler;
import ua.com.fielden.platform.error.Result;

public class PhoneNumberValidator extends AbstractBeforeChangeEventHandler<String> {

    public static final String ERR_INCORRECT_NUMBER_FORMAT = "Number should be in format +38 (098) 765 4321";

    @Override
    public Result handle(final MetaProperty<String> property, 
            final String newValue, final Set<Annotation> mutatorAnnotations) {
        if (newValue != null) {
            if (newValue.length() != 18) {
                return Result.failure(ERR_INCORRECT_NUMBER_FORMAT);
            }
            Pattern pattern = Pattern.compile("^(\\+38( )?)?((\\(0\\d{2}\\)))( )?\\d{3}( )?\\d{4}$");
            Matcher matcher = pattern.matcher(newValue);
            if (!matcher.matches()) {
            	return Result.failure(ERR_INCORRECT_NUMBER_FORMAT);
            }
        }
        return Result.successful(newValue);
    }
}
