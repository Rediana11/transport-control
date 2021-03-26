package com.ikubinfo.internship.controller;

import com.ikubinfo.internship.dto.RouteDTO;
import com.ikubinfo.internship.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public HttpStatus deleteRouteById(@RequestBody RouteDTO route) {

        routeService.deleteRouteById(route);
        return HttpStatus.FORBIDDEN;
    }


}
