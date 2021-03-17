package com.ikubinfo.internship.mapper;

import com.ikubinfo.internship.dto.RouteDTO;
import com.ikubinfo.internship.entity.RouteEntity;
import org.mapstruct.Mapper;

@Mapper
public interface RouteMapper extends CommonDataMapper<RouteDTO,RouteEntity> {

}
