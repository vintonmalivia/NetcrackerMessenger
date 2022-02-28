package com.messenger.models;
import com.messenger.exceptions.NoCreatorIDException;
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

    @Column(name = ColumnNames.NAME, nullable = false)
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ColumnNames.ID, nullable = false, unique = true,  columnDefinition = "varchar(36)")
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = ColumnNames.CREATOR_ID)
    private Profile creator;

    //TODO: @ManyToOne, @OneToMany

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

    // TODO: (normal) Добавить методы добавления/удаления сообщения, добавления/удаления участников
    //TODO: Чекнуть lists, maps, sets

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
