package com.ikubinfo.internship.mapping;

import com.ikubinfo.internship.dto.RoleDTO;
import com.ikubinfo.internship.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {


    RoleDTO roleToRoleDTO(RoleEntity entity);
    RoleEntity roleDTOToRole(RoleDTO dto);
}
