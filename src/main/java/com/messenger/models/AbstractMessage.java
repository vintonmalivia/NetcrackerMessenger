package com.messenger.models;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Table(name = "abstract_messages")
@Entity
public abstract class AbstractMessage implements Serializable
{
     @Id
     private UUID id;

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

     public void setId(UUID id) {
          this.id = id;
     }

     public UUID getId() {
          return id;
     }
}
