package com.messenger.service;

import com.messenger.controllers.ConversationsController;
import com.messenger.models.Conversation;
import com.messenger.models.User;
import com.messenger.repository.ConversationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
public class ConversationService {

    private static final Logger logger = LoggerFactory.getLogger(ConversationService.class);

    private final ConversationRepository databaseConversationDAO;
    private final UserService userService;

    @Autowired
    public ConversationService(ConversationRepository databaseConversationDAO,
                               UserService userService) {
        this.databaseConversationDAO = databaseConversationDAO;
        this.userService = userService;
    }

    public Conversation getById(UUID id) {
        if (databaseConversationDAO.findById(id).isPresent()) {
            return databaseConversationDAO.findById(id).get();
        }
        return null;
    }

    public List<Conversation> getAllConversations(UUID uuid){
        return databaseConversationDAO.getProfileConversations(uuid);
    }

    public void createConversation(Conversation conversation){
        conversation.setCreator(userService.getCurrentUser().getProfile());
        conversation.addMember(userService.getCurrentUser().getProfile());
        databaseConversationDAO.save(conversation);
    }

    public boolean isInConversation(UUID convID, UUID profileID)
    {
        return databaseConversationDAO.isUserInConversation(convID, profileID);
    }

    public void saveConversation(Conversation conversation){
        databaseConversationDAO.save(conversation);
    }

    public void deleteConversation(UUID uuid){databaseConversationDAO.deleteById(uuid);}

    public void addMemberToConversation(UUID uuid, User user){
        if (databaseConversationDAO.findById(uuid).isPresent()) {
            Conversation conversation = databaseConversationDAO.findById(uuid).get();
            User foundUser = userService.findUserByUsername(user.getUsername());
            conversation.addMember(foundUser.getProfile());
            saveConversation(conversation);
            return;
        }
        logger.warn("Conversation with ID = {} not found.", uuid);
    }

    public boolean isUserInMembers(UUID uuid, User user){
        if (databaseConversationDAO.findById(uuid).isPresent()) {
            Conversation conversation = databaseConversationDAO.findById(uuid).get();
            User foundUser = userService.findUserByUsername(user.getUsername());
            return conversation.getMembers().contains(foundUser.getProfile());
        }
        logger.warn("Conversation with ID = {} not found.", uuid);
        return false;
    }

    @Transactional
    public void deleteCurrentUserFromConversationByConversationId(UUID profileID, UUID convID)
    {
        databaseConversationDAO.removeUserFromConversation(profileID, convID);
        databaseConversationDAO.deleteAbandonedUserMessages(profileID, convID);
        databaseConversationDAO.deleteCreatorIDIfUserLeaving(profileID, convID);
        databaseConversationDAO.deleteConversationIfNoMembers();
    }
}
