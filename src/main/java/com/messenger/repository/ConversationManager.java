package com.messenger.repository;
import com.messenger.models.Conversation;
import java.util.ArrayList;
import java.util.List;

public class ConversationManager
{
    private static ConversationManager instance;
    private List<Conversation> conversations;

    public static ConversationManager getInstance()
    {
        if (instance == null)
        {
            instance = new ConversationManager();
            instance.conversations = new ArrayList<>();
        }
        return instance;
    }

    public List<Conversation> getConversations()
    {
        return conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }
}
