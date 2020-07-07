package com.telegrambot.funcompas.repository;

import com.telegrambot.funcompas.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlacesRepository extends JpaRepository<Place, Integer> {

    List<Place> findPlaceByCategoriesName(String categoriesName);

    Optional<Place> findPlaceById(int id);

}

