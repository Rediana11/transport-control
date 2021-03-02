package com.ikubinfo.internship.controller;

import com.ikubinfo.internship.entity.RouteTypeEntity;
import com.ikubinfo.internship.service.RouteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("route-type")
public class RouteTypeController {

    @Autowired
    private RouteTypeService routeTypeService;


    @GetMapping("/list")
    public ResponseEntity<List<RouteTypeEntity>> getAllRouteTypes() {
        List<RouteTypeEntity> list = routeTypeService.getAllRouteTypes();

        return new ResponseEntity<List<RouteTypeEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteTypeEntity> getRouteTypeById(@PathVariable("id") Long id) {
        RouteTypeEntity entity = routeTypeService.getRouteTypeById(id);

        return new ResponseEntity<RouteTypeEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RouteTypeEntity> createRouteType(@RequestBody RouteTypeEntity routeType) {
        return ResponseEntity.ok().body(this.routeTypeService.createRouteType(routeType));
    }

    @PutMapping("/update")
    public ResponseEntity<RouteTypeEntity> updateRouteType(@RequestBody RouteTypeEntity routeType) {
        return ResponseEntity.ok().body(this.routeTypeService.updateRouteType(routeType));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteRouteTypeById(@RequestBody RouteTypeEntity routeType) {
        routeTypeService.deleteRouteTypeById(routeType);
        return HttpStatus.FORBIDDEN;
    }
}
