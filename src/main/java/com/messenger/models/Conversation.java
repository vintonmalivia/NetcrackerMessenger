package com.messenger.models;
import com.messenger.repository.ConversationsDAO;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

// TODO: (high) Нужно убрать расширение от ConversationsDAO, поскольку ConversationsDAO - класс для работы с данными, а Conversation - класс данных (энтити)
public class Conversation extends ConversationsDAO implements Serializable
{
    private String nameOfConversation; // TODO: (low) может, просто name?
    private UUID conversationID; // TODO: (low) может, просто id? Он больше ничьим быть не может по логике
    private UUID creatorID;
    private List<UUID> membersID;
    private List<AbstractMessage> messagesInConversation; // TODO: (low) может, просто messages?

    public Conversation() {
    }

    // TODO: (normal) Можно добавить конструктор без участников. Чтобы можно было добавить их позже
    public Conversation(String nameOfConversation, UUID conversationID, UUID creatorID, List<UUID> membersID, List<AbstractMessage> messagesInConversation) {
        this.nameOfConversation = nameOfConversation;
        this.conversationID = conversationID;
        this.creatorID = creatorID;
        this.membersID = membersID;
        this.messagesInConversation = messagesInConversation;
    }

    // TODO: (low) Название класса уже говорит само за себя. Достаточно называть методы как get<Поле> и set<Поле>: getName, setName, getId, setId
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

    // TODO: (normal) Добавить методы добавления/удаления сообщения, добавления/удаления участников
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
