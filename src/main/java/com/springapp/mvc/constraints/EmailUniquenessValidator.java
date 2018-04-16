package com.springapp.mvc.constraints;

import com.springapp.mvc.service.UserService;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
public class EmailUniquenessValidator implements ConstraintValidator<EmailUniquenessConstraint, String> {

    //@Autowired
    UserService service;

    @Override
    public void initialize(EmailUniquenessConstraint constraintAnnotation) {
        service= new UserService();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return service.mailIsPresentInDB(s, 1);
    }
}
