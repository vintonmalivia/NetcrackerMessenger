package com.messenger.repository;
import com.messenger.models.Conversation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface IDatabaseConversationDAO extends CrudRepository<Conversation, UUID> {
}
