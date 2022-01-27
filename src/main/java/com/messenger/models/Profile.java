package com.messenger.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Table(name = "profile")
@Entity
public class Profile
{
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID userID;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
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
