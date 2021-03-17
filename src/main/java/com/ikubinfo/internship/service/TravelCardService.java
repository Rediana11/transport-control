package com.ikubinfo.internship.service;

import com.ikubinfo.internship.dto.TravelCardDTO;
import com.ikubinfo.internship.entity.TravelCardEntity;
import com.ikubinfo.internship.exception.EntityNotFoundException;
import com.ikubinfo.internship.mapper.TravelCardMapper;
import com.ikubinfo.internship.repository.TravelCardRepository;
import com.ikubinfo.internship.repository.TravelCardTypeRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class TravelCardService {

    @Autowired
    private TravelCardRepository travelCardRepository;

    @Autowired
    private TravelCardTypeRepository cardTypeRepository;

    private TravelCardMapper mapper
            = Mappers.getMapper(TravelCardMapper.class);


    public List<TravelCardDTO> getAllTravelCards() {
        List<TravelCardEntity> cards = travelCardRepository.findAll();

        return mapper.toDto(cards);
    }

    public TravelCardDTO updateTravelCard(TravelCardDTO card) {
        if (travelCardRepository.existsById(card.getId())) {
            TravelCardEntity cardEntity = travelCardRepository.save(mapper.toEntity(card));
            card.setUpdatedOn(new Date());
            return mapper.toDto(cardEntity);
        } else {
            throw new EntityNotFoundException("Not valid Id: " + card.getId());
        }
    }

    public TravelCardDTO createTravelCard(TravelCardDTO card) {
        TravelCardEntity cardEntity = travelCardRepository.save(mapper.toEntity(card));
        card.setCreatedOn(new Date());
        return mapper.toDto(cardEntity);

    }

    public void deleteTravelCardById(TravelCardDTO card) {
        if (travelCardRepository.existsById(card.getId())) {
            card.setDeleted(true);
            travelCardRepository.save(mapper.toEntity(card));
        } else {
            throw new EntityNotFoundException("Not valid Id: " + card.getId());
        }
    }

    public boolean isCreditValuable(TravelCardDTO card) {

        if (card.getBalance().compareTo(cardTypeRepository.findByName("TICKET").getPrice()) == 1) {
            return true;
        }
        return false;
    }

    public boolean usingTicket(TravelCardDTO card) {
        if (isCreditValuable(card)) {
            BigDecimal newBalance = card.getBalance().subtract(cardTypeRepository.findByName("TICKET").getPrice());
            card.setBalance(newBalance);
            travelCardRepository.save(mapper.toEntity(card));
            return true;
        }
        return false;
    }

    public boolean isValidSubscription(TravelCardDTO card) {
        if (!isCreditValuable(card)) {
            if (card.getActivatedOfferDate().plusDays(card.getTravelCardType().getValueDays()).isAfter(LocalDateTime.now())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCard(TravelCardDTO card) {
        if (usingTicket(card)) {
            return true;
        }
        if (!usingTicket(card) && isValidSubscription(card)) {
            return true;
        }
        return false;
    }


}
