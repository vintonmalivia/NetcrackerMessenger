package com.messenger.service;

import com.messenger.models.Conversation;
import com.messenger.models.impl.TextMessage;
import com.messenger.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    private final MessageRepository databaseMessageDAO;
    private final ConversationService conversationService;
    private final UserService userService;

    @Autowired
    public MessageService(MessageRepository databaseMessageDAO,
                          ConversationService conversationService,
                          UserService userService) {
        this.databaseMessageDAO = databaseMessageDAO;
        this.conversationService = conversationService;
        this.userService = userService;
    }

    public List<TextMessage> getMessages(UUID uuid)
    {
        return databaseMessageDAO.getMessages(uuid);
    }

    public void create(TextMessage textMessage, UUID uuid) {
        textMessage.setId(UUID.randomUUID());
        textMessage.setDateOfSending(new Date());
        textMessage.setSender(userService.getCurrentUser().getProfile());
        Conversation conversation = conversationService.getById(uuid);
        conversation.addMessage(textMessage);
        conversationService.saveConversation(conversation);
    }
}
