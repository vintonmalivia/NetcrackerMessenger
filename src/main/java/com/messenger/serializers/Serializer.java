package com.messenger.serializers;
import com.messenger.models.Conversation;
import java.io.IOException;

public interface Serializer
{
    String serialize(Conversation conversation) throws IOException;

    Conversation deserialize(String conversationString) throws IOException;

    //TODO: [Optional] Посмотреть про JSON Node, Object Node и подтипы (библиотека Jackson)
}