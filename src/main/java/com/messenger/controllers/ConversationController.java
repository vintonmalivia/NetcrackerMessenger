package com.messenger.controllers;
import com.messenger.models.Conversation;
import com.messenger.repository.IDatabaseConversationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import static com.messenger.constants.Endpoints.CONVERSATIONS;
import static com.messenger.constants.Endpoints.NEW;

@Controller
@RequestMapping(CONVERSATIONS)
public class ConversationController
{
    private static final String CONVERSATIONS_PATH = "conversations/";
    private static final String ALL_CONVERSATIONS = "allConversations";
    private static final String ALL_CONVERSATIONS_HTML = "allConversations";
    private static final String NEW_CONVERSATION = "newConversation";
    private static final String NEW_CONVERSATION_HTML = "newConversation";
    private static final String REDIRECT = "redirect:/";
    @Autowired
    private  IDatabaseConversationDAO databaseConversationDAO;

    @GetMapping
    public String getConversations(Model model)
    {
        model.addAttribute(ALL_CONVERSATIONS, /*ConversationManager.getInstance().getConversations()*/
                databaseConversationDAO.findAll());
        return CONVERSATIONS_PATH + ALL_CONVERSATIONS_HTML;
    }

    @GetMapping(NEW)
    public String getPageForCreateNewConversation(@ModelAttribute(NEW_CONVERSATION) Conversation conversation)
    {
        return CONVERSATIONS_PATH + NEW_CONVERSATION_HTML;
    }

    @PostMapping
    public String createNewConversation(@ModelAttribute(NEW_CONVERSATION) Conversation conversation)
    {
//        List<Conversation> conversations = ConversationManager.getInstance().getConversations();
//        conversation.setId(UUID.randomUUID());
//        conversations.add(conversation);
//        ConversationManager.getInstance().setConversations(conversations);
        databaseConversationDAO.save(conversation);
        //textMessageDAO.saveNewConversation(conversation);
        return REDIRECT + CONVERSATIONS_PATH;
    }
}
