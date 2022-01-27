package com.messenger.models.impl;
import com.messenger.models.AbstractMessage;
import com.messenger.models.Profile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Table(name = "text_messages")
@Entity
public class TextMessage extends AbstractMessage implements Serializable
{
    @Column(name = "text", nullable = false)
    private String textOfMessage;

    public TextMessage(){}

    public TextMessage(String textOfMessage, Profile sender)
    {
        this.sender = sender;
        this.dateOfSending = new Date();
        this.textOfMessage = textOfMessage;
    }

    public TextMessage(String textOfMessage, Profile sender, Date dateOfSending)
    {
        this.sender = sender;
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
        return "Text of message: " + "\"" + textOfMessage + "\"" + ", sender's ID:  " +  sender
                + ", date of sending:  " + dateOfSending;
    }

}
