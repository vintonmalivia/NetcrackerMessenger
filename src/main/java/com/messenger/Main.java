package com.messenger;
import java.io.*;
import java.util.Date;
import java.util.UUID;
public class Main
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        TextMessage message1 = new TextMessage("Hi. I am the creator.", UUID.randomUUID(),
                new Date(), true);

        TextMessage message2 = new TextMessage("Hello, I am the second.", UUID.randomUUID(),
                new Date(), true);

        TextMessage message3 = new TextMessage("I am third.", UUID.randomUUID(),
                new Date(), true);

        TextMessage message4 = new TextMessage("What?", UUID.randomUUID(),
                new Date(), true);

        TextMessage message5 = new TextMessage("Okay.", UUID.randomUUID(),
                new Date(), true);

        TextMessage message6 = new TextMessage("Maybe.", UUID.randomUUID(),
                new Date(), true);

        TextMessage message7 = new TextMessage("Time.", UUID.randomUUID(),
                new Date(), true);

        TextMessage message8 = new TextMessage("Where?", UUID.randomUUID(),
                new Date(), true);

        TextMessage message9 = new TextMessage("How?", UUID.randomUUID(),
                new Date(), true);

        TextMessage[] messages = new TextMessage[]{message1, message2, message3, message4,
                message5, message6, message7, message8, message9,};

        UUID[] membersID = new UUID[]{message1.senderID, message2.senderID, message3.senderID,
                message4.senderID, message5.senderID, message6.senderID, message7.senderID,
                message8.senderID, message9.senderID};

        Conversation conversation = new Conversation("13GROUP", UUID.randomUUID(), membersID,
                message1.senderID, messages);

        //SerCheck
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Check.bin"));
        Conversation.serializeConversation(conversation, outputStream);
        outputStream.close();

        //DeserCheck
        ObjectInputStream aa = new ObjectInputStream(new FileInputStream("Check.bin"));
        System.out.println("Проверка сериализации и десериализации: " +
                Conversation.deserializeConversation(aa).toString());
        aa.close();

        //JSONSerCheck
        File newFile = new File("C:/Users/Антон/IdeaProjects/NetcrackerMessenger/CheckJSON.json");
        Conversation.serializeConversationToJSONFile(conversation, newFile);

        //JSONDeserCheck
        System.out.println("Проверка сериализации и десериализации JSON: " +
                Conversation.deserializeConversationFromJSONFile(newFile));

        //YAMLSerCheck
        File newFileYAML = new File("C:/Users/Антон/IdeaProjects/NetcrackerMessenger/CheckYAML.yaml");
        Conversation.serializeConversationToYAMLFile(conversation, newFileYAML);

        //YAMLDeserCheck
        System.out.println("Проверка сериализации и десериализации YAML: " +
                Conversation.deserializeConversationFromYAMLFile(newFileYAML));

    }
}
