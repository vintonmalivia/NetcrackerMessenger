package com.messenger.repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.messenger.models.Conversation;

import java.io.*;

// TODO: (normal) Предлагаю создать интерфейс ConversationsDAO. А этот класс будет его имплементацией (аксесс к данным через файлы).
//  В ConversationsDAO должны быть самые основные методы CRUD. Как минимум createConversation (сохранение в файл) и
//  getConversationById (получение из файла). Ну и из файла удобнее брать все сразу чаты: getAllConversation.
//  Нужно подумать, как сделать возможным сохранение в файл без передачи потока в параметры методов.
//  Зачем так делать: не во всех имплементациях этот поток будет нужен. Пример: бд, для бд поток не используется.
//  А передавать ненужный параметр в метод - плохо. Самое простое решение, которое я вижу - создать поле-имя файла, с которым
//  мы будем работать, а потоки создавать уже в методах. Имя файла пусть фигурирует в файле application.properties или
//  и application.yaml (нужно почитать про то, что это такое и как оттуда доставать проперти)

// TODO: (high) Тоже будет @Component, тоже будем инжектить
public class ConversationsDAO implements Serializable
{
    // TODO: (normal) Надо бы переименовать в createConversation
    public static void serializeConversation(Conversation conversation, OutputStream out) throws IOException
    {
        ObjectOutputStream temp = new ObjectOutputStream(out);
        temp.writeObject(conversation);
    }

    // TODO: (normal) Надо бы переименовать в getConversation
    public static Conversation deserializeConversation(InputStream in) throws IOException, ClassNotFoundException
    {
        ObjectInputStream temp = new ObjectInputStream(in);
        return (Conversation) temp.readObject();
    }
    // TODO: (high) Давай все-таки в файлах хранить в виде байтов. Все эти методы лучше переместить в классы сериализаторов.
    //  Нужно создать интерфейс - Serializer, и сделать в нем два метода: serialize(объект) и deserialize(объект). Сериализаторов у тебя всего пока 3 штуки:
    //  1) JSON (при сериализации берем объект и сериализуем в JSON-строку, при десериализации берем строку и строим объект);
    //  2) YAML (то же самое, но с YAML строкой);
    //  3) Байты (не обязательно делать сериализатор, но можно)
    public static void serializeConversationToJSONFile(Conversation conversation, File newFile) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(newFile, conversation);
    }
    // TODO: (normal) - Пойдет в сериализатор JSON
    public static Conversation deserializeConversationFromJSONFile(File newFile) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(newFile, Conversation.class);
    }
    // TODO: (normal) - Пойдет в сериализатор YAML
    public static void serializeConversationToYAMLFile(Conversation conversation, File newFile) throws IOException
    {
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        om.writeValue(newFile, conversation);
    } // TODO: (normal) - Пойдет в сериализатор YAML
    public static Conversation deserializeConversationFromYAMLFile(File newFile) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(newFile, Conversation.class);
    }
}
