package com.messenger.repository.impl;
import com.messenger.models.Conversation;
import com.messenger.repository.IConversationsDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.List;

// TODO: (normal) Предлагаю создать интерфейс ConversationsDAO. А этот класс будет его имплементацией (аксесс к данным через файлы).
//  В ConversationsDAO должны быть самые основные методы CRUD. Как минимум createConversation (сохранение в файл) и
//  getConversationById (получение из файла). Ну и из файла удобнее брать все сразу чаты: getAllConversation.
//  Нужно подумать, как сделать возможным сохранение в файл без передачи потока в параметры методов.
//  Зачем так делать: не во всех имплементациях этот поток будет нужен. Пример: бд, для бд поток не используется.
//  А передавать ненужный параметр в метод - плохо. Самое простое решение, которое я вижу - создать поле-имя файла, с которым
//  мы будем работать, а потоки создавать уже в методах. Имя файла пусть фигурирует в файле application.properties или
//  и application.yaml (нужно почитать про то, что это такое и как оттуда доставать проперти)

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

}
