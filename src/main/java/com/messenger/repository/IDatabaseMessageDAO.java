package com.messenger.repository;

import com.messenger.models.AbstractMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IDatabaseMessageDAO extends CrudRepository<AbstractMessage, UUID> {

}
