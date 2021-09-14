package org.helmut.profile.common.validation.constraints;

import org.helmut.profile.common.validation.CRUD;
import org.helmut.profile.common.validation.constraintvalidators.SectionValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = SectionValidator.class)
public @interface Section {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    CRUD method();
}
