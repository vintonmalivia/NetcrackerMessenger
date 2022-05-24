package com.messenger.service;

import com.messenger.models.Profile;
import com.messenger.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileService {
    private final ProfileRepository databaseProfileDAO;
    
    @Autowired
    public ProfileService(ProfileRepository databaseProfileDAO) {
        this.databaseProfileDAO = databaseProfileDAO;
    }

    public Profile getProfile(UUID uuid){
        if (databaseProfileDAO.findById(uuid).isPresent()) {
            return databaseProfileDAO.findById(uuid).get();
        }
        return null;
    }

    public void updateProfileName(UUID profileID, Profile profile){
        Profile profileToBeUpdated = getProfile(profileID);
        profileToBeUpdated.setName(profile.getName());
        profileToBeUpdated.setSurname(profile.getSurname());
        profileToBeUpdated.setUserID(profileID);
        databaseProfileDAO.save(profileToBeUpdated);
    }
}
