package com.messenger.repository;

import com.messenger.models.impl.TextMessage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends CrudRepository<TextMessage, UUID> {

    @Query(value = "SELECT * FROM text_messages WHERE id_conversation = CAST (:uuid AS varchar) ORDER BY date", nativeQuery = true)
    List<TextMessage> getMessages(@Param("uuid") UUID uuid);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM abstract_messages WHERE sender_id = CAST (:uuid AS varchar)",nativeQuery = true)
    void deleteMessagesByProfile(@Param("uuid") UUID uuid);
}
