package com.messenger.controllers;

import com.messenger.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

import static com.messenger.constants.controllers.Endpoints.ADMIN;

@Controller
public class AdminController {

    private static abstract class Views {
        private static final String ADMIN = "admin";
        private static final String REDIRECT = "redirect:/";
    }

    private static abstract class ModelAttributes {
        private static final String ALL_USERS = "allUsers";
    }

    private static abstract class ReqParam {
        private static final String DEFAULT_VALUE_UUID = "";
        private static final String DEFAULT_VALUE_STRING = "";
    }

    private static abstract class Actions {
        private static final String DELETE = "delete";
    }

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(ADMIN)
    public String showUserList(Model model) {
        model.addAttribute(ModelAttributes.ALL_USERS, userService.getAllUsers());
        logger.trace("Opened page with all users information.");
        return Views.ADMIN;
    }

    @PostMapping(ADMIN)
    public String deleteUserById(@RequestParam(defaultValue = ReqParam.DEFAULT_VALUE_UUID) UUID userId,
                                 @RequestParam(defaultValue = ReqParam.DEFAULT_VALUE_STRING) String action) {
        if (action.equals(Actions.DELETE)){
            userService.deleteUser(userId);
            logger.info("User with ID = {} deleted.", userId);
        }
        return Views.REDIRECT + Views.ADMIN;
    }
}