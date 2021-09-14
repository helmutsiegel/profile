package org.helmut.profile.common.validation.util;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Util class used by validations
 */
public class ValidationUtils<T> {

    /**
     * Converts the violations to a String
     */
    public String violationsToSting(Set<ConstraintViolation<T>> constraintViolations) {
        return constraintViolations
                .stream()
                .map(constraintViolation -> "# " + constraintViolation.getRootBeanClass().getSimpleName() +
                        "." + constraintViolation.getPropertyPath() +
                        " - Invalid Value = " + constraintViolation.getInvalidValue() +
                        " - Error message = " + constraintViolation.getMessage())
                .collect(Collectors.joining(","));
    }
}
