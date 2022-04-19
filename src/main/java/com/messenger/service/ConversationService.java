package com.messenger.service;

import com.messenger.models.Conversation;
import com.messenger.models.User;
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

    public List<Conversation> getAllConversations(UUID uuid){
        return databaseConversationDAO.getProfileConversations(uuid);
    }

    public void createConversation(Conversation conversation){
        conversation.setCreator(userService.getCurrentUser().getProfile());
        conversation.addMember(userService.getCurrentUser().getProfile());
        databaseConversationDAO.save(conversation);
    }

    public void saveConversation(Conversation conversation){
        databaseConversationDAO.save(conversation);
    }

    public void deleteConversation(UUID uuid){databaseConversationDAO.deleteById(uuid);}

    public void addMemberToConversation(UUID uuid, User user){
        Conversation conversation = databaseConversationDAO.findById(uuid).get();
        User foundUser = userService.findUserByUsername(user.getUsername());
        conversation.addMember(foundUser.getProfile());
        saveConversation(conversation);
    }

    public boolean existenceOfUserInConversation(UUID uuid, User user){
        Conversation conversation = databaseConversationDAO.findById(uuid).get();
        User foundUser = userService.findUserByUsername(user.getUsername());
        return conversation.getMembers().contains(foundUser.getProfile());
    }
}
