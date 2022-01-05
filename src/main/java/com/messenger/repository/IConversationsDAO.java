package com.messenger.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.messenger.models.Conversation;

import java.io.*;

public interface IConversationsDAO
{
    void createConversation(Conversation conversation) throws IOException;

    Conversation getConversation() throws IOException, ClassNotFoundException;

    // TODO: Implement createAll, getAll and others
}
