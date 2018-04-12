package com.springapp.mvc.constraints;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

@Component
public class ExpressionAssertValidator implements ConstraintValidator<RePasswordConstraint, Object> {

//   public ExpressionAssertValidator(UserService userService){
//
//   }
    private String field;
    private String confirmingField;
    private String message;

   public void initialize(RePasswordConstraint constraint) {
      field=constraint.field();
      confirmingField=constraint.confirmingField();
      message=constraint.message();
   }

   public boolean isValid(Object obj, ConstraintValidatorContext context) {
       try {
           Class objClass = obj.getClass();
           System.out.println(objClass.getCanonicalName());
           Field firstField = objClass.getDeclaredField(field);
           firstField.setAccessible(true);
           Field secondField = objClass.getDeclaredField(confirmingField);
           secondField.setAccessible(true);
           Object firstFieldValue = firstField.get(obj);
           Object secondFieldValue = secondField.get(obj);

           boolean neitherSet = (firstFieldValue == null) && (secondFieldValue == null);

           if (neitherSet) {
               return true;
           }

           boolean matches = (firstFieldValue != null) && firstFieldValue.equals(secondFieldValue);

           if (!matches) {
               context.disableDefaultConstraintViolation();
               context.buildConstraintViolationWithTemplate(message)
                       .addPropertyNode(confirmingField)
                       .addConstraintViolation();
           }

           return matches;
       }catch (Exception e){
           e.printStackTrace();
       }
       return false;
   }
}
