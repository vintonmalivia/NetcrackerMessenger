package com.messenger.models;

import com.messenger.exceptions.NoCreatorIDException;
import com.messenger.models.impl.TextMessage;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.messenger.constants.tables.TableNames.CONVERSATIONS;

@Table(name = CONVERSATIONS)
@Entity
public class Conversation implements Serializable
{
    private static abstract class ColumnNames
    {
        private static final String NAME = "name";
        private static final String ID = "id";
        private static final String CREATOR_ID = "creator_id";
        private static final String CONV_ID = "conv_id";
        private static final String PROF_ID = "prof_id";
    }

    private static abstract class TableNames
    {
        private static final String CONVERSATION_MEMBERS = "conversation_members";
    }

    private static abstract class ColumnDefinition
    {
        private static final String VARCHAR_36 = "varchar(36)";
    }

    private static abstract class TypeAnnotation
    {
        private static final String UUID_CHAR_TYPE = "org.hibernate.type.UUIDCharType";
    }

    @Column(name = ColumnNames.NAME, nullable = false)
    private String name;

    @Id
    @Type(type = TypeAnnotation.UUID_CHAR_TYPE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ColumnNames.ID, nullable = false, unique = true,  columnDefinition = ColumnDefinition.VARCHAR_36)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = ColumnNames.CREATOR_ID)
    private Profile creator;

    @JoinTable(
            name = TableNames.CONVERSATION_MEMBERS,
            joinColumns = @JoinColumn(
            name = ColumnNames.CONV_ID,
            referencedColumnName = ColumnNames.ID
    ),
    inverseJoinColumns = @JoinColumn(
            name = ColumnNames.PROF_ID,
            referencedColumnName = ColumnNames.ID
    ))
    @ManyToMany
    private List<Profile> members = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) //TODO: EAGER FETCH
    @JoinColumn(name = "id_conversation")
    private List<AbstractMessage> messages = new ArrayList<>();

    public Conversation() {}

    public Conversation(String name, UUID id, Profile creator)
    {
        this.name = name;
        this.id = id;
        this.creator = creator;
        this.members.add(creator);
    }

    public Conversation(String name, UUID id, Profile creator, List<AbstractMessage> messages)
    {
        this.name = name;
        this.id = id;
        this.creator = creator;
        this.messages = messages;
        this.members.add(creator);
    }

    public Conversation(String name, UUID id, Profile creator, List<Profile> members, List<AbstractMessage> messages) throws NoCreatorIDException {
        this.name = name;
        this.id = id;
        this.creator = creator;
        this.members = members;
        this.messages = messages;
        if (!members.contains(creator))
        {
            throw new NoCreatorIDException();
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

    public void addMember(Profile member)
    {
        members.add(member);
    }

    public void deleteMember(Profile member)
    {
        members.remove(member);
    }

    public void addMessage(TextMessage newMessage)
    {
        messages.add(newMessage);
    }

    public void deleteMessage(TextMessage textMessage)
    {
        messages.remove(textMessage);
    }

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
