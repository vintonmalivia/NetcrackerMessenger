package com.messenger.service;

import com.messenger.repository.IDatabaseProfileDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private IDatabaseProfileDAO databaseProfileDAO;
    
    @Autowired
    public ProfileService(IDatabaseProfileDAO databaseProfileDAO) {
        this.databaseProfileDAO = databaseProfileDAO;
    }
}
