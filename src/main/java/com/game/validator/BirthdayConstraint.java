package com.game.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BirthdayValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BirthdayConstraint {
    String message() default "Birthday must be between 2000 and 3000";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
