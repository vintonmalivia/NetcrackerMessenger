package com.messenger.service;

import com.messenger.models.Conversation;
import com.messenger.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ConversationService {
    private ConversationRepository databaseConversationDAO;

    @Autowired
    public ConversationService(ConversationRepository databaseConversationDAO) {
        this.databaseConversationDAO = databaseConversationDAO;
    }

    public Conversation getById(UUID id) {
        return databaseConversationDAO.findById(id).get();
    }

    public List<Conversation> getAllConversations(){
        return (List<Conversation>) databaseConversationDAO.findAll();
    }

    public void createConversation(Conversation conversation){
        databaseConversationDAO.save(conversation);
    }

    public void deleteConversation(UUID uuid){databaseConversationDAO.deleteById(uuid);}
}
