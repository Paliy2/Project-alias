package project_alias.validators;

import java.lang.annotation.Annotation;
import java.util.Set;

import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.entity.meta.impl.AbstractBeforeChangeEventHandler;
import ua.com.fielden.platform.error.Result;

public class VehicleNumberValidator extends AbstractBeforeChangeEventHandler<String> {

    public static final String ERR_INCORRECT_NUMBER_FORMAT = "Number should be in format AA9999AA";

    @Override
    public Result handle(final MetaProperty<String> property, 
            final String newValue, final Set<Annotation> mutatorAnnotations) {
        if (newValue != null) {
            if (newValue.length() != 8) {
                return Result.failure(ERR_INCORRECT_NUMBER_FORMAT);
            }
            int[] letter_indexes = {0, 1, 6, 7};
            String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            for (int i : letter_indexes) {
                if (letters.indexOf(newValue.charAt(i)) == -1) {
                    return Result.failure(ERR_INCORRECT_NUMBER_FORMAT);
                }
            }
            int[] number_indexes = {2, 3, 4, 5};
            String numbers = "0123456789";
            for (int j : number_indexes) {
                if (numbers.indexOf(newValue.charAt(j)) == -1) {
                    return Result.failure(ERR_INCORRECT_NUMBER_FORMAT);
                }
            }
        }
        return Result.successful(newValue);
    }
}
