package project_alias.validators;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.entity.meta.impl.AbstractBeforeChangeEventHandler;
import ua.com.fielden.platform.error.Result;

public class NoWhiteSpacesValidator extends AbstractBeforeChangeEventHandler<String> {

    public static final String ERR_CONTAINS_WHITESPACES = "Value should not contain any whitespaces. Please remove them.";

    @Override
    public Result handle(final MetaProperty<String> property, final String newValue, final Set<Annotation> mutatorAnnotations) {
        if (newValue != null && (newValue.contains("  ") || newValue.startsWith(" ") || newValue.endsWith(" "))) {
            return Result.failure(ERR_CONTAINS_WHITESPACES);
        }
        return Result.successful(newValue);
    }

}
