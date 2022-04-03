package com.messenger.serializers.impl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.messenger.models.Conversation;
import com.messenger.serializers.Serializer;

import java.io.IOException;

public class XMLSerializer implements Serializer
{
    public String serialize(Conversation conversation) throws IOException
    {
        XmlMapper mapper = new XmlMapper();
        return mapper.writeValueAsString(conversation);
    }
    public Conversation deserialize(String conversationXMLString) throws IOException
    {
        XmlMapper mapper = new XmlMapper();
        return mapper.readValue(conversationXMLString, Conversation.class);
    }
}
