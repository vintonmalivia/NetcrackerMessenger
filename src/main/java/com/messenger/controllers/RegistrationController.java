package com.messenger.controllers;

import com.messenger.models.User;
import com.messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

import static com.messenger.constants.controllers.Endpoints.REGISTRATION;

@Controller
public class RegistrationController {

    private static abstract class Views {
        private static final String REGISTRATION_HTML = "registration";
        private static final String REDIRECT = "redirect:/";
    }

    private static abstract class ModelAttributes{
        private static final String USER_FORM = "userForm";
        private static final String ATTRIBUTE_NAME_PASSWORD_ERROR = "passwordError";
        private static final String ATTRIBUTE_VALUE_PASSWORD_ERROR = "Password do not match!";
        private static final String ATTRIBUTE_NAME_USERNAME_ERROR = "usernameError";
        private static final String ATTRIBUTE_VALUE_USERNAME_ERROR = "A user with the same username already exists!";
    }

    @Autowired
    private UserService userService;

    @GetMapping(REGISTRATION)
    public String registration(Model model) {
        model.addAttribute(ModelAttributes.USER_FORM, new User());
        return Views.REGISTRATION_HTML;
    }

    @PostMapping(REGISTRATION)
    public String addUser(@ModelAttribute(ModelAttributes.USER_FORM) User userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return Views.REGISTRATION_HTML;
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute(ModelAttributes.ATTRIBUTE_NAME_PASSWORD_ERROR, ModelAttributes.ATTRIBUTE_VALUE_PASSWORD_ERROR);
            return Views.REGISTRATION_HTML;
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute(ModelAttributes.ATTRIBUTE_NAME_USERNAME_ERROR, ModelAttributes.ATTRIBUTE_VALUE_USERNAME_ERROR);
            return Views.REGISTRATION_HTML;
        }
        return Views.REDIRECT;
    }
}