package com.ikubinfo.internship.mapper;


import com.ikubinfo.internship.dto.RouteTypeDTO;
import com.ikubinfo.internship.entity.RouteTypeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RouteTypeMapper extends DataMapper<RouteTypeDTO,RouteTypeEntity>{
}
