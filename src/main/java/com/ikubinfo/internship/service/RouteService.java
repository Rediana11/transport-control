package com.ikubinfo.internship.service;

import com.ikubinfo.internship.entity.RouteEntity;

import java.util.List;

public interface RouteService {

    List<RouteEntity> getAllRoutes();

    RouteEntity getRouteById(Long id);

    RouteEntity updateRoute(RouteEntity route);

    RouteEntity createRoute(RouteEntity route);

    void deleteRouteById(RouteEntity route);
}
