package com.messenger.controllers;

import com.messenger.repository.IDatabaseProfileDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static com.messenger.constants.controllers.Endpoints.PROFILE;

@Controller
@RequestMapping(PROFILE)
public class ProfileController {

    private static abstract class Views {
        private static final String PROFILE_PATH = "profiles/";
        private static final String PROFILE_HTML = "profile";
    }

    private static abstract class PathVariables{
        private static final String UUID = "uuid";
    }

    private static abstract class ModelAttributes{
        private static final String PROFILE = "profile";
    }

    @Autowired // TODO: смотри подобные тудушки. Ставим над конструкторами, не над полями. + модификатор доступа должен быть явно указан
    private IDatabaseProfileDAO databaseProfileDAO; // TODO: Заменить на сервис. Смотри MessagesController. Там описано.

    @GetMapping
    public String getProfile(@PathVariable(PathVariables.UUID) UUID uuid, Model model)
    {
        model.addAttribute(ModelAttributes.PROFILE, databaseProfileDAO.findById(uuid));
        return Views.PROFILE_PATH + Views.PROFILE_HTML;
    }
}
