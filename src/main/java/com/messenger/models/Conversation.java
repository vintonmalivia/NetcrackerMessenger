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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_id")
    private Profile creator;

    //TODO: @ManyToOne, @OneToMany

    @JoinTable(
            name = "conversation_members",
            joinColumns = @JoinColumn(
            name = "conv_ID",
            referencedColumnName = "id"
    ),
    inverseJoinColumns = @JoinColumn(
            name = "prof_ID",
            referencedColumnName = "id"
    ))
    @ManyToMany
    private List<Profile> members;

    @OneToMany
    private List<AbstractMessage> messages;

    public Conversation() {}

    public Conversation(String name, UUID id, Profile creator)
    {
        this.name = name;
        this.id = id;
        this.creator = creator;
        this.members = new ArrayList<>();
        this.members.add(creator);
    }

    public Conversation(String name, UUID id, Profile creator, List<AbstractMessage> messages)
    {
        this.name = name;
        this.id = id;
        this.creator = creator;
        this.messages = messages;
        this.members = new ArrayList<>();
        this.members.add(creator);
    }

    public Conversation(String name, UUID id, Profile creator, List<Profile> members, List<AbstractMessage> messages)
    {
        this.name = name;
        this.id = id;
        this.creator = creator;
        this.members = members;
        this.messages = messages;
        if (!members.contains(creator))
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

    public Profile getCreator() {
        return creator;
    }

    public void setCreator(Profile creator) {
        this.creator = creator;
    }

    public List<Profile> getMembers() {
        return members;
    }

    public void setMembersID(List<Profile> members) {
        this.members = members;
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
                ", creatorID=" + creator +
                ", membersID=" + members +
                ", messages=" + messages +
                '}';
    }
}
