package com.example.oss.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.example.oss.model.User;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");
        
        
        if (user.getPassword() != null && user.getPassword().length() < 6) {
            errors.rejectValue("password", "password.minlength");
        }
    }
}

