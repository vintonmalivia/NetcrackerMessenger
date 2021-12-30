package com.messenger.models;
import java.util.UUID;

public class Profile
{
    String nameOfUser;  // TODO: (low) может, просто name?
    String surnameOfUser; // TODO: (low) может, просто surname?
    UUID userID;
    String login;
    String password;

    public Profile(String nameOfUser, String surnameOfUser, UUID userID, String login, String password)
    {
        this.nameOfUser = nameOfUser;
        this.surnameOfUser = surnameOfUser;
        this.userID = userID;
        this.login = login;
        this.password = password;
    }

    public String getNameOfUser() {
        return nameOfUser;
    }

    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }

    public String getSurnameOfUser() {
        return surnameOfUser;
    }

    public void setSurnameOfUser(String surnameOfUser) {
        this.surnameOfUser = surnameOfUser;
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
