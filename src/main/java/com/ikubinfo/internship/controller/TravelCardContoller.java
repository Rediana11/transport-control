package com.ikubinfo.internship.controller;

import com.ikubinfo.internship.dto.TravelCardDTO;
import com.ikubinfo.internship.entity.TravelCardEntity;
import com.ikubinfo.internship.mapper.TravelCardMapper;
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


    @PostMapping(value = "create", consumes = {"application/json"},produces = {"application/json"})
    public ResponseEntity<TravelCardDTO> createTravelCard(@RequestBody TravelCardDTO card) {
        return ResponseEntity.ok().body(travelCardService.createTravelCard(card));
    }

    @PutMapping(value = "/update",consumes = {"application/json"},produces = {"application/json"})
    public ResponseEntity<TravelCardDTO> updateTravelCard(@RequestBody TravelCardDTO card) {
        return ResponseEntity.ok().body(travelCardService.updateTravelCard(card));
    }

    @DeleteMapping(value = "/{id}", consumes = {"application/json"})
    public HttpStatus deleteTravelCardById(@RequestBody TravelCardDTO card) {
        travelCardService.deleteTravelCardById(card);
        return HttpStatus.FORBIDDEN;
    }

    @PutMapping(value = "check", consumes = {"application/json"},produces = {"application/json"})
    public ResponseEntity<Boolean> checkTravelCard(@RequestBody TravelCardDTO card) {
        return ResponseEntity.ok().body(this.travelCardService.checkCard(card));
    }

}
