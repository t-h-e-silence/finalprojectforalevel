package com.telegrambot.funcompas.service;

import com.telegrambot.funcompas.entity.UserProfileData;
import com.telegrambot.funcompas.repository.UsersRepository;
import org.springframework.stereotype.Service;


@Service
public class UsersProfileDataService implements UserMethods {
    private final UsersRepository usersRepository;

    public UsersProfileDataService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserProfileData getUserProfileData(Long chatId) {
        return usersRepository.findByChatId(chatId);
    }

    @Override
    public void save(UserProfileData userProfileData) {
        usersRepository.save(userProfileData);
    }

}
