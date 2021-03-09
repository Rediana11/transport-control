package com.ikubinfo.internship.controller;

import com.ikubinfo.internship.dto.RouteDTO;
import com.ikubinfo.internship.entity.RouteEntity;
import com.ikubinfo.internship.mapping.RouteMapper;
import com.ikubinfo.internship.service.RouteService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    private RouteMapper mapper
            = Mappers.getMapper(RouteMapper.class);



    @GetMapping("/list")
    public ResponseEntity<List<RouteDTO>> getAllRoutes() {

        List<RouteEntity> list = routeService.getAllRoutes();
        List<RouteDTO> routesDTO = new ArrayList<>();
        for (RouteEntity route : list) {
            routesDTO.add(mapper.routeToRouteDTO(route));
        }
        return new ResponseEntity<List<RouteDTO>>(routesDTO, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteDTO> getRouteById(@PathVariable("id") Long id) {

        return new ResponseEntity<RouteDTO>(mapper.routeToRouteDTO(routeService.getRouteById(id)), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RouteDTO> createRoute(@RequestBody RouteDTO routeDTO) {
        RouteEntity route = mapper.routeDTOToRoute(routeDTO);
        RouteEntity routeCreated = routeService.createRoute(route);

        return ResponseEntity.ok().body(mapper.routeToRouteDTO(routeCreated));
    }

    @PutMapping("/update")
    public ResponseEntity<RouteDTO> updateRoute(@RequestBody RouteDTO routeDTO) {
        RouteEntity route = mapper.routeDTOToRoute(routeDTO);
        RouteEntity routeCreated = routeService.updateRoute(route);
        return ResponseEntity.ok().body(mapper.routeToRouteDTO(routeCreated));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteRouteById(@RequestBody RouteDTO route) {

        routeService.deleteRouteById(mapper.routeDTOToRoute(route));
        return HttpStatus.FORBIDDEN;
    }


}
