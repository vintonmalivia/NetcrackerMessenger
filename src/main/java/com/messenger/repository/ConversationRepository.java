package com.messenger.repository;

import com.messenger.models.Conversation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConversationRepository extends CrudRepository<Conversation, UUID> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM conversation_members WHERE conv_id " +
            "IN (SELECT id FROM conversations WHERE creator_id = CAST (:uuid AS varchar))", nativeQuery = true)
    void deleteConversationMembers(@Param("uuid") UUID uuid);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM abstract_messages WHERE id_conversation " +
            "IN (SELECT id FROM conversations WHERE creator_id = CAST (:uuid AS varchar))", nativeQuery = true)
    void deleteMessagesFromDeletedConversations(@Param("uuid") UUID uuid);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM conversations WHERE creator_id = CAST (:uuid AS varchar)", nativeQuery = true)
    void deleteUserCreatedConversations(@Param("uuid") UUID uuid);

    @Query(value = "SELECT * FROM conversation_members JOIN conversations c on c.id = conversation_members.conv_id " +
            "WHERE prof_id = CAST (:uuid AS varchar)", nativeQuery = true)
    List<Conversation> getProfileConversations(@Param("uuid") UUID uuid);


}
