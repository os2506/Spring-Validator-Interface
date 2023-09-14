package com.example.oss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.oss.validator.UserValidator;
import com.example.oss.model.User;

@RestController
public class UserController {

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@RequestParam("username") String username, 
    		@RequestParam("password") String password, BindingResult result) {
    	
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        // Validate the user object
        userValidator.validate(user, result);

        if (result.hasErrors()) {
            // Validation failed, handle the errors
            return "registrationForm"; // Return to the registration form with error messages
        }

        // Registration successful, process the user data
        return "registrationSuccess"; // Redirect to a success page
    }
}

