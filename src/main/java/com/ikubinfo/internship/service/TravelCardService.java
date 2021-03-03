package com.ikubinfo.internship.service;

import com.ikubinfo.internship.entity.TravelCardEntity;

import java.util.List;

public interface TravelCardService {


    List<TravelCardEntity> getAllTravelCards();

    TravelCardEntity updateTravelCard(TravelCardEntity card);

    TravelCardEntity createTravelCard(TravelCardEntity card);

    void deleteTravelCardById(TravelCardEntity card);

    boolean isCreditValuable(TravelCardEntity card);

    boolean usingTicket(TravelCardEntity card);

    boolean isValidSubscription(TravelCardEntity card);

    boolean checkCard (TravelCardEntity card);
}
