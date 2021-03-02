package com.ikubinfo.internship.service.serviceImpl;

import com.ikubinfo.internship.entity.RouteTypeEntity;
import com.ikubinfo.internship.entity.TravelCardTypeEntity;
import com.ikubinfo.internship.repository.RouteTypeRepository;
import com.ikubinfo.internship.service.RouteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RouteTypeServiceImpl implements RouteTypeService {

    @Autowired
    private RouteTypeRepository routeTypeRepository;


    @Override
    public List<RouteTypeEntity> getAllRouteTypes() {
        List<RouteTypeEntity> routeTypes = routeTypeRepository.findByIsDeleted(false);

        if (routeTypes.size() > 0) {
            return routeTypes;
        } else {
            return new ArrayList<RouteTypeEntity>();
        }
    }

    @Override
    public RouteTypeEntity getRouteTypeById(Long id) {
        Optional<RouteTypeEntity> cardType = routeTypeRepository.findById(id);

        return cardType.orElseThrow(() -> new IllegalArgumentException("Not valid Id: " + id));
    }

    @Override
    public RouteTypeEntity updateRouteType(RouteTypeEntity routeType) {
        if (routeTypeRepository.existsById(routeType.getId())) {
            return routeTypeRepository.save(routeType);
        } else {
            throw new IllegalArgumentException("Not valid Id: " + routeType.getId());
        }
    }

    @Override
    public RouteTypeEntity createRouteType(RouteTypeEntity routeType) {

        if (!routeTypeRepository.existsById(routeType.getId())) {
            return routeTypeRepository.save(routeType);
        }
        else {
            throw new IllegalArgumentException("Card type already exists: " + routeType.getId());
        }
    }

    @Override
    public void deleteRouteTypeById(RouteTypeEntity routeType) {
        if (routeTypeRepository.existsById(routeType.getId())) {
            routeType.setDeleted(true);
            routeTypeRepository.save(routeType);
        } else {
            throw new IllegalArgumentException("Not valid Id: " + routeType.getId());
        }
    }
}
