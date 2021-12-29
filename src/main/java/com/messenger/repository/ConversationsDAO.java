package com.messenger.repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.messenger.models.Conversation;

import java.io.*;

public class ConversationsDAO implements Serializable
{
    public static void serializeConversation(Conversation conversation, OutputStream out) throws IOException
    {
        ObjectOutputStream temp = new ObjectOutputStream(out);
        temp.writeObject(conversation);
    }

    public static Conversation deserializeConversation(InputStream in) throws IOException, ClassNotFoundException
    {
        ObjectInputStream temp = new ObjectInputStream(in);
        return (Conversation) temp.readObject();
    }

    public static void serializeConversationToJSONFile(Conversation conversation, File newFile) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(newFile, conversation);
    }

    public static Conversation deserializeConversationFromJSONFile(File newFile) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(newFile, Conversation.class);
    }

    public static void serializeConversationToYAMLFile(Conversation conversation, File newFile) throws IOException
    {
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        om.writeValue(newFile, conversation);
    }
    public static Conversation deserializeConversationFromYAMLFile(File newFile) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(newFile, Conversation.class);
    }
}
