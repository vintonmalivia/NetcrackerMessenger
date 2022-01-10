package com.messenger.serializers.impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.messenger.models.Conversation;
import com.messenger.serializers.Serializer;
import java.io.IOException;

public class YAMLSerializer implements Serializer
{
    public String serialize(Conversation conversation) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.writeValueAsString(conversation);
    }
    public Conversation deserialize(String conversationYAMLString) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(conversationYAMLString, Conversation.class);
    }
}
