package com.ikubinfo.internship.service;

import com.ikubinfo.internship.dto.*;
import com.ikubinfo.internship.entity.*;
import com.ikubinfo.internship.exception.EntityNotFoundException;
import com.ikubinfo.internship.mapper.TicketMapper;
import com.ikubinfo.internship.mapper.TravelCardMapper;
import com.ikubinfo.internship.mapper.TravelHistoryMapper;
import com.ikubinfo.internship.repository.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TravelCardService {

    @Autowired
    private TravelCardRepository travelCardRepository;

    @Autowired
    private TravelCardTypeRepository cardTypeRepository;

    @Autowired
    private TravelHistoryRepository travelHistoryRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private RouteRepository routeRepository;

    private TravelCardMapper mapper
            = Mappers.getMapper(TravelCardMapper.class);

    private TicketMapper ticketMapper
            = Mappers.getMapper(TicketMapper.class);

    private TravelHistoryMapper historyMapper
            = Mappers.getMapper(TravelHistoryMapper.class);


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

    public TravelCardDTO getCardById(Long id) {
        Optional<TravelCardEntity> card = travelCardRepository.findById(id);

        return mapper.toDto(card.orElseThrow(() -> new EntityNotFoundException("Not valid Id: " + id)));

    }

    public void deleteTravelCardById(long id) {
        if (travelCardRepository.existsById(id)) {
            Optional<TravelCardEntity> card = travelCardRepository.findById(id);
            card.get().setDeleted(true);
            travelCardRepository.save(card.get());
        } else {
            throw new EntityNotFoundException("Not valid Id: " + id);
        }
    }

    public boolean isCreditValuable(TravelCardDTO card) {

        if (card.getBalance().compareTo(cardTypeRepository.findByName("TICKET").getPrice()) == 1) {

            return true;
        }
        return false;
    }

    public boolean isCardAvailable(TravelCardDTO card){
        if(card.isLost()== false){
            return true;
        }
        return false;
    }
    public void usingTicket(TravelCardDTO card) {
        if (isCreditValuable(card) && isCardAvailable(card)) {
            BigDecimal newBalance = card.getBalance().subtract(cardTypeRepository.findByName("TICKET").getPrice());
            LocalDateTime currentTime = LocalDateTime.now();
            card.setTicketPurchaseTime(currentTime);
            card.setBalance(newBalance);
            travelCardRepository.save(mapper.toEntity(card));
        }
    }

    public boolean isValidSubscription(TravelCardDTO card) {
        if (!isCreditValuable(card)) {
            if (card.getActivatedOfferDate().plusDays(card.getTravelCardType().getValueDays()).isAfter(LocalDateTime.now())) {
                return true;
            }
        }
        return false;
    }

     public TravelHistoryDTO fillTravelHistory (Long id, Long routeId){

         TravelHistoryEntity travelHistory = new TravelHistoryEntity();
         Optional<TravelCardEntity> travelCard = travelCardRepository.findById(id);
         Optional<RouteEntity> route = routeRepository.findById(routeId);
         travelHistory.setCheckedCardTime(LocalDateTime.now());
         travelHistory.setTravelCard(travelCard.get());
         travelHistory.setRoute(route.get());
         travelHistoryRepository.save(travelHistory);

        return historyMapper.toDto(travelHistory);
    }

    public boolean isTicketValid(Long routeId,Long cardId){
        TicketDTO ticket = bookTheTicket(routeId,cardId);
        if (ticket != null && ticket.getRoute().getId()==routeId) {
            if (ticket.getTicketPurchaseTime().isBefore(ticket.getTicketPurchaseTime().plusHours(1))) {
                return true;
            }
        }
        return false;
    }

    public TicketDTO bookTheTicket (Long routeId, Long cardId){
        TicketEntity ticketEntity  =  new TicketEntity();

        Optional<RouteEntity> route = routeRepository.findById(routeId);
        ticketEntity.setTicketPurchaseTime(LocalDateTime.now());
        ticketEntity.setRoute(route.get());
        ticketRepository.save(ticketEntity);
        Optional<TravelCardEntity> card = travelCardRepository.findById(cardId);
        usingTicket(mapper.toDto(card.get()));
        return ticketMapper.toDto(ticketEntity);

    }

    public boolean finishCheckingCard(TravelCardDTO card,Long routeId){
        if(isTicketValid(routeId,card.getId()) || isValidSubscription(card)){
            return true;
        }
        return false;
    }

    public TravelCardDTO checkCard(Long id , Long routeId) {
        Optional<TravelCardEntity> card = travelCardRepository.findById(id);
        if(isCardAvailable(mapper.toDto(card.get()))) {
            if (finishCheckingCard(mapper.toDto(card.get()),routeId)) {
                fillTravelHistory(id,routeId);
                return mapper.toDto(card.get());

            }
        }
        throw new EntityNotFoundException("Not valid Id: " + id);
    }


}
