package com.messenger.controllers;

import com.messenger.models.Conversation;
import com.messenger.models.User;
import com.messenger.repository.ConversationRepository;
import com.messenger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.messenger.constants.controllers.Endpoints.*;

@RestController
@RequestMapping(API_V1)
public class ExportController {
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    private static abstract class PathVariables{
        // TODO: Почему uuid? Лучше conversation_id и user_id. Семантику и абстракцию надо сохранять
        public static final String CONVERSATION_ID = "uuid";
        public static final String USER_ID = "uuid";
    }

    @Autowired
    public ExportController(ConversationRepository conversationRepository,
                            UserRepository userRepository) {
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = CONVERSATIONS_API /* TODO: Но ведь есть константа CONVERSATIONS? Ее будет достаточно... */, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Conversation>> getConversations(){
        List<Conversation> conversations = (List<Conversation>) conversationRepository.findAll();
        return ResponseEntity.ok(conversations);
    }

    @GetMapping(value = CONVERSATION_BY_ID_API, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Conversation> getConversationByID(@PathVariable(PathVariables.CONVERSATION_ID) UUID convID){
        if (conversationRepository.findById(convID).isPresent())
        {
            Conversation conversation = conversationRepository.findById(convID).get();
            return ResponseEntity.ok(conversation);
        }
        // TODO: Добавь запись в лог
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping(value = USERS_API, produces = MediaType.APPLICATION_JSON_VALUE)
    // TODO: getAllUsers
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = USER_BY_ID_API, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByID(@PathVariable(PathVariables.USER_ID) UUID userID){
        if (userRepository.findById(userID).isPresent())
        {
            User user = userRepository.findById(userID).get();
            return ResponseEntity.ok(user);
        }
        // TODO: Добавь запись в лог
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}