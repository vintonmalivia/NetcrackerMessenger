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
        public static final String UUID = "id";
    }

    private static abstract class ModelAttributes {
        private static final String CONVERSATION = "conversation";


        private static final String MESSAGES = "conversationMessages";
        private static final String NEW_MESSAGE = "newMessage";
        private static final String NEW_MEMBER = "newMember";

        private static final String USER_NOT_EXIST = "userNotExist";
        private static final String USER_ALREADY_IN_CONVERSATION = "userAlreadyInConversation";
        private static final String USER_ADDED = "userAdded";

    }

    private static abstract class Redirects {
        private static final String REDIRECT_TO_CONVERSATION_MESSAGES = "redirect:/conversations/{id}/messages";
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
    public String getMessagesFromConversationByID(@PathVariable(PathVariables.UUID) UUID id,
                                                  @ModelAttribute(ModelAttributes.NEW_MESSAGE) TextMessage textMessage,
                                                  Model model)
    {
        if (conversationService.isInConversation(id, userService.getCurrentUser().getProfile().getUserID()))
        {
            model.addAttribute(ModelAttributes.CONVERSATION, conversationService.getById(id));
            model.addAttribute(ModelAttributes.MESSAGES, messageService.getMessages(id));
            return Views.CONVERSATIONS_PATH + Views.MESSAGES_HTML;
        }
        logger.info("Can't open conversation messages from conversation with ID = {} for profile with ID = {} " +
                "because this profile is not a member of this conversation.",
                id, userService.getCurrentUser().getProfile().getUserID());
        return Redirects.REDIRECT_TO_CONVERSATIONS;
    }

    @PostMapping
    public String createMessage(@PathVariable(PathVariables.UUID) UUID id,
                                @ModelAttribute(ModelAttributes.NEW_MESSAGE) TextMessage textMessage,
                                Model model)
    {
        messageService.createMessage(textMessage, id);
        logger.info("Message with ID = {} by user with profile ID = {} has been sent.",
                textMessage.getId(), textMessage.getSender().getUserID());
        model.addAttribute(ModelAttributes.MESSAGES, messageService.getMessages(id));
        return Redirects.REDIRECT_TO_CONVERSATION_MESSAGES;
    }

    @GetMapping(ADD_NEW_MEMBER)
    public String showAddMemberPage(@ModelAttribute(ModelAttributes.NEW_MEMBER) User user,
                                    @PathVariable UUID id)
    {
        if (conversationService.isInConversation(id, userService.getCurrentUser().getProfile().getUserID()))
        {
            logger.info("Page with adding new member to conversation with ID = {} opened to profile with" +
                    " ID = {}.", id, userService.getCurrentUser().getProfile().getUserID());
            return Views.CONVERSATIONS_PATH + Views.ADD_NEW_MEMBER_HTML;
        }
        return Redirects.REDIRECT_TO_CONVERSATIONS;
    }

    @PostMapping(ADD_NEW_MEMBER)
    public String addMemberToConversation(@ModelAttribute(ModelAttributes.NEW_MEMBER) User user,
                                          @PathVariable UUID id,
                                          Model model)
    {
        if (!userService.existsByUsername(user.getUsername()))
        {
            logger.info("User with username = {} does not exist.", user.getUsername());
            model.addAttribute(ModelAttributes.USER_NOT_EXIST, "User not exist.");
            return Views.CONVERSATIONS_PATH + Views.ADD_NEW_MEMBER_HTML;
        }

        if (conversationService.isUserInMembers(id, user))
        {
            logger.info("Can not invite user with username = {} in conversation with ID = {} " +
                    "because user is already member of this conversation.", user.getUsername(), id);
            model.addAttribute(ModelAttributes.USER_ALREADY_IN_CONVERSATION,
                    "User already in this conversation.");
            return Views.CONVERSATIONS_PATH + Views.ADD_NEW_MEMBER_HTML;
        }
        conversationService.addMemberToConversation(id, user);
        logger.info("User with username = {} successfully added in conversation with ID = {}.", user.getUsername(), id);
        model.addAttribute(ModelAttributes.USER_ADDED, "User successfully added to this conversation.");
        return Views.CONVERSATIONS_PATH + Views.ADD_NEW_MEMBER_HTML;
    }
}
