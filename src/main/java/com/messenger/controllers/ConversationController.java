package com.messenger.controllers;

import com.messenger.models.Conversation;
import com.messenger.service.ConversationService;
import com.messenger.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.messenger.constants.controllers.Endpoints.*;

@Slf4j
@Controller
@RequestMapping(CONVERSATIONS)
// TODO: ConversationsController (потому что управлеине не чатом, а чатами)
public class ConversationController
{
    private static abstract class Views {
        private static final String CONVERSATIONS_PATH = "conversations/";
        private static final String ALL_CONVERSATIONS_HTML = "allConversations";
        private static final String NEW_CONVERSATION_HTML = "newConversation";
    }

    private static abstract class ModelAttributes {
        private static final String ALL_CONVERSATIONS = "allConversations";
        private static final String NEW_CONVERSATION = "newConversation";
    }

    private static abstract class PathVariables {
        private static final String CONVERSATION_ID = "uuid";
    }

    private static abstract class Redirects {
        private static final String REDIRECT_TO_CONVERSATIONS = "redirect:/conversations";
    }

    private static final Logger logger = LoggerFactory.getLogger(ConversationController.class);

    private final ConversationService conversationService;
    private final UserService userService;

    @Autowired
    public ConversationController(ConversationService conversationService, UserService userService)
    {
        this.conversationService = conversationService;
        this.userService = userService;
    }

    @GetMapping
    // TODO: howConversations
    public String getConversations(Model model)
    {
        model.addAttribute(ModelAttributes.ALL_CONVERSATIONS,
                conversationService.getAllConversations(userService.getCurrentUser().getProfile().getUserID()));
        logger.trace("Conversations are opened.");
        return Views.CONVERSATIONS_PATH + Views.ALL_CONVERSATIONS_HTML;
    }

    @GetMapping(NEW)
    // TODO: showConversationCreationForm / showConversationCreationPage
    public String getPageForCreateNewConversation(
            @ModelAttribute(ModelAttributes.NEW_CONVERSATION) Conversation conversation)
    {
        logger.trace("Page for creating new conversation is opened.");
        return Views.CONVERSATIONS_PATH + Views.NEW_CONVERSATION_HTML;
    }

    @PostMapping
    // TODO: createConversation. Итак понятно, что будет новый чат )
    public String createNewConversation(@ModelAttribute(ModelAttributes.NEW_CONVERSATION) Conversation conversation)
    {
        conversationService.createConversation(conversation);
        logger.info("New conversation with name = {} and ID = {} is created. Creator = {} {} with profile ID = {}.",
                conversation.getName(), conversation.getId(), conversation.getCreator().getName(),
                conversation.getCreator().getSurname(), conversation.getCreator().getUserID());
        return Redirects.REDIRECT_TO_CONVERSATIONS;
    }

    @PostMapping(CONVERSATION_ID)
    // TODO: deleteConversationById
    public String deleteConversation(@PathVariable(PathVariables.CONVERSATION_ID) UUID uuid) {
        conversationService.deleteConversation(uuid);
        // TODO: А точно чат удалился? По сути, delete, насколько я знаю, не возвращает результат, если удалять нечего
        //  А в логах будет светиться, что конверсейшен, который, по сути, не существовал, был удален каким-то образом
        logger.info("Deleted conversation with ID = {}.", uuid);
        return Redirects.REDIRECT_TO_CONVERSATIONS;
    }

    @PostMapping(LEAVE_CONVERSATION)
    // TODO: Leave от имени пользователя. А тут от имени сервиса. deleteCurrentUserFromConversationByConversationId.
    //  Метод длинное название имеет, но в яве это нормально. Зато понятно, что происходит. Может, придумаешь название короче )
    public String leaveConversation(@PathVariable(PathVariables.CONVERSATION_ID) UUID uuid) {
        UUID profileID = userService.getCurrentUser().getProfile().getUserID();
        // TODO: Добавь запись в лог
        // TODO: leaveConversation от имени пользователя. А тут сервис...
        conversationService.leaveConversation(profileID, uuid);
        return Redirects.REDIRECT_TO_CONVERSATIONS;
    }
}
