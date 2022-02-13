package com.messenger.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.messenger.models.Conversation;
import org.springframework.data.repository.CrudRepository;

import java.io.*;
import java.util.List;

/* Todo: В принципе, этот класс врятли нам пригодится. Можно удалить */
public interface IConversationsDAO
{
    void createConversation(Conversation conversation) throws IOException;

    void createAllConversations(List<Conversation> conversations) throws IOException;

    Conversation getConversation() throws IOException, ClassNotFoundException;

    List<Conversation> getAllConversations() throws IOException, ClassNotFoundException;
    // TODO: Implement getAll and others
}
