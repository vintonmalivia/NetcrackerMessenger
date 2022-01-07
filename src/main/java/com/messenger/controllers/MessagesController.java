package com.messenger.controllers;

import com.messenger.models.Conversation;
import com.messenger.repository.ConversationManager;
import com.messenger.repository.TextMessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/conversations/{uuid}/messages") /* TODO: (normal) в константы */
public class MessagesController
{
//    private final TextMessageDAO textMessageDAO;
//
//    @Autowired
//    public MessagesController(TextMessageDAO textMessageDAO){
//        this.textMessageDAO = textMessageDAO;
//    }

    @GetMapping
    public String getMessagesFromConversationByID(@PathVariable("uuid") /* TODO: (normal) в константы */ UUID uuid, Model model)
    {
        List<Conversation> conversations = ConversationManager.getInstance().getConversations();
        Conversation resultConversation = conversations.stream().filter(conversation -> conversation.getId().equals(uuid)).findAny().orElse(null);
        model.addAttribute("conversationMessages", /* TODO: (normal) в константы */ resultConversation);
        return "conversations/conversationMessages"; /* TODO: (normal) в константы */
    }
}
