package com.messenger.models;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Table(name = "abstract_messages")
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@MappedSuperclass
public abstract class AbstractMessage implements Serializable
{
     //TODO: TIMEZONE
     private static final String TIMEZONE = "GMT+04:00";
     private static final String PATTERN = "dd-MM-yyyy HH:mm";

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(name = "id" /* Todo: В константы */, nullable = false, unique = true)
     private UUID id;

     @ManyToOne
     @JoinColumn(name = "sender_id" /* Todo: В константы */)
     protected Profile sender;

     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PATTERN, timezone = TIMEZONE)
     @Column(name = "date" /* Todo: В константы */, nullable = false)
     protected Date dateOfSending;

     public AbstractMessage() {}

     public Profile getSender() {return sender;}

     public void setSenderID(Profile sender) {this.sender = sender;}

     public Date getDateOfSending() {return dateOfSending;}

     public void setDateOfSending(Date dateOfSending) {this.dateOfSending = dateOfSending;}

     public void setId(UUID id) {
          this.id = id;
     }

     public UUID getId() {
          return id;
     }
}
