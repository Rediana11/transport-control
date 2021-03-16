package com.ikubinfo.internship.service.serviceImpl;

import com.ikubinfo.internship.dto.TravelCardDTO;
import com.ikubinfo.internship.entity.TravelCardEntity;
import com.ikubinfo.internship.exception.EntityNotFoundException;
import com.ikubinfo.internship.mapper.TravelCardMapper;
import com.ikubinfo.internship.repository.TravelCardRepository;
import com.ikubinfo.internship.service.TravelCardService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TravelCardServiceImpl implements TravelCardService {

    @Autowired
    private TravelCardRepository travelCardRepository;

    private TravelCardMapper mapper
            = Mappers.getMapper(TravelCardMapper.class);


    @Override
    public List<TravelCardDTO> getAllTravelCards() {
        List<TravelCardEntity> cards = travelCardRepository.findAll();

       return mapper.toDto(cards);
    }

    @Override
    public TravelCardDTO updateTravelCard(TravelCardDTO card) {
        if (travelCardRepository.existsById(card.getId())) {
            TravelCardEntity cardEntity= travelCardRepository.save(mapper.toEntity(card));
            card.setUpdatedOn(new Date());
            return mapper.toDto(cardEntity);
        } else {
            throw new EntityNotFoundException("Not valid Id: " + card.getId());
        }
    }

    @Override
    public TravelCardDTO createTravelCard(TravelCardDTO card) {
        if (!travelCardRepository.existsById(card.getId())) {
            TravelCardEntity cardEntity= travelCardRepository.save(mapper.toEntity(card));
            card.setCreatedOn(new Date());
            return mapper.toDto(cardEntity);
        } else {
            throw new EntityNotFoundException("Card type already exists: " + card.getId());
        }
    }

    @Override
    public void deleteTravelCardById(TravelCardDTO card) {
        if (travelCardRepository.existsById(card.getId())) {
            card.setDeleted(true);
            travelCardRepository.save(mapper.toEntity(card));
        } else {
            throw new EntityNotFoundException("Not valid Id: " + card.getId());
        }
    }

    @Override
    public boolean isCreditValuable(TravelCardDTO card) {

        if (card.getBalance().compareTo(card.getTravelCardType().getPrice()) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean usingTicket(TravelCardDTO card) {
        if (isCreditValuable(card)) {
            BigDecimal newBalance = card.getBalance().subtract(card.getTravelCardType().getPrice());
            card.setBalance(newBalance);
            travelCardRepository.save(mapper.toEntity(card));
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidSubscription(TravelCardDTO card) {
        if (!isCreditValuable(card)) {
            if (card.getActivatedOfferDate().plusDays(card.getTravelCardType().getValueDays()).isAfter(LocalDateTime.now())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkCard (TravelCardDTO card){
        if (usingTicket(card) || isValidSubscription(card)){
            return true;
        }
        return false;
    }


}
