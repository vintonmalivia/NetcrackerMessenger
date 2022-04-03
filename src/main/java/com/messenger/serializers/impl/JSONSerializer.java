package com.messenger.serializers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.messenger.models.Conversation;
import com.messenger.serializers.Serializer;

import java.io.IOException;

public class JSONSerializer implements Serializer
{
    public String serialize(Conversation conversation) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(conversation);
    }
    public Conversation deserialize(String conversationJSONString) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(conversationJSONString, Conversation.class);
    }
}
