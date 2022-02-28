package com.messenger.models;
import javax.persistence.*;
import java.util.UUID;
import static com.messenger.constants.tables.TableNames.PROFILE;

@Table(name = PROFILE) /* Todo: В константы */
@Entity
public class Profile
{
    private static abstract class ColumnNames
    {
        private static final String NAME = "name";
        private static final String SURNAME = "surname";
        private static final String ID = "id";
    }

    @Column(name = ColumnNames.NAME, nullable = false)
    private String name;

    @Column(name = ColumnNames.SURNAME, nullable = false)
    private String surname;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ColumnNames.ID, nullable = false, unique = true)
    private UUID userID;

    /* TODO (question): (Additional info required) Будут ли в приложении роли? Admin, Simple User ... ? Если да, то предусмотреть такую возможность */

    public Profile(String name, String surname, UUID userID)
    {
        this.name = name;
        this.surname = surname;
        this.userID = userID;
    }

    public Profile() {}

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
}
