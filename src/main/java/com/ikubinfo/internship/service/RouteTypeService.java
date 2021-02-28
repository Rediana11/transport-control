package com.ikubinfo.internship.service;

import com.ikubinfo.internship.entity.RouteTypeEntity;
import com.ikubinfo.internship.entity.TravelCardTypeEntity;

import java.util.List;

public interface RouteTypeService {

    List<RouteTypeEntity> getAllRouteTypes();

    RouteTypeEntity getRouteTypeById(Long id);

    RouteTypeEntity updateRouteType(RouteTypeEntity routeType);

    RouteTypeEntity createRouteType(RouteTypeEntity routeType);

    void deleteRouteTypeById(Long id);
}