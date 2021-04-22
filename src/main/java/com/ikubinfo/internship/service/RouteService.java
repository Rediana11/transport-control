package com.ikubinfo.internship.service;

import com.ikubinfo.internship.dto.RouteDTO;
import com.ikubinfo.internship.dto.StatisticData;
import com.ikubinfo.internship.entity.RouteEntity;
import com.ikubinfo.internship.exception.EntityNotFoundException;
import com.ikubinfo.internship.mapper.RouteMapper;
import com.ikubinfo.internship.repository.RouteRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    private RouteMapper mapper
            = Mappers.getMapper(RouteMapper.class);


    public List<RouteDTO> getAllRoutes() {
        List<RouteEntity> routes = routeRepository.findByIsDeleted(false);

        return mapper.toDto(routes);
    }

    public RouteDTO getRouteById(Long id) {

        Optional<RouteEntity> route = routeRepository.findById(id);

        return mapper.toDto(route.orElseThrow(() -> new EntityNotFoundException("Not valid Id: " + id)));

    }

    public RouteDTO updateRoute(RouteDTO route) {
        if (routeRepository.existsById(route.getId())) {
            route.setUpdatedOn(new Date());
            RouteEntity updatedRoute = routeRepository.save(mapper.toEntity(route));
            return mapper.toDto(updatedRoute);
        } else {
            throw new EntityNotFoundException("Not valid Id: " + route.getId());
        }
    }

    public RouteDTO createRoute(RouteDTO route) {

        route.setCreated_on(new Date());
        RouteEntity newRoute = routeRepository.save(mapper.toEntity(route));
        return mapper.toDto(newRoute);
    }


    public void deleteRouteById(long id) {
        if (routeRepository.existsById(id)) {
            Optional<RouteEntity> route= routeRepository.findById(id);
            route.get().setDeleted(true);
            routeRepository.save(route.get());
        } else {
            throw new EntityNotFoundException("Not valid Id: " + id);
        }
    }

    public List<StatisticData> getMostFrequentedLines(LocalDateTime firstDate, LocalDateTime secondDate){
         return routeRepository.getMostFrequentedLines(firstDate, secondDate);
    }

}
