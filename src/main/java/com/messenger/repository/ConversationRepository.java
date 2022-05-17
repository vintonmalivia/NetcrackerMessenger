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

    abstract class QueryParams{
        public static final String PROFILE_ID = "profileID";
        public static final String SENDER_ID = "senderID";
        public static final String CONVERSATION_ID = "convID";
        public static final String CREATOR_ID = "creatorID";
    }

    @Query(value = "SELECT * FROM conversation_members JOIN conversations c on c.id = conversation_members.conv_id " +
            "WHERE prof_id = CAST (:profileID AS varchar)", nativeQuery = true)
    List<Conversation> getProfileConversations(@Param(QueryParams.PROFILE_ID) UUID profileID);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM conversations WHERE id NOT IN (SELECT conv_id FROM conversation_members)",
            nativeQuery = true)
    void deleteConversationIfNoMembers();

    @Query(value = "SELECT * FROM conversation_members JOIN profile p on p.id = conversation_members.prof_id " +
            "WHERE conv_id = CAST (:convID AS varchar)", nativeQuery = true)
    List<Profile> getMembers(@Param(QueryParams.CONVERSATION_ID) UUID convID);

    @Query(value = "SELECT exists(SELECT * FROM conversation_members WHERE conv_id = CAST (:convID AS varchar)" +
            " AND prof_id = CAST (:profileID AS varchar))", nativeQuery = true)
    boolean isUserInConversation(@Param(QueryParams.CONVERSATION_ID) UUID convID,
                                 @Param(QueryParams.PROFILE_ID) UUID profileID);

    //  Methods if deleting user
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM conversation_members WHERE prof_id = CAST (:profileID AS varchar)", nativeQuery = true)
    void deleteUserFromConversationMembers(@Param(QueryParams.PROFILE_ID) UUID profileID);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM abstract_messages WHERE sender_id = CAST (:senderID AS varchar)", nativeQuery = true)
    void deleteMessagesFromDeletedUser(@Param(QueryParams.SENDER_ID) UUID senderID);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE conversations SET creator_id = null WHERE creator_id = CAST (:creatorID AS varchar)",
            nativeQuery = true)
    void deleteCreatorID(@Param(QueryParams.CREATOR_ID) UUID creatorID);

    //  Methods if user leaves conversation
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM conversation_members WHERE prof_id = CAST (:profileID AS varchar) " +
            "AND conv_id = CAST (:convID AS varchar)", nativeQuery = true)
    void removeUserFromConversation(@Param(QueryParams.PROFILE_ID) UUID profileID,
                                    @Param(QueryParams.CONVERSATION_ID) UUID convID);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM abstract_messages WHERE sender_id = CAST (:senderID AS varchar) " +
            "AND id_conversation = CAST (:convID AS varchar)", nativeQuery = true)
    void deleteAbandonedUserMessages(@Param(QueryParams.SENDER_ID) UUID senderID,
                                     @Param(QueryParams.CONVERSATION_ID) UUID convID);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE conversations SET creator_id = null WHERE creator_id = CAST (:creatorID AS varchar) " +
            "AND id = CAST (:convID AS varchar)", nativeQuery = true)
    void deleteCreatorIDIfUserLeaving(@Param(QueryParams.CREATOR_ID) UUID creatorID,
                                      @Param(QueryParams.CONVERSATION_ID) UUID convID);
}
