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

    @GetMapping
    public String getConversations(Model model)
    {
        model.addAttribute("allConversations" /* TODO: (normal) в константы */,  ConversationManager.getInstance().getConversations());
        return "conversations/allConversations"; // TODO: (normal) в константы
    }

    @GetMapping("/new")
    public String getPageForCreateNewConversation(@ModelAttribute("newConversation") /* TODO: (normal) в константы */ Conversation conversation)
    {
        return "conversations/newConversation"; // TODO: (normal) в константы
    }

    @PostMapping
    public String createNewConversation(@ModelAttribute("newConversation") /* TODO: (normal) в константы */ Conversation conversation)
    {
        List<Conversation> conversations = ConversationManager.getInstance().getConversations();
        conversations.add(conversation);
        ConversationManager.getInstance().setConversations(conversations);
        //textMessageDAO.saveNewConversation(conversation);
        return "redirect:/conversations"; /* TODO: (normal) в константы */
    }
}
