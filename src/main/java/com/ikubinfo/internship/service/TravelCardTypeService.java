package com.ikubinfo.internship.service;

import com.ikubinfo.internship.dto.TravelCardTypeDTO;
import com.ikubinfo.internship.entity.TravelCardTypeEntity;
import com.ikubinfo.internship.exception.EntityNotFoundException;
import com.ikubinfo.internship.mapper.TravelCardTypeMapper;
import com.ikubinfo.internship.repository.TravelCardTypeRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TravelCardTypeService {

    @Autowired
    private TravelCardTypeRepository cardTypeRepository;

    private TravelCardTypeMapper mapper
            = Mappers.getMapper(TravelCardTypeMapper.class);

    public List<TravelCardTypeDTO> getAllCardTypes() {
        List<TravelCardTypeEntity> cardTypes = cardTypeRepository.findByIsDeleted(false);
        return mapper.toDto(cardTypes);

    }

    public TravelCardTypeDTO getCardTypeById(Long id) {
        Optional<TravelCardTypeEntity> cardType = cardTypeRepository.findById(id);

        return mapper.toDto(cardType.orElseThrow(() -> new EntityNotFoundException("Not valid Id: " + id)));

    }

    public TravelCardTypeDTO createCardType(TravelCardTypeDTO cardType) {
        TravelCardTypeEntity updatedCardType = cardTypeRepository.save(mapper.toEntity(cardType));
        cardType.setCreatedOn(new Date());
        return mapper.toDto(updatedCardType);

    }

    public TravelCardTypeDTO updateCardType(TravelCardTypeDTO cardType) {
        if (cardTypeRepository.existsById(cardType.getId())) {

            TravelCardTypeEntity updatedCardType = cardTypeRepository.save(mapper.toEntity(cardType));
            cardType.setUpdatedOn(new Date());
            return mapper.toDto(updatedCardType);
        } else {
            throw new EntityNotFoundException("Not valid Id: " + cardType.getId());
        }
    }

    public void deleteCardTypeById(TravelCardTypeDTO cardType) {
        if (cardTypeRepository.existsById(cardType.getId())) {
            cardType.setDeleted(true);
            cardTypeRepository.save(mapper.toEntity(cardType));
        } else {
            throw new EntityNotFoundException("Not valid Id: " + cardType.getId());
        }
    }
}
