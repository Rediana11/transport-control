package com.ikubinfo.internship.service.serviceImpl;

import com.ikubinfo.internship.entity.RouteTypeEntity;
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
    RouteTypeRepository routeTypeRepository;


    @Override
    public List<RouteTypeEntity> getAllRouteTypes() {
        List<RouteTypeEntity> routeTypes = routeTypeRepository.findAllValidRouteTypes();

        if (routeTypes.size() > 0) {
            return routeTypes;
        } else {
            return new ArrayList<RouteTypeEntity>();
        }
    }

    @Override
    public RouteTypeEntity getRouteTypeById(Long id) {
        Optional<RouteTypeEntity> routeType = routeTypeRepository.findById(id);

        if (routeType.isPresent()) {
            return routeType.get();
        } else {
            throw new InvalidConfigurationPropertyValueException("No route type record exist for given id", routeType, "");
        }    }

    @Override
    public RouteTypeEntity updateRouteType(RouteTypeEntity routeType) {
        Optional < RouteTypeEntity > route = this.routeTypeRepository.findById(routeType.getId());

        if (route.isPresent()) {
            RouteTypeEntity newEntity = route.get();
            newEntity.setId(routeType.getId());
            newEntity.setName(routeType.getName());
            newEntity.setDescription(routeType.getDescription());
            newEntity.setCode(routeType.getCode());
            routeTypeRepository.save(newEntity);

            return newEntity;
        } else {
            throw new  InvalidConfigurationPropertyValueException("No route type record exist for given id", routeType, "");
        }    }

    @Override
    public RouteTypeEntity createRouteType(RouteTypeEntity routeType) {
        return routeTypeRepository.save(routeType);
    }

    @Override
    public void deleteRouteTypeById(Long id) {
        Optional<RouteTypeEntity> route = routeTypeRepository.findById(id);

        if (route.isPresent()) {
            RouteTypeEntity newEntity = route.get();
            newEntity.setDeleted(false);
        } else {
            throw new InvalidConfigurationPropertyValueException("No route type record exist for given id", route, "");
        }
    }
}
