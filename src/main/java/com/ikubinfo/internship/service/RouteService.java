package com.ikubinfo.internship.service;

import com.ikubinfo.internship.dto.RouteDTO;
import com.ikubinfo.internship.entity.RouteEntity;

import java.sql.SQLException;
import java.util.List;

public interface RouteService {

    List<RouteDTO> getAllRoutes();

    RouteDTO getRouteById(Long id);

    RouteDTO updateRoute(RouteDTO route);

    RouteDTO createRoute(RouteDTO route);

    void deleteRouteById(RouteDTO route);
}
