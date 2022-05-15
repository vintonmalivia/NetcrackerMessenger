package com.messenger.service;

import com.messenger.models.Conversation;
import com.messenger.models.Profile;
import com.messenger.models.User;
import com.messenger.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        // TODO: Здесь findById(id) возвращает Optional. А если там нет объекта? Нужно сделать проверку
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

    public boolean isInConversation(UUID convID, UUID profileID)
    {
        return databaseConversationDAO.isInConversation(convID, profileID);
    }

    public void saveConversation(Conversation conversation){
        databaseConversationDAO.save(conversation);
    }

    public void deleteConversation(UUID uuid){databaseConversationDAO.deleteById(uuid);}

    // TODO: Не нужно - удаляй
    public List<Profile> getMembers(UUID uuid){return databaseConversationDAO.getMembers(uuid);}

    public void addMemberToConversation(UUID uuid, User user){
        // TODO: Здесь findById(id) возвращает Optional. А если там нет объекта? Нужно сделать проверку
        Conversation conversation = databaseConversationDAO.findById(uuid).get();
        User foundUser = userService.findUserByUsername(user.getUsername());
        conversation.addMember(foundUser.getProfile());
        saveConversation(conversation);
    }

    // TODO: Лучше isUserInMembers (булеан методы обычно начинаются как вопрос, например с предлога is)
    //  Примеры: isEmpty, isBlank...
    public boolean existenceOfUserInConversation(UUID uuid, User user){
        // TODO: Здесь findById(id) возвращает Optional. А если там нет объекта? Нужно сделать проверку
        Conversation conversation = databaseConversationDAO.findById(uuid).get();
        User foundUser = userService.findUserByUsername(user.getUsername());
        return conversation.getMembers().contains(foundUser.getProfile());
    }

    @Transactional
    // TODO: Название от имени юзера, а не сервиса. Поищи аналогичные тудушки
    public void leaveConversation(UUID profileID, UUID convID)
    {
        databaseConversationDAO.leaveFromConversation(profileID, convID);
        databaseConversationDAO.deleteAbandonedUserMessages(profileID, convID);
        databaseConversationDAO.deleteCreatorIDIfUserLeaving(profileID, convID);
        databaseConversationDAO.deleteConversationIfNoMembers();
    }
}
