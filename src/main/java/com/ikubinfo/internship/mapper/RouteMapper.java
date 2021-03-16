package com.ikubinfo.internship.mapper;

import com.ikubinfo.internship.dto.RouteDTO;
import com.ikubinfo.internship.entity.RouteEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RouteMapper extends DataMapper<RouteDTO,RouteEntity>{

}
