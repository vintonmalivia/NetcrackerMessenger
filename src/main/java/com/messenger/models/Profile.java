package com.messenger.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

import static com.messenger.constants.tables.TableNames.PROFILE;

@Table(name = PROFILE)
@Entity
public class Profile
{
    private static abstract class ColumnNames
    {
        private static final String NAME = "name";
        private static final String SURNAME = "surname";
        private static final String ID = "id";
    }

    private static abstract class TypeAnnotation {
        private static final String UUID_CHAR_TYPE = "org.hibernate.type.UUIDCharType";
    }

    @Column(name = ColumnNames.NAME, nullable = false)
    private String name;

    @Column(name = ColumnNames.SURNAME, nullable = false)
    private String surname;

    @Id
    @Type(type = TypeAnnotation.UUID_CHAR_TYPE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ColumnNames.ID, nullable = false, unique = true)
    private UUID userID;

    @Transient
    private boolean isSpamming;

    public Profile() {}

    public Profile(String name, String surname, UUID userID)
    {
        this.name = name;
        this.surname = surname;
        this.userID = userID;
    }

    public Profile(String name, String surname, UUID userID, boolean isSpamming)
    {
        this.name = name;
        this.surname = surname;
        this.userID = userID;
        this.isSpamming = isSpamming;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public boolean getSpammingStatus() {
        return isSpamming;
    }

    public void setSpammingStatus(boolean isSpamming) {
        this.isSpamming = isSpamming;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", userID=" + userID +
                '}';
    }
}
