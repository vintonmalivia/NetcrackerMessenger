package com.messenger.service;

import com.messenger.models.impl.TextMessage;
import com.messenger.repository.IDatabaseMessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    private IDatabaseMessageDAO databaseMessageDAO;

    @Autowired
    public MessageService(IDatabaseMessageDAO databaseMessageDAO) {
        this.databaseMessageDAO = databaseMessageDAO;
    }

    public List<TextMessage> getTextMessages(UUID uuid)
    {
        return databaseMessageDAO.getMessages(uuid);
    }
}
