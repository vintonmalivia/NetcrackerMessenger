package com.messenger.controllers;
import com.messenger.models.Conversation;
import com.messenger.repository.ConversationManager;
import com.messenger.repository.TextMessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/conversations") // TODO: (normal) Думаю, это нужно вынести в константы. Давай постараемся по-максимуму избавиться от "магических" строк
public class ConversationController
{
//    private final TextMessageDAO textMessageDAO;

//    @Autowired
//    public ConversationController(TextMessageDAO textMessageDAO){
//        this.textMessageDAO = textMessageDAO;
//    }
    private static final String CONVERSATIONS = "conversations/";
    private static final String ALL_CONVERSATIONS = "allConversations";
    private static final String NEW_CONVERSATION = "newConversation";
    private static final String REDIRECT = "redirect:/";
    private static final String NEW = "/new";

    @GetMapping
    public String getConversations(Model model)
    {
        model.addAttribute(ALL_CONVERSATIONS,  ConversationManager.getInstance().getConversations());
        return CONVERSATIONS + ALL_CONVERSATIONS;
    }

    @GetMapping(NEW)
    public String getPageForCreateNewConversation(@ModelAttribute(NEW_CONVERSATION) Conversation conversation)
    {
        return CONVERSATIONS + NEW_CONVERSATION;
    }

    @PostMapping
    public String createNewConversation(@ModelAttribute(NEW_CONVERSATION) Conversation conversation)
    {
        List<Conversation> conversations = ConversationManager.getInstance().getConversations();
        conversation.setId(UUID.randomUUID());
        conversations.add(conversation);
        ConversationManager.getInstance().setConversations(conversations);
        //textMessageDAO.saveNewConversation(conversation);
        return REDIRECT + CONVERSATIONS;
    }
}
