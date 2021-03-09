package com.ikubinfo.internship.controller;

import com.ikubinfo.internship.dto.TravelCardDTO;
import com.ikubinfo.internship.entity.TravelCardEntity;
import com.ikubinfo.internship.mapping.TravelCardMapper;
import com.ikubinfo.internship.service.TravelCardService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("travel-card")
public class TravelCardContoller {

    @Autowired
    private TravelCardService travelCardService;

    private TravelCardMapper mapper
            = Mappers.getMapper(TravelCardMapper.class);



    @PostMapping
    public ResponseEntity<TravelCardDTO> createTravelCard(@RequestBody TravelCardDTO card) {
        TravelCardEntity travelCardEntity = mapper.cardDTOToCard(card);
        TravelCardEntity cardCreated = travelCardService.createTravelCard(travelCardEntity);
        return ResponseEntity.ok().body(mapper.cardToCardDTO(cardCreated));
    }

    @PutMapping("/update")
    public ResponseEntity<TravelCardDTO> updateTravelCard(@RequestBody TravelCardDTO card) {
        TravelCardEntity travelCardEntity = mapper.cardDTOToCard(card);
        TravelCardEntity cardCreated = travelCardService.updateTravelCard(travelCardEntity);
        return ResponseEntity.ok().body(mapper.cardToCardDTO(cardCreated));    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteTravelCardById(@RequestBody TravelCardDTO card) {
        travelCardService.deleteTravelCardById(mapper.cardDTOToCard(card));
        return HttpStatus.FORBIDDEN;
    }

    @PutMapping("check")
    public ResponseEntity<Boolean> checkTravelCard(@RequestBody TravelCardDTO card) {
        return ResponseEntity.ok().body(this.travelCardService.checkCard(mapper.cardDTOToCard(card)));
    }

}
