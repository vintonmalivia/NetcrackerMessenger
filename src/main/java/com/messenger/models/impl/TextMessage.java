package com.messenger.models.impl;
import com.messenger.models.AbstractMessage;

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

    @Override
    public String toString()
    {
        return "Text of message: " + "\"" + textOfMessage + "\"" + ", sender's ID:  " +  senderID
                + ", date of sending:  " + dateOfSending;
    }

}
