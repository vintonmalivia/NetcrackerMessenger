package com.messenger.models;
import javax.persistence.*;
import java.util.UUID;

@Table(name = "profile") /* Todo: В константы */
@Entity
public class Profile
{
    @Column(name = "name" /* Todo: В константы */, nullable = false)
    private String name;

    @Column(name = "surname" /* Todo: В константы */, nullable = false)
    private String surname;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id" /* Todo: В константы */, nullable = false, unique = true)
    private UUID userID;

    @Column(name = "login" /* Todo: В константы *//*, nullable = false, unique = true*/)
    private String login;

    @Column(name = "password" /* Todo: В константы *//*, nullable = false*/)
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
