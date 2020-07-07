package com.telegrambot.funcompas.service;

import com.telegrambot.funcompas.entity.Place;
import com.telegrambot.funcompas.entity.WorkingTimeClose;
import com.telegrambot.funcompas.entity.WorkingTimeOpen;
import com.telegrambot.funcompas.repository.PlacesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PlaceMethodsService implements PlaceMethods {

    private final PlacesRepository placesRepository;

    public PlaceMethodsService(PlacesRepository placesRepository) {
        this.placesRepository = placesRepository;
    }

    @Override
    public List<Place> getByCategory(String categorieName) {
        return placesRepository.findPlaceByCategoriesName(categorieName);
    }

    @Override
    public Optional<Place> findPlaceById(int id) {
        return placesRepository.findPlaceById(id);
    }

}
