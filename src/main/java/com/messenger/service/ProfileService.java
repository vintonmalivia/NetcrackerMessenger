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

    // TODO: Здесь findById(id) возвращает Optional. А если там нет объекта? Нужно сделать проверку
    // TODO: Метод как-то режет глаз. Бизнес-логику на следующую строку нужно перенести, а последнюю скобочку после
    //  бизнес-логики на следующей строке (пример, местная функция updateProfileName)
    public Profile getProfile(UUID uuid){return databaseProfileDAO.findById(uuid).get();}

    public void updateProfileName(UUID profileID, Profile profile){
        Profile profileToBeUpdated = getProfile(profileID);
        profileToBeUpdated.setName(profile.getName());
        profileToBeUpdated.setSurname(profile.getSurname());
        profileToBeUpdated.setUserID(profileID);
        databaseProfileDAO.save(profileToBeUpdated);
    }
}
