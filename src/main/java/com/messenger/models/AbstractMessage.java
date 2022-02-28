package com.messenger.models;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import static com.messenger.constants.tables.TableNames.ABSTRACT_MESSAGES;

@Table(name = ABSTRACT_MESSAGES)
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@MappedSuperclass
public abstract class AbstractMessage implements Serializable
{
     private static abstract class ColumnNames
     {
          private static final String ID = "id";
          private static final String SENDER_ID = "sender_id";
          private static final String DATE = "date";
     }

     //TODO: TIMEZONE
     private static final String TIMEZONE = "GMT+04:00";
     private static final String PATTERN = "dd-MM-yyyy HH:mm";

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(name = ColumnNames.ID, nullable = false, unique = true, columnDefinition = "varchar(36)")
     private UUID id;

     @ManyToOne
     @JoinColumn(name = ColumnNames.SENDER_ID,  columnDefinition = "varchar(36)")
     protected Profile sender;

     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PATTERN, timezone = TIMEZONE)
     @Column(name = ColumnNames.DATE, nullable = false)
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
