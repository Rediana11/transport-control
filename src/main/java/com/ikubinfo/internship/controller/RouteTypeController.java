package com.ikubinfo.internship.controller;

import com.ikubinfo.internship.dto.RouteTypeDTO;
import com.ikubinfo.internship.service.RouteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/route-type")
public class RouteTypeController {

    @Autowired
    private RouteTypeService routeTypeService;



    @GetMapping(value="/list", produces = {"application/json"})
    public ResponseEntity<List<RouteTypeDTO>> getAllRouteTypes() {
        List<RouteTypeDTO> list = routeTypeService.getAllRouteTypes();
        return new ResponseEntity<List<RouteTypeDTO>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = {"application/json"})
    public ResponseEntity<RouteTypeDTO> getRouteTypeById(@PathVariable("id") Long id) {

        return new ResponseEntity<RouteTypeDTO>(routeTypeService.getRouteTypeById(id), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<RouteTypeDTO> createRouteType(@Valid @RequestBody RouteTypeDTO routeTypeDTO) {
        return ResponseEntity.ok().body(routeTypeService.createRouteType(routeTypeDTO));
    }

    @PutMapping(value = "/update", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<RouteTypeDTO> updateRouteType(@RequestBody RouteTypeDTO routeTypeDTO) {
        return ResponseEntity.ok().body(routeTypeService.updateRouteType(routeTypeDTO));
    }

    @DeleteMapping(value = "/delete/{id}",consumes = {"application/json"})
    public HttpStatus deleteRouteTypeById(@RequestBody RouteTypeDTO routeTypeDTO) {
        routeTypeService.deleteRouteTypeById(routeTypeDTO);
        return HttpStatus.FORBIDDEN;
    }
}
