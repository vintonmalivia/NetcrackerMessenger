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

    // TODO: Не, такие константы лучше не объединять во вложенный класс. Сделай их простыми константами в классе MessageService
    private static class BlockAndUnblockConsts{
        public static final int NUMBER_OF_MESSAGES_TO_BLOCK = 10;
        public static final long SECONDS_TO_UNBLOCK = 60;
    }

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

    // TODO: Можно ли разбить текущий метод на несколько мелких?
    public void createMessage(TextMessage textMessage, UUID uuid) {
        Profile currentUserProfile = userService.getCurrentUser().getProfile();
        if (databaseMessageDAO.getNumberOfMessagesInLastMinute(
                currentUserProfile.getUserID()) >= BlockAndUnblockConsts.NUMBER_OF_MESSAGES_TO_BLOCK)
        {
            currentUserProfile.setSpammingStatus(true);
            Timer timer = new Timer(true);

            // TODO: stopSpam -> stopSpamTask
            TimerTask stopSpam = new TimerTask() {
                @Override
                public void run() {
                    unblockProfile();
                }

                private void unblockProfile() {
                        currentUserProfile.setSpammingStatus(false);
                }
            };
            timer.schedule(stopSpam, TimeUnit.SECONDS.toMillis(BlockAndUnblockConsts.SECONDS_TO_UNBLOCK));
        }
        textMessage.setId(UUID.randomUUID());
        textMessage.setDateOfSending(new Date());
        textMessage.setSender(userService.getCurrentUser().getProfile());
        Conversation conversation = conversationService.getById(uuid);
        conversation.addMessage(textMessage);
        conversationService.saveConversation(conversation);
    }

}
