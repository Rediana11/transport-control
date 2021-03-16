package com.ikubinfo.internship.mapper;

import com.ikubinfo.internship.dto.RoleDTO;
import com.ikubinfo.internship.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper extends DataMapper<RoleDTO,RoleEntity>{
}
