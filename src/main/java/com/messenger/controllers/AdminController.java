package com.messenger.controllers;

import com.messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.UUID;
import static com.messenger.constants.controllers.Endpoints.ADMIN;
import static com.messenger.constants.controllers.Endpoints.ADMIN_GT_USER_ID;

@Controller
public class AdminController {

    private static abstract class Views {
        private static final String ADMIN = "admin";
        private static final String REDIRECT = "redirect:/";
    }

    private static abstract class ModelAttributes {
        private static final String ALL_USERS = "allUsers";
    }

    private static abstract class PathVariables {
        private static final String USER_ID = "userId";
    }

    private static abstract class ReqParam {
        private static final String DEFAULT_VALUE_UUID = "";
        private static final String DEFAULT_VALUE_STRING = "";
    }

    private static abstract class Actions {
        private static final String DELETE = "delete";
    }

    @Autowired // TODO: смотри подобные тудушки. Ставим над конструкторами, не над полями. + модификатор доступа должен быть явно указан
    private UserService userService;

    @GetMapping(ADMIN)
    public String getUserList(Model model) {
        model.addAttribute(ModelAttributes.ALL_USERS, userService.allUsers());
        return Views.ADMIN;
    }

    @PostMapping(ADMIN)
    public String deleteUser(@RequestParam(required = true, defaultValue = ReqParam.DEFAULT_VALUE_UUID) UUID userId,
                              @RequestParam(required = true, defaultValue = ReqParam.DEFAULT_VALUE_STRING) String action,
                              Model model) {
        if (action.equals(Actions.DELETE)){
            userService.deleteUser(userId);
        }
        return Views.REDIRECT + Views.ADMIN;
    }

    @GetMapping(ADMIN_GT_USER_ID)
    public String gtUser(@PathVariable(PathVariables.USER_ID) UUID userId, Model model) {
        model.addAttribute(ModelAttributes.ALL_USERS, userService.usergtList(userId));
        return Views.ADMIN;
    }
}