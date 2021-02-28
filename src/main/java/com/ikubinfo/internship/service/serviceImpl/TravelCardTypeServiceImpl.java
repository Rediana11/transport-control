package com.ikubinfo.internship.service.serviceImpl;

import com.ikubinfo.internship.entity.TravelCardEntity;
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
    TravelCardTypeRepository cardTypeRepository;

    @Override
    public List<TravelCardTypeEntity> getAllCardTypes() {
        List<TravelCardTypeEntity> cardTypes = cardTypeRepository.findAllValidCardTypes();

        if (cardTypes.size() > 0) {
            return cardTypes;
        } else {
            return new ArrayList<TravelCardTypeEntity>();
        }
    }

    @Override
    public TravelCardTypeEntity getCardTypeById(Long id) {
        Optional<TravelCardTypeEntity> cardType = cardTypeRepository.findById(id);

        if (cardType.isPresent()) {
            return cardType.get();
        } else {
            throw new InvalidConfigurationPropertyValueException("No card type record exist for given id", cardType, "");
        }
    }

    @Override
    public TravelCardTypeEntity createCardType(TravelCardTypeEntity cardType) {
        return cardTypeRepository.save(cardType);
    }

    @Override
    public TravelCardTypeEntity updateCardType(TravelCardTypeEntity cardType) {
        Optional < TravelCardTypeEntity > card = this.cardTypeRepository.findById(cardType.getId());

        if (card.isPresent()) {
            TravelCardTypeEntity newEntity = card.get();
            newEntity.setId(cardType.getId());
            newEntity.setName(cardType.getName());
            newEntity.setPrice(cardType.getPrice());
            newEntity.setValue(cardType.getValue());
            cardTypeRepository.save(newEntity);

            return newEntity;
        } else {
            throw new  InvalidConfigurationPropertyValueException("No card type record exist for given id", cardType, "");
        }
    }

    @Override
    public void deleteCardTypeById(Long id) {
        Optional<TravelCardTypeEntity> card = cardTypeRepository.findById(id);

        if (card.isPresent()) {
            TravelCardTypeEntity newEntity = card.get();
            newEntity.setDeleted(false);
        } else {
            throw new InvalidConfigurationPropertyValueException("No card type record exist for given id", card, "");
        }
    }
}
