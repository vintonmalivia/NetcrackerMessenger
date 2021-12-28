package com.messenger.models;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class AbstractMessage implements Serializable
{
     protected UUID senderID;

     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone = "GMT+04:00")
     protected Date dateOfSending;

     public AbstractMessage() {}

     public UUID getSenderID() {return senderID;}

     public void setSenderID(UUID senderID) {this.senderID = senderID;}

     public void setMessageRead(boolean MessageRead) {
     }

     public Date getDateOfSending() {return dateOfSending;}

     public void setDateOfSending(Date dateOfSending) {this.dateOfSending = dateOfSending;}
}
