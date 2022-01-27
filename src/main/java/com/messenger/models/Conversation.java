package com.messenger.models;
import org.hibernate.annotations.CollectionId;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Table(name = "conversations")
@Entity
public class Conversation implements Serializable
{
    @Column(name = "name", nullable = false)
    private String name;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    private UUID creatorID;

    //TODO: @ManyToOne, @OneToMany

    @ManyToMany
    private List<UUID> membersID;

    @OneToMany
    private List<AbstractMessage> messages;

    public Conversation() {}

    public Conversation(String name, UUID id, UUID creatorID)
    {
        this.name = name;
        this.id = id;
        this.creatorID = creatorID;
        this.membersID = new ArrayList<>();
        this.membersID.add(creatorID);
    }

    public Conversation(String name, UUID id, UUID creatorID, List<AbstractMessage> messages)
    {
        this.name = name;
        this.id = id;
        this.creatorID = creatorID;
        this.messages = messages;
        this.membersID = new ArrayList<>();
        this.membersID.add(creatorID);
    }

    public Conversation(String name, UUID id, UUID creatorID, List<UUID> membersID, List<AbstractMessage> messages) {
        this.name = name;
        this.id = id;
        this.creatorID = creatorID;
        this.membersID = membersID;
        this.messages = messages;
        if (!membersID.contains(creatorID))
        {
            // TODO: What if creator not in members list??? (Добавить валидацию листа участников чата)
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public List<AbstractMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<AbstractMessage> messages) {
        this.messages = messages;
    }

    // TODO: (normal) Добавить методы добавления/удаления сообщения, добавления/удаления участников
    //TODO: lists, maps, sets

    @Override
    public String toString() {
        return "Conversation{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", creatorID=" + creatorID +
                ", membersID=" + membersID +
                ", messages=" + messages +
                '}';
    }
}
