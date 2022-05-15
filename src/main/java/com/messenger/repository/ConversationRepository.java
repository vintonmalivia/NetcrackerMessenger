package com.messenger.repository;

import com.messenger.models.Conversation;
import com.messenger.models.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConversationRepository extends CrudRepository<Conversation, UUID> {

    @Query(value = "SELECT * FROM conversation_members JOIN conversations c on c.id = conversation_members.conv_id " +
            "WHERE prof_id = CAST (:profileID AS varchar)", nativeQuery = true)
    List<Conversation> getProfileConversations(@Param("profileID" /* В константы* */) UUID profileID);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM conversations WHERE id NOT IN (SELECT conv_id FROM conversation_members)",
            nativeQuery = true)
    void deleteConversationIfNoMembers();

    @Query(value = "SELECT * FROM conversation_members JOIN profile p on p.id = conversation_members.prof_id " +
            "WHERE conv_id = CAST (:convID AS varchar)", nativeQuery = true)
    List<Profile> getMembers(@Param("convID" /* В константы* */) UUID convID);

    @Query(value = "SELECT exists(SELECT * FROM conversation_members WHERE conv_id = CAST (:convID AS varchar)" +
            " AND prof_id = CAST (:profileID AS varchar))", nativeQuery = true)
    // TODO: isInConversation - кто в чате? Кто? Что это? isUserInConversation
    boolean isInConversation(@Param("convID" /* В константы* */) UUID convID, @Param("profileID" /* В константы* */) UUID profileID);

    //  Methods if deleting user
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM conversation_members WHERE prof_id = CAST (:profileID AS varchar)", nativeQuery = true)
    void deleteUserFromConversationMembers(@Param("profileID" /* В константы* */) UUID profileID);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM abstract_messages WHERE sender_id = CAST (:senderID AS varchar)", nativeQuery = true)
    void deleteMessagesFromDeletedUser(@Param("senderID" /* В константы* */) UUID senderID);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE conversations SET creator_id = null WHERE creator_id = CAST (:creatorID AS varchar)",
            nativeQuery = true)
    void deleteCreatorID(@Param("creatorID" /* В константы* */) UUID creatorID);

    //  Methods if user leaves conversation
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM conversation_members WHERE prof_id = CAST (:profileID AS varchar) " +
            "AND conv_id = CAST (:convID AS varchar)", nativeQuery = true)
    // TODO: removeUserFromConversation
    void leaveFromConversation(@Param("profileID" /* В константы* */) UUID profileID, @Param("convID" /* В константы* */) UUID convID);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM abstract_messages WHERE sender_id = CAST (:senderID AS varchar) " +
            "AND id_conversation = CAST (:convID AS varchar)", nativeQuery = true)
    void deleteAbandonedUserMessages(@Param("senderID" /* В константы* */) UUID senderID, @Param("convID" /* В константы* */) UUID convID);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE conversations SET creator_id = null WHERE creator_id = CAST (:creatorID AS varchar) " +
            "AND id = CAST (:convID AS varchar)", nativeQuery = true)
    void deleteCreatorIDIfUserLeaving(@Param("creatorID" /* В константы* */) UUID creatorID, @Param("convID" /* В константы* */) UUID convID);

}
