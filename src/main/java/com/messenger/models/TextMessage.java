package com.messenger.models;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class TextMessage extends AbstractMessage implements Serializable
{
    private String textOfMessage;

    public TextMessage(){};

    public TextMessage(String textOfMessage, UUID senderID, Date dateOfSending)
    {
        this.senderID = senderID;
        this.dateOfSending = dateOfSending;
        this.textOfMessage = textOfMessage;
    }

    public String getTextOfMessage()
    {
        return textOfMessage;
    }

    public void setTextOfMessage(String textOfMessage)
    {
        this.textOfMessage = textOfMessage;
    }

    // TODO: (high) Геттеры и сеттеры у тебя уже есть в абстрактном классе. Нет смысла переопределять их здесь
    public UUID getSenderID()
    {
        return senderID;
    }

    public void setSenderID(UUID senderID)
    {
        this.senderID = senderID;
    }

    public Date getDateOfSending()
    {
        return dateOfSending;
    }

    public void setDateOfSending(Date dateOfSending)
    {
        this.dateOfSending = dateOfSending;
    }


    @Override
    public String toString()
    {
        return "Text of message: " + "\"" + textOfMessage + "\"" + ", sender's ID:  " +  senderID
                + ", date of sending:  " + dateOfSending;
    }

}
