package com.messenger.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.messenger.models.Conversation;

import java.io.*;
import java.util.List;

public interface IConversationsDAO
{
    void createConversation(Conversation conversation) throws IOException;

    void createAllConversations(List<Conversation> conversations) throws IOException;

    Conversation getConversation() throws IOException, ClassNotFoundException;

    // TODO: Implement getAll and others
}
