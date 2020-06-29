package com.telegrambot.funcompas.repository;

import com.telegrambot.funcompas.entity.Place;
import com.telegrambot.funcompas.entity.WorkingTime;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlacesRepository extends JpaRepository<Place, Integer> {

    List<Place> findAllByAddress_City(String city);

    List<Place> findByWorkingTime(WorkingTime workingTime);

    List<Place> findByCategoriesName(String categoriesName);


}

