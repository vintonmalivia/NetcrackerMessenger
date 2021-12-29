package com.messenger.models;
import com.messenger.repository.ConversationsDAO;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Conversation extends ConversationsDAO implements Serializable
{
    private String nameOfConversation;
    private UUID conversationID;
    private UUID creatorID;
    private List<UUID> membersID;
    private List<AbstractMessage> messagesInConversation;

    public Conversation() {
    }

    public Conversation(String nameOfConversation, UUID conversationID, UUID creatorID, List<UUID> membersID, List<AbstractMessage> messagesInConversation) {
        this.nameOfConversation = nameOfConversation;
        this.conversationID = conversationID;
        this.creatorID = creatorID;
        this.membersID = membersID;
        this.messagesInConversation = messagesInConversation;
    }

    public String getNameOfConversation() {
        return nameOfConversation;
    }

    public void setNameOfConversation(String nameOfConversation) {
        this.nameOfConversation = nameOfConversation;
    }

    public UUID getConversationID() {
        return conversationID;
    }

    public void setConversationID(UUID conversationID) {
        this.conversationID = conversationID;
    }

    public UUID getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(UUID creatorID) {
        this.creatorID = creatorID;
    }

    public List<UUID> getMembersID() {
        return membersID;
    }

    public void setMembersID(List<UUID> membersID) {
        this.membersID = membersID;
    }

    public List<AbstractMessage> getMessagesInConversation() {
        return messagesInConversation;
    }

    public void setMessagesInConversation(List<AbstractMessage> messagesInConversation) {
        this.messagesInConversation = messagesInConversation;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "nameOfConversation='" + nameOfConversation + '\'' +
                ", conversationID=" + conversationID +
                ", creatorID=" + creatorID +
                ", membersID=" + membersID +
                ", messagesInConversation=" + messagesInConversation +
                '}';
    }
}
