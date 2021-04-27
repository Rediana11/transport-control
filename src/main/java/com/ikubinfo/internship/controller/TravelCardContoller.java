package com.ikubinfo.internship.controller;

import com.ikubinfo.internship.dto.RouteDTO;
import com.ikubinfo.internship.dto.TicketDTO;
import com.ikubinfo.internship.dto.TravelCardDTO;
import com.ikubinfo.internship.dto.TravelCardTypeDTO;
import com.ikubinfo.internship.service.TravelCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/travel-card")
public class TravelCardContoller {

    @Autowired
    private TravelCardService travelCardService;

    @GetMapping(value = "/list", produces = {"application/json"})
    public ResponseEntity<List<TravelCardDTO>> getAllCards() {

        List<TravelCardDTO> list = travelCardService.getAllTravelCards();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<TravelCardDTO> getCardById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(travelCardService.getCardById(id));

    }

    @PostMapping(value = "/create", consumes = {"application/json"},produces = {"application/json"})
    public ResponseEntity<TravelCardDTO> createTravelCard(@Valid @RequestBody TravelCardDTO card) {
        return ResponseEntity.ok().body(travelCardService.createTravelCard(card));
    }

    @PutMapping(value = "/update",consumes = {"application/json"},produces = {"application/json"})
    public ResponseEntity<TravelCardDTO> updateTravelCard(@RequestBody TravelCardDTO card) {
        return ResponseEntity.ok().body(travelCardService.updateTravelCard(card));
    }

    @DeleteMapping(value = "/delete/{id}", consumes = {"application/json"})
    public HttpStatus deleteTravelCardById(@PathVariable("id") Long id) {
        travelCardService.deleteTravelCardById(id);
        return HttpStatus.OK;
    }

    @PutMapping(value = "/check/{id}/{routeId}")
    public ResponseEntity<TravelCardDTO> checkTravelCard(@PathVariable("id") Long id, @PathVariable("routeId") Long routeId) {
        return ResponseEntity.ok().body(this.travelCardService.checkCard(id,routeId));
    }

    @PutMapping(value = "/book-ticket/{id}/{routeId}")
    public ResponseEntity<TicketDTO> bookTheTicket(@PathVariable("id") Long id, @PathVariable("routeId") Long routeId) {
        return ResponseEntity.ok().body(this.travelCardService.bookTheTicket(routeId,id));
    }

}
