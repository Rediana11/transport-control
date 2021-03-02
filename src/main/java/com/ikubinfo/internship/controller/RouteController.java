package com.ikubinfo.internship.controller;

import com.ikubinfo.internship.entity.RouteEntity;
import com.ikubinfo.internship.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("route")
public class RouteController {

    @Autowired
    private RouteService routeService;


    @GetMapping("/list")
    public ResponseEntity<List<RouteEntity>> getAllRoutes() {
        List<RouteEntity> list = routeService.getAllRoutes();

        return new ResponseEntity<List<RouteEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteEntity> getRouteById(@PathVariable("id") Long id) {
        RouteEntity entity = routeService.getRouteById(id);

        return new ResponseEntity<RouteEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RouteEntity> createRoute(@RequestBody RouteEntity route) {
        return ResponseEntity.ok().body(this.routeService.createRoute(route));
    }

    @PutMapping("/update")
    public ResponseEntity<RouteEntity> updateRoute( @RequestBody RouteEntity route) {
        return ResponseEntity.ok().body(this.routeService.updateRoute(route));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteRouteById( @RequestBody RouteEntity route) {
        routeService.deleteRouteById(route);
        return HttpStatus.FORBIDDEN;
    }
}
