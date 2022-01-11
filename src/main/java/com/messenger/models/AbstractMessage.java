package com.messenger.models;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public abstract class AbstractMessage implements Serializable
{
     //TODO: TIMEZONE
     private static final String TIMEZONE = "GMT+04:00";
     private static final String PATTERN = "dd-MM-yyyy HH:mm";
     protected UUID senderID;

     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PATTERN, timezone = TIMEZONE)
     protected Date dateOfSending;

     public AbstractMessage() {}

     public UUID getSenderID() {return senderID;}

     public void setSenderID(UUID senderID) {this.senderID = senderID;}

     public Date getDateOfSending() {return dateOfSending;}

     public void setDateOfSending(Date dateOfSending) {this.dateOfSending = dateOfSending;}
}
