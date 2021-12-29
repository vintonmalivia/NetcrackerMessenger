package com.messenger.repository;

import com.messenger.models.AbstractMessage;
import com.messenger.models.Conversation;
import com.messenger.models.TextMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class TextMessageDAO
{
    private List<AbstractMessage> messages1conv;
    {
        messages1conv = new ArrayList<>();
        messages1conv.add(new TextMessage("Hi. I am the creator.", UUID.randomUUID(), new Date()));
        messages1conv.add(new TextMessage("Hello, I am the second.", UUID.randomUUID(), new Date()));
        messages1conv.add(new TextMessage("I am third.", UUID.randomUUID(), new Date()));
        messages1conv.add(new TextMessage("Four.", UUID.randomUUID(), new Date()));
    }

    private List<UUID> membersID1conv;
    {
        membersID1conv = new ArrayList<>();
    }

    private List<AbstractMessage> messages2conv;
    {
        messages2conv = new ArrayList<>();
        messages2conv.add(new TextMessage("Hi", UUID.randomUUID(), new Date()));
        messages2conv.add(new TextMessage("Hi", UUID.randomUUID(), new Date()));
        messages2conv.add(new TextMessage("Hi", UUID.randomUUID(), new Date()));
    }

    private List<Conversation> conversationList;
    {
        conversationList = new ArrayList<>();
        conversationList.add(new Conversation("Conv1", UUID.randomUUID(), UUID.randomUUID(), membersID1conv, messages1conv));
        conversationList.add(new Conversation("Conv2", UUID.randomUUID(), UUID.randomUUID(), membersID1conv, messages2conv));
    }

    public List<Conversation> getConversations()
    {
        return conversationList;
    }

    public Conversation getConversationMessages(UUID uuid)
    {
        return conversationList.stream().filter(conversation -> conversation.getConversationID().equals(uuid)).findAny().orElse(null);
    }

    public void saveNewConversation(Conversation conversation)
    {
        conversation.setConversationID(UUID.randomUUID());
        conversationList.add(conversation);
    }
}
