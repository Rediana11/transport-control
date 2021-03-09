package com.ikubinfo.internship.controller;

import com.ikubinfo.internship.dto.TravelCardTypeDTO;
import com.ikubinfo.internship.entity.TravelCardTypeEntity;
import com.ikubinfo.internship.mapping.TravelCardTypeMapper;
import com.ikubinfo.internship.service.TravelCardTypeService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("card-type")
public class TravelCardTypeController {

    @Autowired
    private TravelCardTypeService cardTypeService;


    private TravelCardTypeMapper mapper
            = Mappers.getMapper(TravelCardTypeMapper.class);


    @GetMapping("/list")
    public ResponseEntity<List<TravelCardTypeDTO>> getAllCardTypes() {
        List<TravelCardTypeEntity> list = cardTypeService.getAllCardTypes();
        List<TravelCardTypeDTO> cardTypeDTOS = new ArrayList<>();
        for (TravelCardTypeEntity card : list) {
            cardTypeDTOS.add(mapper.cardTypeToCardTypeDTO(card));
        }
        return new ResponseEntity<List<TravelCardTypeDTO>>(cardTypeDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelCardTypeDTO> getCardTypeById(@PathVariable("id") Long id) {

        return new ResponseEntity<TravelCardTypeDTO>(mapper.cardTypeToCardTypeDTO( cardTypeService.getCardTypeById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TravelCardTypeDTO> createCardType(@RequestBody TravelCardTypeDTO cardType) {
        TravelCardTypeEntity card = mapper.cardTypeDTOToCardType(cardType);
        TravelCardTypeEntity cardTypeCreated = cardTypeService.createCardType(card);

        return ResponseEntity.ok().body(mapper.cardTypeToCardTypeDTO(cardTypeCreated));
    }

    @PutMapping("/update")
    public ResponseEntity<TravelCardTypeDTO> updateCardType(@RequestBody TravelCardTypeDTO cardType) {
        TravelCardTypeEntity card = mapper.cardTypeDTOToCardType(cardType);
        TravelCardTypeEntity cardTypeCreated = cardTypeService.updateCardType(card);

        return ResponseEntity.ok().body(mapper.cardTypeToCardTypeDTO(cardTypeCreated));    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteCardTypeById(@RequestBody TravelCardTypeDTO cardType) {
        cardTypeService.deleteCardTypeById(mapper.cardTypeDTOToCardType(cardType));
        return HttpStatus.FORBIDDEN;
    }
}
