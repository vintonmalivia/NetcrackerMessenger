package com.messenger.controllers;

import com.messenger.models.Conversation;
import com.messenger.service.ConversationService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.messenger.constants.controllers.Endpoints.CONVERSATIONS;
import static com.messenger.constants.controllers.Endpoints.NEW;

@Slf4j
@Controller
@RequestMapping(CONVERSATIONS)
public class ConversationController
{
    private static abstract class Views {
        private static final String CONVERSATIONS_PATH = "conversations/";
        private static final String ALL_CONVERSATIONS_HTML = "allConversations";
        private static final String NEW_CONVERSATION_HTML = "newConversation";
        private static final String REDIRECT = "redirect:/";
    }

    private static abstract class ModelAttributes {
        private static final String ALL_CONVERSATIONS = "allConversations";
        private static final String NEW_CONVERSATION = "newConversation";
//        private static final String CONVERSATION = "conversation";
    }

    private static final Logger logger = LoggerFactory.getLogger(ConversationController.class);

    private final ConversationService conversationService;

    @Autowired
    public ConversationController(ConversationService conversationService)
    {
        this.conversationService = conversationService;
    }

    @GetMapping
    public String getConversations(Model model)
    {
        model.addAttribute(ModelAttributes.ALL_CONVERSATIONS, conversationService.getAllConversations());
        logger.trace("Conversations are opened.");
        return Views.CONVERSATIONS_PATH + Views.ALL_CONVERSATIONS_HTML;
    }

    @GetMapping(NEW)
    public String getPageForCreateNewConversation(
            @ModelAttribute(ModelAttributes.NEW_CONVERSATION) Conversation conversation)
    {
        logger.trace("Page for creating new conversation is opened.");
        return Views.CONVERSATIONS_PATH + Views.NEW_CONVERSATION_HTML;
    }

    @PostMapping
    public String createNewConversation(@ModelAttribute(ModelAttributes.NEW_CONVERSATION) Conversation conversation)
    {
        conversationService.createConversation(conversation);
        logger.info("New conversation with name = {} and ID = {} is created. Creator = {}.",
                conversation.getName(), conversation.getId(), conversation.getCreator());
        return Views.REDIRECT + Views.CONVERSATIONS_PATH;
    }

    //TODO: Doesn't work, needs to be fixed
    @PostMapping("/{uuid}" /* TODO: В константы */)
    public String deleteConversation(@PathVariable("uuid" /* TODO: В константы */) UUID uuid) {
        conversationService.deleteConversation(uuid);
        logger.info("Deleted.");
        logger.error("Not deleted.");
        return Views.REDIRECT + Views.CONVERSATIONS_PATH;
    }

}
