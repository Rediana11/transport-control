package com.ikubinfo.internship.service;

import com.ikubinfo.internship.dto.TravelCardTypeDTO;
import com.ikubinfo.internship.entity.TravelCardTypeEntity;

import java.util.List;

public interface TravelCardTypeService {

    List<TravelCardTypeDTO> getAllCardTypes();

    TravelCardTypeDTO getCardTypeById(Long id);

    TravelCardTypeDTO updateCardType(TravelCardTypeDTO cardType);

    TravelCardTypeDTO createCardType(TravelCardTypeDTO cardType);

    void deleteCardTypeById(TravelCardTypeDTO cardType);
}
