package com.ikubinfo.internship.mapping;

import com.ikubinfo.internship.dto.RouteDTO;
import com.ikubinfo.internship.entity.RouteEntity;
import org.mapstruct.Mapper;

@Mapper
public interface RouteMapper {

    RouteDTO routeToRouteDTO(RouteEntity entity);
    RouteEntity routeDTOToRoute(RouteDTO dto);
}
