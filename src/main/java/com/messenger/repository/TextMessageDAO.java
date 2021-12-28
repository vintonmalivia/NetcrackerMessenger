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
        conversationList.add((Conversation) messages1conv);
        conversationList.add((Conversation) messages2conv);
    }


//    TextMessage message1 = new TextMessage("Hi. I am the creator.", UUID.randomUUID(), new Date());
//    TextMessage message2 = new TextMessage("Hello, I am the second.", UUID.randomUUID(), new Date());
//    TextMessage message3 = new TextMessage("I am third.", UUID.randomUUID(), new Date());
//    TextMessage message4 = new TextMessage("What?", UUID.randomUUID(), new Date());
//    TextMessage message5 = new TextMessage("Okay.", UUID.randomUUID(), new Date());
//    TextMessage message6 = new TextMessage("Maybe.", UUID.randomUUID(), new Date());
//    TextMessage message7 = new TextMessage("Time.", UUID.randomUUID(), new Date());
//    TextMessage message8 = new TextMessage("Where?", UUID.randomUUID(), new Date());
//    TextMessage message9 = new TextMessage("How?", UUID.randomUUID(), new Date());
//    UUID[] membersID = new UUID[]{message1.getSenderID(), message2.getSenderID(), message3.getSenderID(),
//                message4.getSenderID(), message5.getSenderID(), message6.getSenderID(), message7.getSenderID(),
//                message8.getSenderID(), message9.getSenderID()};
//    private TextMessage[] messages = new TextMessage[]{message1, message2, message3, message4,
//            message5, message6, message7, message8, message9};
//    Conversation conversation = new Conversation("13GROUP", UUID.randomUUID(),
//            membersID, message1.getSenderID(), messages);
//
//
//    TextMessage message10 = new TextMessage("Hi", UUID.randomUUID(), new Date());
//    TextMessage message20 = new TextMessage("Hi", UUID.randomUUID(), new Date());
//    TextMessage message30 = new TextMessage("Hi", UUID.randomUUID(), new Date());
//    UUID[] membersID2 = new UUID[]{message10.getSenderID(), message20.getSenderID(), message30.getSenderID()};
//    private TextMessage[] messages2 = new TextMessage[]{message10, message20, message30};
//    Conversation conversation2 = new Conversation("13GROUP second", UUID.randomUUID(),
//            membersID2, message10.getSenderID(), messages2);


    public List<Conversation> getConversations()
    {
        return conversationList;
    }

    public Conversation getConversationMessages(UUID uuid)
    {
//        for (int i = 0; i < conversations.length; i++)
//        {
//            if (uuid.equals(conversations[i].getConversationID()))
//            {
//                return conversations[i];
//            }
//        }
//        return null;
        return conversationList.stream().filter(conversation -> conversation.getConversationID() == uuid).findAny().orElse(null);
    }

//    private Conversation[] conversations = new Conversation[]{conversation, conversation2};
//
//    /** Singleton Realization **/
//
//    private ConversationManager conversationz = new ConversationManager(conversations);
//
//    public ConversationManager getConversations()
//    {
//        return conversationz;
//    }
//
//    public Conversation getConversationMessages(UUID uuid)
//    {
//        for (int i = 0; i < conversationz.getConversations().length; i++)
//        {
//            if (uuid.equals(conversationz.getConversations()[i].getConversationID()))
//            {
//                return conversationz.getConversations()[i];
//            }
//        }
//        return null;
//    }

}
