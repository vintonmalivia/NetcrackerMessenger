package com.messenger.controllers;
import com.messenger.models.Conversation;
import com.messenger.repository.TextMessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/messages")
public class MessageController
{
    private final TextMessageDAO textMessageDAO;

    @Autowired
    public MessageController(TextMessageDAO textMessageDAO){
        this.textMessageDAO = textMessageDAO;
    }

    @GetMapping()
    public String getConversations(Model model)
    {
        model.addAttribute("allConversations", textMessageDAO.getConversations());
        return "messages/getConversations";
    }

    @GetMapping("/{uuid}")
    public String getMessagesFromConversationByID(@PathVariable("uuid") UUID uuid, Model model)
    {
        model.addAttribute("conversationMessages", textMessageDAO.getConversationMessages(uuid));
        return "messages/getConversationMessages";
    }

    @GetMapping("/new-conversation")
    public String getPageForCreateNewConversation(@ModelAttribute("newConversation") Conversation conversation)
    {
        return "messages/newConversation";
    }

    @PostMapping()
    public String createNewConversation(@ModelAttribute("newConversation") Conversation conversation)
    {
        textMessageDAO.saveNewConversation(conversation);
        return "redirect:/messages";
    }
}
