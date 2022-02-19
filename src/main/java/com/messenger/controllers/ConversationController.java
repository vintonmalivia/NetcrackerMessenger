package com.messenger.controllers;
import com.messenger.models.Conversation;
import com.messenger.repository.IDatabaseConversationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import static com.messenger.constants.controllers.Endpoints.CONVERSATIONS;
import static com.messenger.constants.controllers.Endpoints.NEW;

@Controller
@RequestMapping(CONVERSATIONS)
public class ConversationController
{
//    private static final String CONVERSATIONS_PATH = "conversations/";
//    private static final String ALL_CONVERSATIONS = "allConversations";
//    private static final String ALL_CONVERSATIONS_HTML = "allConversations";
//    private static final String NEW_CONVERSATION = "newConversation";
//    private static final String NEW_CONVERSATION_HTML = "newConversation";
//    private static final String REDIRECT = "redirect:/";

    // Пример объявления вложенных статических классов констант
    private static abstract class Views {
        private static final String CONVERSATIONS_PATH = "conversations/";
        private static final String ALL_CONVERSATIONS_HTML = "allConversations";
        private static final String NEW_CONVERSATION_HTML = "newConversation";
        private static final String REDIRECT = "redirect:/";
    }

    private static abstract class ModelAttributes {
        private static final String ALL_CONVERSATIONS = "allConversations";
        private static final String NEW_CONVERSATION = "newConversation";
        private static final String CONVERSATION = "com/messenger/exceptions/conversation";
    }

    @Autowired
    private  IDatabaseConversationDAO databaseConversationDAO;

    @GetMapping
    public String getConversations(Model model)
    {
        model.addAttribute(ModelAttributes.ALL_CONVERSATIONS, /*ConversationManager.getInstance().getConversations()*/
                databaseConversationDAO.findAll());
        return Views.CONVERSATIONS_PATH + Views.ALL_CONVERSATIONS_HTML;
    }

    @GetMapping(NEW)
    public String getPageForCreateNewConversation(@ModelAttribute(ModelAttributes.NEW_CONVERSATION) Conversation conversation)
    {
        return Views.CONVERSATIONS_PATH + Views.NEW_CONVERSATION_HTML;
    }

    @PostMapping
    public String createNewConversation(@ModelAttribute(ModelAttributes.NEW_CONVERSATION) Conversation conversation)
    {
//        List<Conversation> conversations = ConversationManager.getInstance().getConversations();
//        conversation.setId(UUID.randomUUID());
//        conversations.add(conversation);
//        ConversationManager.getInstance().setConversations(conversations);
//        textMessageDAO.saveNewConversation(conversation);
        databaseConversationDAO.save(conversation);
        return Views.REDIRECT + Views.CONVERSATIONS_PATH;
    }

    @DeleteMapping
    public String deleteConversation(@ModelAttribute(ModelAttributes.CONVERSATION) Conversation conversation)
    {
        databaseConversationDAO.delete(conversation);
        return Views.REDIRECT + Views.CONVERSATIONS_PATH;
    }

}
