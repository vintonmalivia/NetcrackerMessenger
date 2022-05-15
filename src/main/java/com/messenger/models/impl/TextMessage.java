package com.messenger.models.impl;

import com.messenger.models.AbstractMessage;
import com.messenger.models.Profile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

import static com.messenger.constants.tables.TableNames.TEXT_MESSAGES;

@Table(name = TEXT_MESSAGES)
@Entity
public class TextMessage extends AbstractMessage implements Serializable // TODO: AbstractMessage уже имплементирует
//                                                                           Serializable. Здесь не нужно это прописывать снова
{
    private static abstract class ColumnNames
    {
        private static final String TEXT = "text"; // TODO: Сделай text_value. Это будет более правильным
    }

    @Column(name = ColumnNames.TEXT, nullable = false)
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
