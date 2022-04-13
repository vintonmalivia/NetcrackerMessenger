package com.messenger.service;

import com.messenger.models.Profile;
import com.messenger.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileService {
    private ProfileRepository databaseProfileDAO;
    
    @Autowired
    public ProfileService(ProfileRepository databaseProfileDAO) {
        this.databaseProfileDAO = databaseProfileDAO;
    }

    public Profile getProfile(UUID uuid){return databaseProfileDAO.findById(uuid).get();
    }
}
