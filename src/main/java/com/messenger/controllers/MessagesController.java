package com.messenger.controllers;

import com.messenger.models.Conversation;
import com.messenger.models.impl.TextMessage;
import com.messenger.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    private static final Logger logger = LoggerFactory.getLogger(MessagesController.class);

    private final MessageService messageService;

    @Autowired
    public MessagesController(MessageService messageService)
    {
        this.messageService=messageService;
    }

    @GetMapping
    public String getMessagesFromConversationByID(@PathVariable(PathVariables.UUID) UUID uuid,
                                                  @ModelAttribute("newMessage") TextMessage textMessage,
                                                  Model model)
    {
        model.addAttribute(ModelAttributes.MESSAGES, messageService.getTextMessages(uuid));
        return Views.CONVERSATIONS_PATH + Views.MESSAGES_HTML;
    }

    @PostMapping
    public String createNewMessage(@PathVariable(PathVariables.UUID) UUID uuid,
                                   @ModelAttribute("newMessage") TextMessage textMessage,
                                   Model model)
    {
        messageService.create(textMessage, uuid);
        model.addAttribute(ModelAttributes.MESSAGES, messageService.getTextMessages(uuid));
        return Views.CONVERSATIONS_PATH + Views.MESSAGES_HTML;
    }
}
