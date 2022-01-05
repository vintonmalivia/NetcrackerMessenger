package com.messenger.models;
import java.util.UUID;

public class Profile
{
    private String name;
    private String surname;
    private UUID userID;
    private String login;
    private String password;

    public Profile(String name, String surname, UUID userID, String login, String password)
    {
        this.name = name;
        this.surname = surname;
        this.userID = userID;
        this.login = login;
        this.password = password;
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
