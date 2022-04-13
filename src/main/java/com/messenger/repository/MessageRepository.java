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

    @Query(value = "SELECT * FROM text_messages WHERE id_conversation = CAST (:uuid AS varchar)", nativeQuery = true)
    List<TextMessage> getMessages(@Param("uuid") UUID uuid);
}
