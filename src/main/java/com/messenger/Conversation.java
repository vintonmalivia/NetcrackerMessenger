package com.messenger;
import java.util.Arrays;
import java.util.UUID;

public class Conversation extends DataProcessing
{
    private String nameOfConversation;
    private UUID conversationID;
    private UUID creatorID;
    private UUID[] membersID;
    private TextMessage[] messagesInConversation;


    public Conversation(){};
    public Conversation(String nameOfConversation, UUID conversationID, UUID[] membersID, UUID creatorID, TextMessage[] messagesInConversation)
    {
        this.nameOfConversation = nameOfConversation;
        this.conversationID = conversationID;
        this.membersID = membersID;
        this.creatorID = creatorID;
        this.messagesInConversation = messagesInConversation;
    }

    public UUID[] getMembersID()
    {
        return membersID;
    }

    public void setMembersID(UUID[] membersID)
    {
        this.membersID = membersID;
    }

    public UUID getCreatorID()
    {
        return creatorID;
    }

    public void setCreatorID(UUID creatorID)
    {
        this.creatorID = creatorID;
    }

    public String getNameOfConversation() {
        return nameOfConversation;
    }

    public void setNameOfConversation(String nameOfConversation)
    {
        this.nameOfConversation = nameOfConversation;
    }

    public UUID getConversationID()
    {
        return conversationID;
    }

    public void setConversationID(UUID conversationID)
    {
        this.conversationID = conversationID;
    }

    public TextMessage[] getMessagesInConversation()
    {
        return messagesInConversation;
    }

    public void setMessagesInConversation(TextMessage[] messagesInConversation)
    {
        this.messagesInConversation = messagesInConversation;
    }

    @Override
    public String toString() {
        return "Conversation {" +
                "nameOfConversation = '" + nameOfConversation + '\'' +
                ", conversationID = " + conversationID +
                ", creatorID = " + creatorID +
                ", membersID = " + Arrays.toString(membersID) +
                ", messagesInConversation = " + Arrays.toString(messagesInConversation) +
                '}';
    }
}
