package org.helmut.profile.common.validation.constraints;

import org.helmut.profile.common.validation.constraintvalidators.URLValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {URLValidator.class})
public @interface URL {

    String message() default "{org.helmut.profile.common.validation.constraints.URL.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String protocol() default "";

    String host() default "";

    int port() default -1;
}
