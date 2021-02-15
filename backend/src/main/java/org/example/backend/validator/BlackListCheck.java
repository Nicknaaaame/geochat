package org.example.backend.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = BlackListCheckValidator.class)
public @interface BlackListCheck {
    String message() default "In black list";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
