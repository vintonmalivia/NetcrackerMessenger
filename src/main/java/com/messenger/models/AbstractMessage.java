package com.messenger.models;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public abstract class AbstractMessage implements Serializable
{
     private static final String TIMEZONE = "GMT+04:00";
     private static final String PATTERN = "dd-MM-yyyy HH:mm";
     protected UUID senderID;

     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PATTERN, timezone = TIMEZONE) // TODO: (normal) А еще лучше, если таймзона будет рассчитываться исходя из текущей таймзоны
     protected Date dateOfSending; // TODO: (low) в Java 8 появился класс LocalDateTime. Можно попробовать поработать с ним. Он лучше

     public AbstractMessage() {}

     // TODO: (additional) Существует очень полезная библиотека lombok. Прикручивается как зависимость maven в pom.xml. В нем есть свои плюсы и свои минусы.
     // TODO: можно попробовать поработать с lombok и посмотреть аннотации @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString и другие...
     public UUID getSenderID() {return senderID;}

     public void setSenderID(UUID senderID) {this.senderID = senderID;}

     public Date getDateOfSending() {return dateOfSending;}

     public void setDateOfSending(Date dateOfSending) {this.dateOfSending = dateOfSending;}
}
