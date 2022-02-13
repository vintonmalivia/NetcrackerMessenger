package com.messenger.controllers;
import com.messenger.models.Conversation;
import com.messenger.repository.ConversationManager;
import com.messenger.repository.IDatabaseMessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import static com.messenger.constants.Endpoints.MESSAGES;

@Controller
@RequestMapping(MESSAGES)
public class MessagesController
{
//    private final TextMessageDAO textMessageDAO;
//
//    @Autowired
//    public MessagesController(TextMessageDAO textMessageDAO){
//        this.textMessageDAO = textMessageDAO;
//    }
    private static final String CONVERSATIONS_PATH = "conversations/"; // TODO: Можно объединять константы для разных целей в отдельные вложенные статические классы (пример сразу после констант)
    private static final String MESSAGES = "conversationMessages";
    private static final String MESSAGES_HTML = "conversationMessages";
    private static final String UUID = "uuid";

    // Пример объявления вложенных статических классов констант
    /*
    private static abstract class Views {
        public static final String CONVERSATION_MESSAGES = "conversations/conversationMessages";
    }

    private static abstract class PathVariables {
        public static final String UUID = "uuid";
    }

    private static abstract class ModelAttributes {
        public static final String SOME_MODEL_ATTRIBUTE = "some_attribute";
    }
    */

    @Autowired
    private IDatabaseMessageDAO databaseMessageDAO;

    @GetMapping
    public String getMessagesFromConversationByID(@PathVariable(UUID) UUID uuid, Model model)
    {
//        List<Conversation> conversations = ConversationManager.getInstance().getConversations();
//        Conversation resultConversation = conversations.stream().filter(conversation -> conversation.getId().equals(uuid)).findAny().orElse(null);
//        model.addAttribute(MESSAGES, resultConversation);
//        model.addAttribute(MESSAGES, databaseMessageDAO.findAllById());
        return CONVERSATIONS_PATH + MESSAGES_HTML;
    }
}
