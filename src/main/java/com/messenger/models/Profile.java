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
        private static final String LOGIN = "login";
        private static final String PASSWORD = "password";
    }

    @Column(name = ColumnNames.NAME, nullable = false)
    private String name;

    @Column(name = ColumnNames.SURNAME, nullable = false)
    private String surname;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ColumnNames.ID, nullable = false, unique = true)
    private UUID userID;

    @Column(name = ColumnNames.LOGIN/*, nullable = false, unique = true*/)
    private String login;

    @Column(name = ColumnNames.PASSWORD/*, nullable = false*/)
    private String password;

    /* TODO (question): (Additional info required) Будут ли в приложении роли? Admin, Simple User ... ? Если да, то предусмотреть такую возможность */

    public Profile(String name, String surname, UUID userID, String login, String password)
    {
        this.name = name;
        this.surname = surname;
        this.userID = userID;
        this.login = login;
        this.password = password;
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }
}
