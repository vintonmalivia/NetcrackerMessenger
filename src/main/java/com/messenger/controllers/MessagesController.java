package com.messenger.controllers;
import com.messenger.repository.IDatabaseMessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.util.UUID;
import static com.messenger.constants.controllers.Endpoints.MESSAGES;

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

    // Пример объявления вложенных статических классов констант
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

    @Autowired
    private IDatabaseMessageDAO databaseMessageDAO;

    @GetMapping
    public String getMessagesFromConversationByID(@PathVariable(PathVariables.UUID) UUID uuid, Model model)
    {
//        List<Conversation> conversations = ConversationManager.getInstance().getConversations();
//        Conversation resultConversation = conversations.stream().filter(conversation -> conversation.getId().equals(uuid)).findAny().orElse(null);
//        model.addAttribute(MESSAGES, resultConversation);
        model.addAttribute(ModelAttributes.MESSAGES, databaseMessageDAO.getMessages(uuid));
        return Views.CONVERSATIONS_PATH + Views.MESSAGES_HTML;
    }
}
