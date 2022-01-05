package com.messenger.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.messenger.models.Conversation;

import java.io.File;
import java.io.IOException;

public interface Serializer
{
    // TODO: (high) Давай все-таки в файлах хранить в виде байтов. Все эти методы лучше переместить в классы сериализаторов.
    //  Нужно создать интерфейс - Serializer, и сделать в нем два метода: serialize(объект) и deserialize(объект). Сериализаторов у тебя всего пока 3 штуки:
    //  1) JSON (при сериализации берем объект и сериализуем в JSON-строку, при десериализации берем строку и строим объект);
    //  2) YAML (то же самое, но с YAML строкой);
    //  3) Байты (не обязательно делать сериализатор, но можно)


}
