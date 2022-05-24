package com.messenger.service;

import com.messenger.models.Conversation;
import com.messenger.models.Profile;
import com.messenger.models.impl.TextMessage;
import com.messenger.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class MessageService {

    private static final int NUMBER_OF_MESSAGES_TO_BLOCK = 10;
    private static final long SECONDS_TO_UNBLOCK = 60;

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

    public TimerTask startAntispamTask(){
        Profile currentUserProfile = userService.getCurrentUser().getProfile();
        return new TimerTask() {
            @Override
            public void run() {
                unblockProfile();
            }

            private void unblockProfile() {
                currentUserProfile.setSpammingStatus(false);
            }
        };
    }

    public void createMessage(TextMessage textMessage, UUID uuid) {
        Profile currentUserProfile = userService.getCurrentUser().getProfile();
        if (databaseMessageDAO.getNumberOfMessagesInLastMinute(
                currentUserProfile.getUserID()) >= NUMBER_OF_MESSAGES_TO_BLOCK)
        {
            currentUserProfile.setSpammingStatus(true);
            Timer timer = new Timer(true);
            timer.schedule(startAntispamTask(), TimeUnit.SECONDS.toMillis(SECONDS_TO_UNBLOCK));
        }
        textMessage.setId(UUID.randomUUID());
        textMessage.setDateOfSending(new Date());
        textMessage.setSender(userService.getCurrentUser().getProfile());
        Conversation conversation = conversationService.getById(uuid);
        conversation.addMessage(textMessage);
        conversationService.saveConversation(conversation);
    }

}
