package com.messenger.controllers;

import com.messenger.models.Conversation;
import com.messenger.service.ConversationService;
import com.messenger.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.messenger.constants.controllers.Endpoints.*;

@Slf4j
@Controller
@RequestMapping(CONVERSATIONS)
public class ConversationsController
{
    private static abstract class Views {
        private static final String CONVERSATIONS_PATH = "conversations/";
        private static final String ALL_CONVERSATIONS_HTML = "allConversations";
        private static final String NEW_CONVERSATION_HTML = "newConversation";
    }

    private static abstract class ModelAttributes {
        private static final String ALL_CONVERSATIONS = "allConversations";
        private static final String NEW_CONVERSATION = "newConversation";
    }

    private static abstract class PathVariables {
        private static final String CONVERSATION_ID = "id";
    }

    private static abstract class Redirects {
        private static final String REDIRECT_TO_CONVERSATIONS = "redirect:/conversations";
    }

    private static final Logger logger = LoggerFactory.getLogger(ConversationsController.class);

    private final ConversationService conversationService;
    private final UserService userService;

    @Autowired
    public ConversationsController(ConversationService conversationService, UserService userService)
    {
        this.conversationService = conversationService;
        this.userService = userService;
    }

    @GetMapping
    public String showConversations(Model model)
    {
        model.addAttribute(ModelAttributes.ALL_CONVERSATIONS,
                conversationService.getAllConversations(userService.getCurrentUser().getProfile().getUserID()));
        logger.trace("Conversations are opened.");
        return Views.CONVERSATIONS_PATH + Views.ALL_CONVERSATIONS_HTML;
    }

    @GetMapping(NEW)
    public String showConversationCreationPage(
            @ModelAttribute(ModelAttributes.NEW_CONVERSATION) Conversation conversation)
    {
        logger.trace("Page for creating new conversation is opened.");
        return Views.CONVERSATIONS_PATH + Views.NEW_CONVERSATION_HTML;
    }

    @PostMapping
    public String createConversation(@ModelAttribute(ModelAttributes.NEW_CONVERSATION) Conversation conversation)
    {
        conversationService.createConversation(conversation);
        logger.info("New conversation with name = {} and ID = {} is created. Creator = {} {} with profile ID = {}.",
                conversation.getName(), conversation.getId(), conversation.getCreator().getName(),
                conversation.getCreator().getSurname(), conversation.getCreator().getUserID());
        return Redirects.REDIRECT_TO_CONVERSATIONS;
    }

    @PostMapping(CONVERSATION_ID)
    public String deleteConversationById(@PathVariable(PathVariables.CONVERSATION_ID) UUID id) {
        conversationService.deleteConversation(id);
        logger.info("Deleted conversation with ID = {}.", id);
        return Redirects.REDIRECT_TO_CONVERSATIONS;
    }

    @PostMapping(LEAVE_CONVERSATION)
    public String deleteCurrentUserFromConversationByConversationId(@PathVariable(PathVariables.CONVERSATION_ID) UUID id) {
        UUID profileID = userService.getCurrentUser().getProfile().getUserID();
        conversationService.deleteCurrentUserFromConversationByConversationId(profileID, id);
        logger.info("Profile with ID = {} was deleted from conversation with ID = {}.", profileID, id);
        return Redirects.REDIRECT_TO_CONVERSATIONS;
    }
}
