package com.messenger.service;

import com.messenger.models.impl.TextMessage;
import com.messenger.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    private MessageRepository databaseMessageDAO;

    @Autowired
    public MessageService(MessageRepository databaseMessageDAO) {
        this.databaseMessageDAO = databaseMessageDAO;
    }

    public List<TextMessage> getTextMessages(UUID uuid)
    {
        return databaseMessageDAO.getMessages(uuid);
    }

    public void create(TextMessage textMessage, UUID uuid) {
        textMessage.setId(UUID.randomUUID());
        textMessage.setDateOfSending(new Date());
        textMessage.setSender(null);
//        getTextMessages(uuid).add(textMessage);
//        databaseMessageDAO.setConversationId(uuid);
        databaseMessageDAO.getMessages(uuid).add(databaseMessageDAO.save(textMessage));
    }
}
