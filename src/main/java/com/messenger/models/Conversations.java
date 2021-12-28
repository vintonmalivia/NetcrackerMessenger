package com.messenger.models;

import java.util.List;

public class Conversations
{
    List<Conversation> allConversations;

    public Conversations() {
    }


    public Conversations(List<Conversation> allConversations) {
        this.allConversations = allConversations;
    }

    public List<Conversation> getAllConversations() {
        return allConversations;
    }

    public void setAllConversations(List<Conversation> allConversations) {
        this.allConversations = allConversations;
    }
}
