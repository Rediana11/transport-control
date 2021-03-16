package com.ikubinfo.internship.service;

import com.ikubinfo.internship.dto.TravelCardDTO;
import com.ikubinfo.internship.entity.TravelCardEntity;

import java.util.List;

public interface TravelCardService {


    List<TravelCardDTO> getAllTravelCards();

    TravelCardDTO updateTravelCard(TravelCardDTO card);

    TravelCardDTO createTravelCard(TravelCardDTO card);

    void deleteTravelCardById(TravelCardDTO card);

    boolean isCreditValuable(TravelCardDTO card);

    boolean usingTicket(TravelCardDTO card);

    boolean isValidSubscription(TravelCardDTO card);

    boolean checkCard (TravelCardDTO card);
}
