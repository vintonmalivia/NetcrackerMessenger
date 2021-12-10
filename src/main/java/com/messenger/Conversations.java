package com.messenger;

public class Conversations
{
    Conversation[] arrayOfConversations;

    public Conversations(Conversation[] arrayOfConversations)
    {
        this.arrayOfConversations = arrayOfConversations;
    }

    public Conversation[] getArrayOfConversations()
    {
        return arrayOfConversations;
    }

    public void setArrayOfConversations(Conversation[] arrayOfConversations)
    {
        this.arrayOfConversations = arrayOfConversations;
    }

}
