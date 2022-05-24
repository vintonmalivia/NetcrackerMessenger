package com.messenger.service;

import com.messenger.models.Role;
import com.messenger.models.User;
import com.messenger.repository.ConversationRepository;
import com.messenger.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private static abstract class ExceptionMessage
    {
        private static final String USER_NOT_FOUND = "User not found";
    }

    private static abstract class UserData
    {
        private static final String ROLE_USER = "ROLE_USER";
        private static final String ROLE_USER_ID = "0ada0e40-c34b-48ce-9ddc-be67eec99eed";
    }

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConversationRepository conversationRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       ConversationRepository conversationRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.conversationRepository = conversationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (Objects.isNull(user)) {
            logger.error("Can not load user with username = {} because user with this username does not exist.",
                    username);
            throw new UsernameNotFoundException(ExceptionMessage.USER_NOT_FOUND);
        }

        return user;
    }

    public User getCurrentUser() {
        try {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (NullPointerException nullPointerException) {
            logger.error(nullPointerException.getMessage());
        }
        return null;
    }

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (!Objects.isNull(userFromDB)) {
            logger.error("Can not save user with username = {} because user with this username already exists.",
                    user.getUsername());
            return false;
        }

        user.setRoles(Collections.singleton(new Role(UUID.fromString(UserData.ROLE_USER_ID), UserData.ROLE_USER)));

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Transactional
    public void deleteUser(UUID userId) {
        if (userRepository.findById(userId).isPresent()) {
            UUID profileID = userRepository.findById(userId).get().getProfile().getUserID();
            conversationRepository.deleteUserFromConversationMembers(profileID);
            conversationRepository.deleteMessagesFromDeletedUser(profileID);
            conversationRepository.deleteCreatorID(profileID);
            conversationRepository.deleteConversationIfNoMembers();
            userRepository.deleteById(userId);
        }
    }
}