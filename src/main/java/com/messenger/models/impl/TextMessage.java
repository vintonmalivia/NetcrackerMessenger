package com.messenger.models.impl;
import com.messenger.models.AbstractMessage;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Table(name = "text_messages")
@Entity
public class TextMessage extends AbstractMessage implements Serializable
{
    private String textOfMessage;

    public TextMessage(){}

    public TextMessage(String textOfMessage, UUID senderID)
    {
        this.senderID = senderID;
        this.dateOfSending = new Date();
        this.textOfMessage = textOfMessage;
    }

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
