package com.ikubinfo.internship.controller;

import com.ikubinfo.internship.entity.TravelCardEntity;
import com.ikubinfo.internship.entity.TravelCardTypeEntity;
import com.ikubinfo.internship.service.TravelCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("travel-card")
public class TravelCardContoller {

    @Autowired
    private TravelCardService travelCardService;

    @GetMapping("/list")
    public ResponseEntity<List<TravelCardEntity>> getAllCardTypes() {
        List<TravelCardEntity> list = travelCardService.getAllTravelCards();

        return new ResponseEntity<List<TravelCardEntity>>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TravelCardEntity> createTravelCard(@RequestBody TravelCardEntity card) {
        return ResponseEntity.ok().body(this.travelCardService.createTravelCard(card));
    }

    @PutMapping("/update")
    public ResponseEntity<TravelCardEntity> updateTravelCard(@RequestBody TravelCardEntity card) {
        return ResponseEntity.ok().body(this.travelCardService.updateTravelCard(card));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteTravelCardById(@RequestBody TravelCardEntity card) {
        travelCardService.deleteTravelCardById(card);
        return HttpStatus.FORBIDDEN;
    }

    @PutMapping("check")
    public ResponseEntity<Boolean> checkTravelCard(@RequestBody TravelCardEntity card) {
        return ResponseEntity.ok().body(this.travelCardService.checkCard(card));
    }

}
