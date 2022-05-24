package com.messenger.controllers;

import com.messenger.models.Conversation;
import com.messenger.models.User;
import com.messenger.repository.ConversationRepository;
import com.messenger.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ExportController.class);

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    private static abstract class PathVariables{
        public static final String CONVERSATION_ID = "conversation_id";
        public static final String USER_ID = "user_id";
    }

    @Autowired
    public ExportController(ConversationRepository conversationRepository,
                            UserRepository userRepository) {
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = CONVERSATIONS, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Conversation>> getConversations(){
        List<Conversation> conversations = (List<Conversation>) conversationRepository.findAll();
        return ResponseEntity.ok(conversations);
    }

    @GetMapping(value = CONVERSATION_BY_ID_API, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Conversation> getConversationByID(@PathVariable(PathVariables.CONVERSATION_ID) UUID id){
        if (conversationRepository.findById(id).isPresent())
        {
            Conversation conversation = conversationRepository.findById(id).get();
            return ResponseEntity.ok(conversation);
        }
        logger.error("Could not find conversation with ID = {}.", id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping(value = USERS_API, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = USER_BY_ID_API, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByID(@PathVariable(PathVariables.USER_ID) UUID id){
        if (userRepository.findById(id).isPresent())
        {
            User user = userRepository.findById(id).get();
            return ResponseEntity.ok(user);
        }
        logger.error("Could not find user with ID = {}.", id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}