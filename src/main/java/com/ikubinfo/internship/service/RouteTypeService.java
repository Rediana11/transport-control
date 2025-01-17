package com.ikubinfo.internship.service;

import com.ikubinfo.internship.dto.RouteTypeDTO;
import com.ikubinfo.internship.entity.RouteEntity;
import com.ikubinfo.internship.entity.RouteTypeEntity;
import com.ikubinfo.internship.exception.EntityNotFoundException;
import com.ikubinfo.internship.exception.TypeInUseException;
import com.ikubinfo.internship.mapper.RouteTypeMapper;
import com.ikubinfo.internship.repository.RouteTypeRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RouteTypeService  {

    @Autowired
    private RouteTypeRepository routeTypeRepository;

    private RouteTypeMapper mapper
            = Mappers.getMapper(RouteTypeMapper.class);

    public List<RouteTypeDTO> getAllRouteTypes() {
        List<RouteTypeEntity> routeTypes = routeTypeRepository.findByIsDeleted(false);

        return mapper.toDto(routeTypes);
    }

    public RouteTypeDTO getRouteTypeById(Long id) {
        Optional<RouteTypeEntity> cardType = routeTypeRepository.findById(id);

        return mapper.toDto(cardType.orElseThrow(() -> new EntityNotFoundException("Not valid Id: " + id)));
    }

    public RouteTypeDTO updateRouteType(RouteTypeDTO routeType) {
        if (routeTypeRepository.isRouteTypeInUse(routeType.getId())>0){
            throw new TypeInUseException("This route type is already in use, can not update it!");
        }
        else if (routeTypeRepository.existsById(routeType.getId())) {
            routeType.setUpdatedOn(new Date());
            RouteTypeEntity updatedRouteType = routeTypeRepository.save(mapper.toEntity(routeType));
            return mapper.toDto(updatedRouteType);
        } else {
            throw new EntityNotFoundException("Not valid Id: " + routeType.getId());
        }
    }

    public RouteTypeDTO createRouteType(RouteTypeDTO routeType) {
        routeType.setCreated_on(new Date());
        RouteTypeEntity newTypeEntity = routeTypeRepository.save(mapper.toEntity(routeType));
        return mapper.toDto(newTypeEntity);
    }

    public void deleteRouteTypeById(long id) {

        if (routeTypeRepository.isRouteTypeInUse(id)>0){
            throw new TypeInUseException("This route type is already in use, can not delete it!");
        }
        else if (routeTypeRepository.existsById(id)) {
            Optional<RouteTypeEntity> route= routeTypeRepository.findById(id);
            route.get().setDeleted(true);
            routeTypeRepository.save(route.get());
        }else {
            throw new EntityNotFoundException("Not valid Id: " + id);
        }
    }
}
