package com.messenger.service;

import com.messenger.models.Conversation;
import com.messenger.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ConversationService {
    private final ConversationRepository databaseConversationDAO;
    private final UserService userService;

    @Autowired
    public ConversationService(ConversationRepository databaseConversationDAO,
                               UserService userService) {
        this.databaseConversationDAO = databaseConversationDAO;
        this.userService = userService;
    }

    public Conversation getById(UUID id) {
        return databaseConversationDAO.findById(id).get();
    }

    public List<Conversation> getAllConversations(){
        return (List<Conversation>) databaseConversationDAO.findAll();
    }

    public void createConversation(Conversation conversation){
//        conversation.setCreator(userService.getCurrentUser().getProfile());
        databaseConversationDAO.save(conversation);
    }

    public void saveConversation(Conversation conversation){
        databaseConversationDAO.save(conversation);
    }

    public void deleteConversation(UUID uuid){databaseConversationDAO.deleteById(uuid);}
}
