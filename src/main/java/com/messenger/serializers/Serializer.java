package com.messenger.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.messenger.models.Conversation;

import java.io.File;
import java.io.IOException;

public interface Serializer
{
    // TODO: (high) Фиг с ними, с байтами. Пусть лучше будут только JSON и YAML (по желанию, можно сделать в XML, оценю)
    //  Нужно создать интерфейс - Serializer, и сделать в нем два метода: serialize(объект) и deserialize(объект). Сериализаторов у тебя всего пока 3 штуки:
    //  1) JSON (при сериализации берем объект и сериализуем в JSON-строку, при десериализации берем строку и строим объект);
    //  2) YAML (то же самое, но с YAML строкой);

}
