package com.ikubinfo.internship.service.serviceImpl;

import com.ikubinfo.internship.entity.RouteEntity;
import com.ikubinfo.internship.entity.TravelCardTypeEntity;
import com.ikubinfo.internship.repository.RouteRepository;
import com.ikubinfo.internship.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;


    @Override
    public List<RouteEntity> getAllRoutes() {
        List<RouteEntity> routes = routeRepository.findByIsDeleted(false);

        if (routes.size() > 0) {
            return routes;
        } else {
            return new ArrayList<RouteEntity>();
        }
    }

    @Override
    public RouteEntity getRouteById(Long id) {

        Optional<RouteEntity> route = routeRepository.findById(id);

        return route.orElseThrow(() -> new IllegalArgumentException("Not valid Id: " + id));

    }

    @Override
    public RouteEntity updateRoute(RouteEntity route) {
        if (routeRepository.existsById(route.getId())) {
            return routeRepository.save(route);
        } else {
            throw new IllegalArgumentException("Not valid Id: " + route.getId());
        }
    }

    @Override
    public RouteEntity createRoute(RouteEntity route) {
        if (!routeRepository.existsById(route.getId())) {
            return routeRepository.save(route);
        } else {
            throw new IllegalArgumentException("Card type already exists: " + route.getId());
        }
    }

    @Override
    public void deleteRouteById(RouteEntity route) {
        if (routeRepository.existsById(route.getId())) {
            route.setDeleted(true);
            routeRepository.save(route);
        } else {
            throw new IllegalArgumentException("Not valid Id: " + route.getId());
        }
    }
}
