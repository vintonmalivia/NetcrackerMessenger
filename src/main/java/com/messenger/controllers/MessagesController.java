package com.messenger.controllers;

import com.messenger.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static com.messenger.constants.controllers.Endpoints.MESSAGES;

@Controller
@RequestMapping(MESSAGES)
public class MessagesController
{
    private static abstract class Views {
        private static final String CONVERSATIONS_PATH = "conversations/";
        private static final String MESSAGES_HTML = "conversationMessages";
    }

    private static abstract class PathVariables {
        public static final String UUID = "uuid";
    }

    private static abstract class ModelAttributes {
        private static final String MESSAGES = "conversationMessages";
    }

    private final MessageService messageService;

    @Autowired
    public MessagesController(MessageService messageService)
    {
        this.messageService=messageService;
    }

    @GetMapping
    public String getMessagesFromConversationByID(@PathVariable(PathVariables.UUID) UUID uuid, Model model)
    {
        model.addAttribute(ModelAttributes.MESSAGES, messageService.getTextMessages(uuid));
        return Views.CONVERSATIONS_PATH + Views.MESSAGES_HTML;
    }
}
