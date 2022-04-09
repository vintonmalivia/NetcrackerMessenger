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

    @Autowired
    public MessageService(MessageRepository databaseMessageDAO,
                          ConversationService conversationService) {
        this.databaseMessageDAO = databaseMessageDAO;
        this.conversationService = conversationService;
    }

    public List<TextMessage> getTextMessages(UUID uuid)
    {
        return databaseMessageDAO.getMessages(uuid);
    }

    public void create(TextMessage textMessage, UUID uuid) {
        textMessage.setId(UUID.randomUUID());
        textMessage.setDateOfSending(new Date());
        textMessage.setSender(null);
        Conversation conversation = conversationService.getById(uuid);
        conversation.addMessage(textMessage);
        conversationService.createConversation(conversation);
    }
}
