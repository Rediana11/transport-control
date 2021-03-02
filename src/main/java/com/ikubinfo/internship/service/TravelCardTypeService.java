package com.ikubinfo.internship.service;

import com.ikubinfo.internship.entity.TravelCardTypeEntity;

import java.util.List;

public interface TravelCardTypeService {

    List<TravelCardTypeEntity> getAllCardTypes();

    TravelCardTypeEntity getCardTypeById(Long id);

    TravelCardTypeEntity updateCardType(TravelCardTypeEntity cardType);

    TravelCardTypeEntity createCardType(TravelCardTypeEntity cardType);

    void deleteCardTypeById(TravelCardTypeEntity cardType);
}
