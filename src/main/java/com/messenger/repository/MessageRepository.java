package com.messenger.repository;

import com.messenger.models.impl.TextMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends CrudRepository<TextMessage, UUID> {

    @Query(value = "SELECT * FROM text_messages WHERE id_conversation = CAST (:convID AS varchar) ORDER BY date",
            nativeQuery = true)
    List<TextMessage> getMessages(@Param("convID" /* В константы* */) UUID convID);

    @Query(value = "SELECT count(*) FROM abstract_messages WHERE sender_id = CAST (:senderID AS varchar) " +
            "AND CAST(date AS timestamp) BETWEEN (now() - INTERVAL '1 minute') and now()",
            nativeQuery = true)
    int getNumberOfMessagesInLastMinute(@Param("senderID" /* В константы* */) UUID senderID);
}
