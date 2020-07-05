package com.telegrambot.funcompas.repository;
import com.telegrambot.funcompas.entity.Place;
import com.telegrambot.funcompas.entity.WorkingTimeClose;
import com.telegrambot.funcompas.entity.WorkingTimeOpen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlacesRepository extends JpaRepository<Place, Integer> {

    List<Place> findByWorkingTimeOpenBeforeAndWorkingTimeCloseAfter(WorkingTimeOpen workingTimeOpen, WorkingTimeClose workingTimeClose);

    List<Place> findPlaceByCategoriesName(String categoriesName);

    List<Place> findByUserChatId(Long chatId);

    Optional<Place> findPlaceByName(String name);

}

