package com.messenger.repository.impl;
import com.messenger.models.Conversation;
import com.messenger.repository.IConversationsDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FileConversationsDAO implements IConversationsDAO, Serializable
{
    @Value("file.path") // TODO: В константы
    private static String fileName;

    public void createConversation(Conversation conversation) throws IOException
    {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        ObjectOutputStream temp = new ObjectOutputStream(outputStream);
        temp.writeObject(conversation);
    }

    public void createAllConversations(List<Conversation> conversations) throws IOException
    {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        ObjectOutputStream temp = new ObjectOutputStream(outputStream);
        temp.writeObject(conversations);
    }

    public Conversation getConversation() throws IOException, ClassNotFoundException
    {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
        ObjectInputStream temp = new ObjectInputStream(inputStream);
        return (Conversation) temp.readObject();
    }

    public List<Conversation> getAllConversations() throws IOException, ClassNotFoundException
    {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
        ObjectInputStream temp = new ObjectInputStream(inputStream);
        return Collections.singletonList((Conversation) temp.readObject());
    }


    // TODO: Collections
    // TODO: createAll, getAll and others
}
