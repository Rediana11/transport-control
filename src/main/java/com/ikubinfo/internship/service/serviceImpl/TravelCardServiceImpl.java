package com.ikubinfo.internship.service.serviceImpl;

import com.ikubinfo.internship.entity.TravelCardEntity;
import com.ikubinfo.internship.repository.TravelCardRepository;
import com.ikubinfo.internship.service.TravelCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TravelCardServiceImpl implements TravelCardService {

    @Autowired
    private TravelCardRepository travelCardRepository;

    @Override
    public List<TravelCardEntity> getAllTravelCards() {
        List<TravelCardEntity> cards = travelCardRepository.findAll();

        if (cards.size() > 0) {
            return cards;
        } else {
            return new ArrayList<TravelCardEntity>();
        }
    }

    @Override
    public TravelCardEntity updateTravelCard(TravelCardEntity card) {
        if (travelCardRepository.existsById(card.getId())) {
            return travelCardRepository.save(card);
        } else {
            throw new IllegalArgumentException("Not valid Id: " + card.getId());
        }
    }

    @Override
    public TravelCardEntity createTravelCard(TravelCardEntity card) {
        if (!travelCardRepository.existsById(card.getId())) {
            return travelCardRepository.save(card);
        } else {
            throw new IllegalArgumentException("Card type already exists: " + card.getId());
        }
    }

    @Override
    public void deleteTravelCardById(TravelCardEntity card) {
        if (travelCardRepository.existsById(card.getId())) {
            card.setDeleted(true);
            travelCardRepository.save(card);
        } else {
            throw new IllegalArgumentException("Not valid Id: " + card.getId());
        }
    }

    @Override
    public boolean isCreditValuable(TravelCardEntity card) {

        if (card.getBalance().compareTo(card.getTravelCardType().getPrice()) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean usingTicket(TravelCardEntity card) {
        if (isCreditValuable(card)) {
            BigDecimal newBalance = card.getBalance().subtract(card.getTravelCardType().getPrice());
            card.setBalance(newBalance);
            travelCardRepository.save(card);
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidSubscription(TravelCardEntity card) {
        if (!isCreditValuable(card)) {
            if (card.getActivatedOfferDate().plusDays(card.getTravelCardType().getValueDays()).isAfter(LocalDateTime.now())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkCard (TravelCardEntity card){
        if (usingTicket(card) || isValidSubscription(card)){
            return true;
        }
        return false;
    }


}
