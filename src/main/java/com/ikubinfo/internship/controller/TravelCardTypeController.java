package com.ikubinfo.internship.controller;

import com.ikubinfo.internship.entity.TravelCardTypeEntity;
import com.ikubinfo.internship.service.TravelCardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("card-type")
public class TravelCardTypeController {

    @Autowired
    private TravelCardTypeService cardTypeService;

    @GetMapping
    public ResponseEntity<List<TravelCardTypeEntity>> getAllEmployees() {
        List<TravelCardTypeEntity> list = cardTypeService.getAllCardTypes();

        return new ResponseEntity<List<TravelCardTypeEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelCardTypeEntity> getCardTypeById(@PathVariable("id") Long id) {
        TravelCardTypeEntity entity = cardTypeService.getCardTypeById(id);

        return new ResponseEntity<TravelCardTypeEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TravelCardTypeEntity> createCardType(@RequestBody TravelCardTypeEntity cardType) {
        return ResponseEntity.ok().body(this.cardTypeService.createCardType(cardType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravelCardTypeEntity> updateCardType(@PathVariable long id, @RequestBody TravelCardTypeEntity cardType) {
        cardType.setId(id);
        return ResponseEntity.ok().body(this.cardTypeService.updateCardType(cardType));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteCardTypeById(@PathVariable("id") Long id) {
        cardTypeService.deleteCardTypeById(id);
        return HttpStatus.FORBIDDEN;
    }
}
