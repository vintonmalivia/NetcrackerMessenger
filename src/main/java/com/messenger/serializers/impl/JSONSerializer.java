package com.messenger.serializers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.messenger.models.Conversation;

import java.io.File;
import java.io.IOException;

public class JSONSerializer
{
    static void serializeConversationToJSONFile(Conversation conversation, File newFile) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(newFile, conversation);
    }

    static Conversation deserializeConversationFromJSONFile(File newFile) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(newFile, Conversation.class);
    }
}
