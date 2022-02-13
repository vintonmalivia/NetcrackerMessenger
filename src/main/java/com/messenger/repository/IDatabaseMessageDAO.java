package com.messenger.repository;

import com.messenger.models.AbstractMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/* Todo: (в дискорде про это уже сказано) Научиться делать методы, которые не входят в обычный CrudRepository */
/*  Например: поиск всех сообщений, принадлежащих одному чату (по ID чата). Подсказка: Посмотри про аннотации @Query */
public interface IDatabaseMessageDAO extends CrudRepository<AbstractMessage, UUID> {

}
