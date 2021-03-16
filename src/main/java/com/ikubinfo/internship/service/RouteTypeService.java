package com.ikubinfo.internship.service;

import com.ikubinfo.internship.dto.RouteTypeDTO;
import com.ikubinfo.internship.entity.RouteTypeEntity;

import java.util.List;

public interface RouteTypeService {

    List<RouteTypeDTO> getAllRouteTypes();

    RouteTypeDTO getRouteTypeById(Long id);

    RouteTypeDTO updateRouteType(RouteTypeDTO routeType);

    RouteTypeDTO createRouteType(RouteTypeDTO routeType);

    void deleteRouteTypeById(RouteTypeDTO routeType);
}
