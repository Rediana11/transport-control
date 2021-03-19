package com.ikubinfo.internship.controller;

import com.ikubinfo.internship.dto.TravelCardTypeDTO;
import com.ikubinfo.internship.service.TravelCardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/card-type")
public class TravelCardTypeController {

    @Autowired
    private TravelCardTypeService cardTypeService;


    @GetMapping(value = "/list")
    public ResponseEntity<List<TravelCardTypeDTO>> getAllCardTypes() {
        List<TravelCardTypeDTO> list = cardTypeService.getAllCardTypes();

        return new ResponseEntity<List<TravelCardTypeDTO>>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = {"application/json"})
    public ResponseEntity<TravelCardTypeDTO> getCardTypeById(@PathVariable("id") Long id) {

        return new ResponseEntity<TravelCardTypeDTO>(cardTypeService.getCardTypeById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create", produces = {"application/json"},consumes = {"application/json"})
    public ResponseEntity<TravelCardTypeDTO> createCardType(@Valid @RequestBody TravelCardTypeDTO cardType) {

        return ResponseEntity.ok().body(cardTypeService.createCardType(cardType));
    }

    @PutMapping(value = "/update",produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<TravelCardTypeDTO> updateCardType(@RequestBody TravelCardTypeDTO cardType) {

        return ResponseEntity.ok().body(cardTypeService.updateCardType(cardType));
    }

    @DeleteMapping(value = "/delete/{id}", consumes = "application/json")
    public HttpStatus deleteCardTypeById(@RequestBody TravelCardTypeDTO cardType) {
        cardTypeService.deleteCardTypeById(cardType);
        return HttpStatus.FORBIDDEN;
    }
}
