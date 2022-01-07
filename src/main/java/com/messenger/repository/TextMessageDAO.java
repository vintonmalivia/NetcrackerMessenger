package com.messenger.repository;

import com.messenger.models.AbstractMessage;
import com.messenger.models.Conversation;
import com.messenger.models.impl.TextMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

// TODO: (high) - репозиторий для сообщений, не для чатов!
@Component // FIXME: (normal) DAO - это репозиторий. Все репозитории должны обозначаться на общей аннотацией @Component, а @Repository,
           //  Я знаю 4 типа компонент: @Repository, @Service, @Controller/@RestController. Скоро будем делить приложение на слои, можно почитать про слои репозиториев,
           //  сервисов и контроллеров
public class TextMessageDAO
{
//    private List<AbstractMessage> messages1conv;
//    // TODO: (high) инициализация должна идти в основном классе - Main. А лучше один раз проинициализировать, сохранить в файл и убрать инициализацию эту
//    //  В приложении не должно быть такого, по сути. Все должно храниться где-то
//    {
//        messages1conv = new ArrayList<>();
//        messages1conv.add(new TextMessage("Hi. I am the creator.", UUID.randomUUID(), new Date()));
//        messages1conv.add(new TextMessage("Hello, I am the second.", UUID.randomUUID(), new Date()));
//        messages1conv.add(new TextMessage("I am third.", UUID.randomUUID(), new Date()));
//        messages1conv.add(new TextMessage("Four.", UUID.randomUUID(), new Date()));
//    }
//
//    private List<UUID> membersID1conv;
//    // TODO: (high) инициализация должна идти в основном классе - Main
//    {
//        membersID1conv = new ArrayList<>();
//    }
//
//    private List<AbstractMessage> messages2conv;
//    // TODO: (high) инициализация должна идти в основном классе - Main
//    {
//        messages2conv = new ArrayList<>();
//        messages2conv.add(new TextMessage("Hi", UUID.randomUUID(), new Date()));
//        messages2conv.add(new TextMessage("Hi", UUID.randomUUID(), new Date()));
//        messages2conv.add(new TextMessage("Hi", UUID.randomUUID(), new Date()));
//    }
//
//    private List<Conversation> conversationList;
//    // TODO: (high) инициализация должна идти в основном классе - Main
//    {
//        conversationList = new ArrayList<>();
//        conversationList.add(new Conversation("Conv1", UUID.randomUUID(), UUID.randomUUID(), membersID1conv, messages1conv));
//        conversationList.add(new Conversation("Conv2", UUID.randomUUID(), UUID.randomUUID(), membersID1conv, messages2conv));
//    }


//    // TODO: (high) Это касается чатов. Все, что касается CRUD чатов, должно быть в ConversationsDAO
//    public List<Conversation> getConversations()
//    {
//        return conversationList;
//    }
//
//    public Conversation getConversationMessagesByID(UUID uuid)
//    {
//        return conversationList.stream().filter(conversation -> conversation.getId().equals(uuid)).findAny().orElse(null);
//    }
//
//    // TODO: (high) Это касается чатов. Все, что касается CRUD чатов, должно быть в ConversationsDAO
//    public void saveNewConversation(Conversation conversation)
//    {
//        conversation.setId(UUID.randomUUID());
//        conversationList.add(conversation);
//    }
}
