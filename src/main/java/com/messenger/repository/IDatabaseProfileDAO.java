package com.messenger.repository;

import com.messenger.models.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IDatabaseProfileDAO extends CrudRepository<Profile, UUID> {
}
