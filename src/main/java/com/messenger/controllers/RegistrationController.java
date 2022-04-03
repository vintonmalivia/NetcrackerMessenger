package com.messenger.controllers;

import com.messenger.models.Profile;
import com.messenger.models.User;
import com.messenger.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(REGISTRATION)
    public String registration(Model model) {
        model.addAttribute(ModelAttributes.USER_FORM, new User());
        logger.info("New user registered.");
        return Views.REGISTRATION_HTML;
    }

    @PostMapping(REGISTRATION)
    public String addUser(@ModelAttribute(ModelAttributes.USER_FORM) User userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return Views.REGISTRATION_HTML; // TODO: чуть больше бизнес-логики. Что за ошибки? Как пользователь поймет, что он сделал не так?
                                            //  Можно добавлять сообщение в модель и выводить его, если оно есть (либо пустую строку, если ошибок нет). Это просто пример реализации
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute(
                    ModelAttributes.ATTRIBUTE_NAME_PASSWORD_ERROR,
                    ModelAttributes.ATTRIBUTE_VALUE_PASSWORD_ERROR);
            logger.info("Password do not match.");
            return Views.REGISTRATION_HTML;
        }

        if (!userService.saveUser(userForm)){
            model.addAttribute(
                    ModelAttributes.ATTRIBUTE_NAME_USERNAME_ERROR,
                    ModelAttributes.ATTRIBUTE_VALUE_USERNAME_ERROR);
            logger.info("User with the same username already exists.");
            return Views.REGISTRATION_HTML;
        }

        return Views.REDIRECT;
    }
}