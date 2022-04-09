package com.messenger.service;

import com.messenger.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private ProfileRepository databaseProfileDAO;
    
    @Autowired
    public ProfileService(ProfileRepository databaseProfileDAO) {
        this.databaseProfileDAO = databaseProfileDAO;
    }
}
