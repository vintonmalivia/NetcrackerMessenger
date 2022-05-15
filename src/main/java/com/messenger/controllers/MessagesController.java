package com.messenger.controllers;

import com.messenger.models.User;
import com.messenger.models.impl.TextMessage;
import com.messenger.service.ConversationService;
import com.messenger.service.MessageService;
import com.messenger.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.messenger.constants.controllers.Endpoints.ADD_NEW_MEMBER;
import static com.messenger.constants.controllers.Endpoints.MESSAGES;

@Controller
@RequestMapping(MESSAGES)
public class MessagesController
{
    private static abstract class Views {
        private static final String CONVERSATIONS_PATH = "conversations/";
        private static final String MESSAGES_HTML = "conversationMessages";
        private static final String ADD_NEW_MEMBER_HTML = "addNewMember";
    }

    private static abstract class PathVariables {
        public static final String UUID = "uuid";
    }

    private static abstract class ModelAttributes {
        private static final String CONVERSATION = "conversation";


        private static final String MESSAGES = "conversationMessages";
        private static final String NEW_MESSAGE = "newMessage";
        private static final String NEW_MEMBER = "newMember";

        // TODO: Не используется - убирай
        private static final String MEMBERS = "members";

        private static final String USER_NOT_EXIST = "userNotExist";
        private static final String USER_NOT_EXIST_VALUE = "User not exist.";
        private static final String USER_ALREADY_IN_CONVERSATION = "userAlreadyInConversation";
        // TODO: Обычно кастомные сообщения, которые ну 100% будут использоваться лишь раз, в константы не добавляют
        //  Сделай просто инлайном (прям на месте использования).
        private static final String USER_ALREADY_IN_CONVERSATION_VALUE = "User already in this conversation.";
        private static final String USER_ADDED = "userAdded";
        // TODO: То же самое
        private static final String USER_ADDED_VALUE = "User successfully added to this conversation.";
    }

    private static abstract class Redirects {
        private static final String REDIRECT_TO_CONVERSATION_MESSAGES = "redirect:/conversations/{uuid}/messages";
        private static final String REDIRECT_TO_CONVERSATIONS = "redirect:/conversations";
    }

    private static final Logger logger = LoggerFactory.getLogger(MessagesController.class);

    private final MessageService messageService;
    private final UserService userService;
    private final ConversationService conversationService;


    @Autowired
    public MessagesController(MessageService messageService,
                              UserService userService,
                              ConversationService conversationService)
    {
        this.messageService = messageService;
        this.userService = userService;
        this.conversationService = conversationService;
    }

    @GetMapping
    public String getMessagesFromConversationByID(@PathVariable(PathVariables.UUID) UUID uuid,
                                                  @ModelAttribute(ModelAttributes.NEW_MESSAGE) TextMessage textMessage,
                                                  Model model)
    {
        if (conversationService.isInConversation(uuid, userService.getCurrentUser().getProfile().getUserID()))
        {
            model.addAttribute(ModelAttributes.CONVERSATION, conversationService.getById(uuid));
            model.addAttribute(ModelAttributes.MESSAGES, messageService.getMessages(uuid));
            return Views.CONVERSATIONS_PATH + Views.MESSAGES_HTML;
        }
        // TODO: Выведи в лог сообщение о том, что не получается вывести сообщения для текущего пользователя, так как он
        //  не состоит в беседе. + указать юзера, попытавшегося это сделать
        return Redirects.REDIRECT_TO_CONVERSATIONS;
    }

    @PostMapping
    // TODO: createMessage
    public String createNewMessage(@PathVariable(PathVariables.UUID) UUID uuid, // TODO: Просто id достаточно
                                   @ModelAttribute(ModelAttributes.NEW_MESSAGE) TextMessage textMessage,
                                   Model model)
    {
        messageService.createMessage(textMessage, uuid);
        logger.info("Message with ID = {} by user with profile ID = {} has been sent.",
                textMessage.getId(), textMessage.getSender().getUserID());
        model.addAttribute(ModelAttributes.MESSAGES, messageService.getMessages(uuid));
        return Redirects.REDIRECT_TO_CONVERSATION_MESSAGES;
    }

    @GetMapping(ADD_NEW_MEMBER)
    // TODO: showAddMemberPage
    public String getPageToAddMember(@ModelAttribute(ModelAttributes.NEW_MEMBER) User user,
                                     @PathVariable UUID uuid)
    {
        if (conversationService.isInConversation(uuid, userService.getCurrentUser().getProfile().getUserID()))
        {
            // TODO: Выведи в лог сообщение об этом
            return Views.CONVERSATIONS_PATH + Views.ADD_NEW_MEMBER_HTML;
        }
        return Redirects.REDIRECT_TO_CONVERSATIONS;
    }

    @PostMapping(ADD_NEW_MEMBER)
    public String addMemberToConversation(@ModelAttribute(ModelAttributes.NEW_MEMBER) User user,
                                          @PathVariable UUID uuid,
                                          Model model)
    {
        if (!userService.existsByUsername(user.getUsername()))
        {
            // TODO: Выведи в лог сообщение об отсутствии пользователя
            model.addAttribute(ModelAttributes.USER_NOT_EXIST, ModelAttributes.USER_NOT_EXIST_VALUE);
            return Views.CONVERSATIONS_PATH + Views.ADD_NEW_MEMBER_HTML;
        }

        if (conversationService.existenceOfUserInConversation(uuid, user))
        {
            model.addAttribute(ModelAttributes.USER_ALREADY_IN_CONVERSATION,
                    ModelAttributes.USER_ALREADY_IN_CONVERSATION_VALUE);
            return Views.CONVERSATIONS_PATH + Views.ADD_NEW_MEMBER_HTML;
        }

        conversationService.addMemberToConversation(uuid, user);
        model.addAttribute(ModelAttributes.USER_ADDED, ModelAttributes.USER_ADDED_VALUE);
        return Views.CONVERSATIONS_PATH + Views.ADD_NEW_MEMBER_HTML;
    }
}
