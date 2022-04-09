package com.messenger.repository;

import com.messenger.models.impl.TextMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends CrudRepository<TextMessage, UUID> {

    @Query(value = "SELECT * FROM text_messages WHERE id_conversation = CAST (:uuid AS varchar)", nativeQuery = true)
    List<TextMessage> getMessages(@Param("uuid") UUID uuid);

//    @Query(value = "INSERT INTO text_messages VALUES (id_conversation = :uuid)", nativeQuery = true)
//    void setConversationId(@Param("uuid") UUID uuid);
}
