package com.ikubinfo.internship.controller;

import com.ikubinfo.internship.dto.RouteDTO;
import com.ikubinfo.internship.dto.StatisticData;
import com.ikubinfo.internship.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/route")
public class RouteController {

    @Autowired
    private RouteService routeService;


    @GetMapping(value = "/list", produces = {"application/json"})
    public ResponseEntity<List<RouteDTO>> getAllRoutes() {

        List<RouteDTO> list = routeService.getAllRoutes();

        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<RouteDTO> getRouteById(@PathVariable("id") Long id) {

        return ResponseEntity.ok(routeService.getRouteById(id));

    }

    @PostMapping(value = "/create", consumes = {"application/json"}, produces = "application/json")
    public ResponseEntity<RouteDTO> createRoute(@Valid @RequestBody RouteDTO routeDTO) {
        RouteDTO routeCreated = routeService.createRoute(routeDTO);

        return ResponseEntity.ok().body(routeCreated);
    }

    @PutMapping(value = "/update", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<RouteDTO> updateRoute(@RequestBody RouteDTO routeDTO) {
        RouteDTO routeCreated = routeService.updateRoute(routeDTO);
        return ResponseEntity.ok().body(routeCreated);
    }

    @DeleteMapping(value = "/delete/{id}", consumes = {"application/json"})
    public HttpStatus deleteRouteById(@PathVariable ("id") Long id ) {
        routeService.deleteRouteById(id);
        return HttpStatus.OK;
    }

    @GetMapping(value = "/list/most-frequented/{localDateTime1}/{localDateTime2}")
    public ResponseEntity<List<StatisticData>> getMostFrequentedLines(@RequestParam("localDateTime1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                     LocalDateTime localDateTime1,
                                                                      @RequestParam("localDateTime2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                             LocalDateTime localDateTime2) {

        return ResponseEntity.ok(routeService.getMostFrequentedLines(localDateTime1,localDateTime2));
    }
}
