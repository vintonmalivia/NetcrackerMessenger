package com.messenger;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class TextMessage extends AbstractMessage implements Serializable
{
    private String textOfMessage;

    public TextMessage(){};

    public TextMessage(String textOfMessage, UUID senderID, Date dateOfSending, boolean isMessageRead)
    {
        this.senderID = senderID;
        this.dateOfSending = dateOfSending;
        this.isMessageRead = isMessageRead;
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

    public boolean isMessageRead()
    {
        return isMessageRead;
    }

    public void setMessageRead(boolean messageRead)
    {
        isMessageRead = messageRead;
    }

    @Override
    public String toString()
    {
        return "Text of message: " + "\"" + textOfMessage + "\"" + ", sender's ID:  " +  senderID
                + ", date of sending:  " + dateOfSending + ", is message read: " + isMessageRead;
    }

}
