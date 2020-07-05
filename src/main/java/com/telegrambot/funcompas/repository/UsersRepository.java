package com.telegrambot.funcompas.repository;

import com.telegrambot.funcompas.entity.UserProfileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UserProfileData, Long> {

    UserProfileData findByChatId(long chatId);

    void save(long chatId);

}
