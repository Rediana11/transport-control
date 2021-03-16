package com.ikubinfo.internship.service.serviceImpl;

import com.ikubinfo.internship.dto.RouteDTO;
import com.ikubinfo.internship.entity.RouteEntity;
import com.ikubinfo.internship.exception.EntityNotFoundException;
import com.ikubinfo.internship.mapper.RouteMapper;
import com.ikubinfo.internship.repository.RouteRepository;
import com.ikubinfo.internship.service.RouteService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    private RouteMapper mapper
            = Mappers.getMapper(RouteMapper.class);


    @Override
    public List<RouteDTO> getAllRoutes() {
        List<RouteEntity> routes = routeRepository.findByIsDeleted(false);

        return mapper.toDto(routes);
    }

    @Override
    public RouteDTO getRouteById(Long id) {

        Optional<RouteEntity> route = routeRepository.findById(id);

        return mapper.toDto(route.orElseThrow(() -> new EntityNotFoundException("Not valid Id: " + id)));

    }

    @Override
    public RouteDTO updateRoute(RouteDTO route) {
        if (routeRepository.existsById(route.getId())) {
            route.setUpdatedOn(new Date());
            RouteEntity updatedRoute = routeRepository.save(mapper.toEntity(route));
            return mapper.toDto(updatedRoute);
        } else {
            throw new EntityNotFoundException("Not valid Id: " + route.getId());
        }
    }

    @Override
    public RouteDTO createRoute(RouteDTO route) {

        if (!routeRepository.existsById(route.getId())) {
            route.setCreated_on(new Date());
            RouteEntity newRoute  = routeRepository.save(mapper.toEntity(route));
            return mapper.toDto(newRoute);
        }
        {
            throw new EntityNotFoundException("Route already exists: " + route.getId());
        }
    }

    @Override
    public void deleteRouteById(RouteDTO route) {
        if (routeRepository.existsById(route.getId())) {
            route.setDeleted(true);
            routeRepository.save(mapper.toEntity(route));
        } else {
            throw new EntityNotFoundException("Not valid Id: " + route.getId());
        }
    }
}
