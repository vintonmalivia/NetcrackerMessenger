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
@RequestMapping("/conversations")
public class ConversationController
{
//    private final TextMessageDAO textMessageDAO;

//    @Autowired
//    public ConversationController(TextMessageDAO textMessageDAO){
//        this.textMessageDAO = textMessageDAO;
//    }

    @GetMapping
    public String getConversations(Model model)
    {
        model.addAttribute("allConversations",  ConversationManager.getInstance().getConversations());
        return "conversations/allConversations";
    }

    @GetMapping("/new")
    public String getPageForCreateNewConversation(@ModelAttribute("newConversation") Conversation conversation)
    {
        return "conversations/newConversation";
    }

    @PostMapping
    public String createNewConversation(@ModelAttribute("newConversation") Conversation conversation)
    {
        List<Conversation> conversations = ConversationManager.getInstance().getConversations();
        conversations.add(conversation);
        ConversationManager.getInstance().setConversations(conversations);
        //textMessageDAO.saveNewConversation(conversation);
        return "redirect:/conversations";
    }
}
