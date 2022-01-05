package com.messenger.serializers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.messenger.models.Conversation;

import java.io.File;
import java.io.IOException;

public class YAMLSerializer {
    static void serializeConversationToYAMLFile(Conversation conversation, File newFile) throws IOException
    {
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        om.writeValue(newFile, conversation);
    }
    static Conversation deserializeConversationFromYAMLFile(File newFile) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(newFile, Conversation.class);
    }
}
