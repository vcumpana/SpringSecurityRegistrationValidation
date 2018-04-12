package com.springapp.mvc.constraints;

import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class EmailUniquenessValidator implements ConstraintValidator<EmailUniquenessConstraint, String> {

    @Autowired
    UserService service;

    @Override
    public void initialize(EmailUniquenessConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return service.mailIsPresentInDB(s);
    }
}
