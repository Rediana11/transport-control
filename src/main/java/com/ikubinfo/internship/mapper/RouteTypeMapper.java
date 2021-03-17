package com.ikubinfo.internship.mapper;


import com.ikubinfo.internship.dto.RouteTypeDTO;
import com.ikubinfo.internship.entity.RouteTypeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface RouteTypeMapper extends CommonDataMapper<RouteTypeDTO,RouteTypeEntity> {
}
