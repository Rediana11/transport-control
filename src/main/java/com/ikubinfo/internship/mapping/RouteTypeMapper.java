package com.ikubinfo.internship.mapping;


import com.ikubinfo.internship.dto.RouteTypeDTO;
import com.ikubinfo.internship.entity.RouteTypeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface RouteTypeMapper {


    RouteTypeDTO routeTypeToRouteTypeDTO(RouteTypeEntity entity);
    RouteTypeEntity routeTypeDTOToRouteType(RouteTypeDTO dto);
}
