package com.messenger.controllers;

import com.messenger.models.Profile;
import com.messenger.service.ProfileService;
import com.messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        private static final String CURRENT_USER = "currentUser";
    }

    private static abstract class Redirects{
        private static final String REDIRECT_TO_PROFILE = "redirect:/profile/{uuid}";
    }

    private final ProfileService profileService;
    private final UserService userService;
    
    @Autowired
    public ProfileController(ProfileService profileService, UserService userService){
        this.profileService = profileService;
        this.userService = userService;
    }

    @GetMapping
    public String getProfile(@ModelAttribute(ModelAttributes.PROFILE) Profile profile,
                             @PathVariable(PathVariables.UUID) UUID uuid,
                             Model model)
    {
        model.addAttribute(ModelAttributes.PROFILE, profileService.getProfile(uuid));
        model.addAttribute(ModelAttributes.CURRENT_USER, userService.getCurrentUser().getProfile().getUserID());
        return Views.PROFILE_PATH + Views.PROFILE_HTML;
    }

    @PostMapping
    public String updateMyProfileName(@ModelAttribute(ModelAttributes.PROFILE) Profile profile,
                                      @PathVariable(PathVariables.UUID) UUID uuid,
                                      Model model)
    {
        model.addAttribute(ModelAttributes.CURRENT_USER, userService.getCurrentUser().getProfile().getUserID());
        profileService.updateProfileName(uuid, profile);
        return Redirects.REDIRECT_TO_PROFILE;
    }
}
