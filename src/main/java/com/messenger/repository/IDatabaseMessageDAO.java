package com.messenger.repository;
import com.messenger.models.AbstractMessage;
import com.messenger.models.impl.TextMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

/* Todo: (в дискорде про это уже сказано) Научиться делать методы, которые не входят в обычный CrudRepository */
/*  Например: поиск всех сообщений, принадлежащих одному чату (по ID чата). Подсказка: Посмотри про аннотации @Query */
public interface IDatabaseMessageDAO extends CrudRepository<TextMessage, UUID> {

    @Query(value = "SELECT * FROM text_messages WHERE id_conversation = CAST (:uuid AS varchar)", nativeQuery = true)
    List<TextMessage> getMessages(@Param("uuid") UUID uuid);
}
