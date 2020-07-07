package com.telegrambot.funcompas.service;
import com.telegrambot.funcompas.entity.Place;
import com.telegrambot.funcompas.entity.WorkingTimeClose;
import com.telegrambot.funcompas.entity.WorkingTimeOpen;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Component
public interface PlaceMethods {

    List<Place>  getByCategory(String categoryId);

    Optional<Place> findPlaceById(int id);

}
