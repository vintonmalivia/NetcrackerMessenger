package com.messenger.service;

import com.messenger.models.Role;
import com.messenger.models.User;
import com.messenger.repository.RoleRepository;
import com.messenger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private static abstract class ExceptionMessage
    {
        private static final String USER_NOT_FOUND = "User not found";
    }

    private static abstract class UserData
    {
        private static final String ROLE_USER = "ROLE_USER"; // todo: Наверное, это нужно перенести в отдельный класс ролей
        private static final String ROLE_USER_ID = "0ada0e40-c34b-48ce-9ddc-be67eec99eed"; // TODO: Что это? Хардкод?
    }

    @PersistenceContext
    private EntityManager em;
    @Autowired // TODO: Autowired ставится над конструктором. Использование его над полями лучше избегать. Почитай здесь: https://blog.marcnuri.com/field-injection-is-not-recommended
    UserRepository userRepository; // TODO: Всегда указывай модификаторы доступа явно
    @Autowired // TODO: см. 37 строку
    RoleRepository roleRepository; // TODO: Всегда указывай модификаторы доступа явно
    @Autowired // TODO: см. 37 строку
    BCryptPasswordEncoder bCryptPasswordEncoder; // TODO: Всегда указывай модификаторы доступа явно

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(ExceptionMessage.USER_NOT_FOUND);
        }

        return user;
    }

    public User findUserById(UUID userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User()); // TODO: А почему мы создаем нового юзера, если его нет? По названию метода,
                                              //  создания происходить не должно. Решение: переделать логику или переназвать метод
    }

    public List<User> allUsers() { // TODO: Лучше getAllUsers. Потому что allUsers не совсем понятно, для чего это (можно только предположить!)
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        // TODO: Просто как вариант. В java 8 появилась возможность юзать Objects.isNull(). Мы стараемся использовать
        //  как можно более новые варианты написания кода
        if (userFromDB != null) {
            return false;
        }

        // TODO: на будущее: очень часто тяжело читать подобный код. Слишком длинная цепочка вызовов.
        //  Именно тут все логино и понятно, но код не всегда простой. По возможности, нужно разделять цепочку вызовов
        user.setRoles(Collections.singleton(new Role(UUID.fromString(UserData.ROLE_USER_ID), UserData.ROLE_USER)));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(UUID userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> usergtList(UUID idMin) { // TODO: не совсем понятно, что делает метод. Название не очень удачное
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId" /* TODO: В константы */, User.class)
                .setParameter("paramId" /* TODO: В константы */, idMin).getResultList();
    }
}