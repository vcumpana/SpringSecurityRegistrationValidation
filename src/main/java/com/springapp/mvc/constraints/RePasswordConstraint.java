package com.springapp.mvc.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Constraint(validatedBy = ExpressionAssertValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RePasswordConstraint {
    String message() default "expression must evaluate to true";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String field();
    String confirmingField();
}
