package com.messenger.controllers;

import com.messenger.models.Profile;
import com.messenger.models.User;
import com.messenger.models.impl.TextMessage;
import com.messenger.repository.UserRepository;
import com.messenger.service.ConversationService;
import com.messenger.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        private static final String MEMBERS = "members";

        private static final String USER_NOT_EXIST = "userNotExist";
        private static final String USER_NOT_EXIST_VALUE = "User not exist.";
        private static final String USER_ALREADY_IN_CONVERSATION = "userAlreadyInConversation";
        private static final String USER_ALREADY_IN_CONVERSATION_VALUE = "User already in this conversation.";
        private static final String USER_ADDED = "userAdded";
        private static final String USER_ADDED_VALUE = "User successfully added to this conversation.";
    }

    private static abstract class Redirects {
        private static final String REDIRECT_TO_CONVERSATION_MESSAGES = "redirect:/conversations/{uuid}/messages";
    }

    private static final Logger logger = LoggerFactory.getLogger(MessagesController.class);

    private final MessageService messageService;
    private final UserRepository userRepository;
    private final ConversationService conversationService;


    @Autowired
    public MessagesController(MessageService messageService,
                              UserRepository userRepository,
                              ConversationService conversationService)
    {
        this.messageService = messageService;
        this.userRepository = userRepository;
        this.conversationService = conversationService;
    }

    @GetMapping
    public String getMessagesFromConversationByID(@PathVariable(PathVariables.UUID) UUID uuid,
                                                  @ModelAttribute(ModelAttributes.NEW_MESSAGE) TextMessage textMessage,
//                                                  @ModelAttribute(ModelAttributes.MEMBERS) List<Profile> members,
                                                  Model model)
    {
        model.addAttribute(ModelAttributes.CONVERSATION, conversationService.getById(uuid));
        model.addAttribute(ModelAttributes.MESSAGES, messageService.getMessages(uuid));
//        model.addAttribute(ModelAttributes.MEMBERS, conversationService.getMembers(uuid));
        return Views.CONVERSATIONS_PATH + Views.MESSAGES_HTML;
    }

    @PostMapping
    public String createNewMessage(@PathVariable(PathVariables.UUID) UUID uuid,
                                   @ModelAttribute(ModelAttributes.NEW_MESSAGE) TextMessage textMessage,
                                   Model model)
    {
        messageService.create(textMessage, uuid);
        logger.info("Message with ID = {} by user with profile ID = {} has been sent.",
                textMessage.getId(), textMessage.getSender().getUserID());
        model.addAttribute(ModelAttributes.MESSAGES, messageService.getMessages(uuid));
        return Redirects.REDIRECT_TO_CONVERSATION_MESSAGES;
    }

    @GetMapping(ADD_NEW_MEMBER)
    public String getPageToAddMember(@ModelAttribute(ModelAttributes.NEW_MEMBER) User user,
                                     @PathVariable UUID uuid)
    {
        return Views.CONVERSATIONS_PATH + Views.ADD_NEW_MEMBER_HTML;
    }

    @PostMapping(ADD_NEW_MEMBER)
    public String addMemberToConversation(@ModelAttribute(ModelAttributes.NEW_MEMBER) User user,
                                          @PathVariable UUID uuid,
                                          Model model)
    {
        if (!userRepository.existsByUsername(user.getUsername()))
        {
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
