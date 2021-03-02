package com.ikubinfo.internship.service.serviceImpl;

import com.ikubinfo.internship.entity.TravelCardTypeEntity;
import com.ikubinfo.internship.repository.TravelCardTypeRepository;
import com.ikubinfo.internship.service.TravelCardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TravelCardTypeServiceImpl implements TravelCardTypeService {

    @Autowired
    private TravelCardTypeRepository cardTypeRepository;

    @Override
    public List<TravelCardTypeEntity> getAllCardTypes() {
        List<TravelCardTypeEntity> cardTypes = cardTypeRepository.findByIsDeleted(false);

        if (cardTypes.size() > 0) {
            return cardTypes;
        } else {
            return new ArrayList<TravelCardTypeEntity>();
        }
    }

    @Override
    public TravelCardTypeEntity getCardTypeById(Long id) {
        Optional<TravelCardTypeEntity> cardType = cardTypeRepository.findById(id);

        return cardType.orElseThrow(() -> new IllegalArgumentException("Not valid Id: " + id));

    }

    @Override
    public TravelCardTypeEntity createCardType(TravelCardTypeEntity cardType) {
        if (!cardTypeRepository.existsById(cardType.getId())) {
            return cardTypeRepository.save(cardType);
        }
        else {
            throw new IllegalArgumentException("Card type already exists: " + cardType.getId());
        }

    }

    @Override
    public TravelCardTypeEntity updateCardType(TravelCardTypeEntity cardType) {
        if (cardTypeRepository.existsById(cardType.getId())) {
            return cardTypeRepository.save(cardType);
        } else {
            throw new IllegalArgumentException("Not valid Id: " + cardType.getId());
        }

    }

    @Override
    public void deleteCardTypeById(TravelCardTypeEntity cardType) {
        if (cardTypeRepository.existsById(cardType.getId())) {
            cardType.setDeleted(true);
             cardTypeRepository.save(cardType);
        } else {
            throw new IllegalArgumentException("Not valid Id: " + cardType.getId());
        }
    }
}
