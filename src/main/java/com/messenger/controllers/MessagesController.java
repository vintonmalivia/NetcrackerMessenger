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
    private static final String CONVERSATIONS = "conversations/";
    private static final String MESSAGES = "conversationMessages";
    private static final String UUID = "uuid";

    @GetMapping
    public String getMessagesFromConversationByID(@PathVariable(UUID) UUID uuid, Model model)
    {
        List<Conversation> conversations = ConversationManager.getInstance().getConversations();
        Conversation resultConversation = conversations.stream().filter(conversation -> conversation.getId().equals(uuid)).findAny().orElse(null);
        model.addAttribute(MESSAGES, resultConversation);
        return CONVERSATIONS + MESSAGES;
    }
}
