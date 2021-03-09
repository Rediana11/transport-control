package com.ikubinfo.internship.controller;

import com.ikubinfo.internship.dto.RouteTypeDTO;
import com.ikubinfo.internship.entity.RouteTypeEntity;
import com.ikubinfo.internship.mapping.RouteTypeMapper;
import com.ikubinfo.internship.service.RouteTypeService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("route-type")
public class RouteTypeController {

    @Autowired
    private RouteTypeService routeTypeService;


    private RouteTypeMapper mapper
            = Mappers.getMapper(RouteTypeMapper.class);

    @GetMapping("/list")
    public ResponseEntity<List<RouteTypeDTO>> getAllRouteTypes() {
        List<RouteTypeEntity> list = routeTypeService.getAllRouteTypes();
        List<RouteTypeDTO> routesDTO = new ArrayList<>();
        for (RouteTypeEntity route : list) {
            routesDTO.add(mapper.routeTypeToRouteTypeDTO(route));
        }
        return new ResponseEntity<List<RouteTypeDTO>>(routesDTO, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteTypeDTO> getRouteTypeById(@PathVariable("id") Long id) {

        return new ResponseEntity<RouteTypeDTO>(mapper.routeTypeToRouteTypeDTO(routeTypeService.getRouteTypeById(id)), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RouteTypeDTO> createRouteType(@RequestBody RouteTypeDTO routeTypeDTO) {
        RouteTypeEntity route = mapper.routeTypeDTOToRouteType(routeTypeDTO);
        RouteTypeEntity routeCreated = routeTypeService.createRouteType(route);
        return ResponseEntity.ok().body(mapper.routeTypeToRouteTypeDTO(routeCreated));
    }

    @PutMapping("/update")
    public ResponseEntity<RouteTypeDTO> updateRouteType(@RequestBody RouteTypeDTO routeTypeDTO) {
        RouteTypeEntity route = mapper.routeTypeDTOToRouteType(routeTypeDTO);
        RouteTypeEntity routeCreated = routeTypeService.updateRouteType(route);
        return ResponseEntity.ok().body(mapper.routeTypeToRouteTypeDTO(routeCreated));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteRouteTypeById(@RequestBody RouteTypeDTO routeTypeDTO) {
        routeTypeService.deleteRouteTypeById(mapper.routeTypeDTOToRouteType(routeTypeDTO));
        return HttpStatus.FORBIDDEN;
    }
}
