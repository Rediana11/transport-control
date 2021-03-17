package com.ikubinfo.internship.mapper;

import com.ikubinfo.internship.dto.PersonDTO;
import com.ikubinfo.internship.entity.PersonEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper extends CommonDataMapper<PersonDTO,PersonEntity> {

}
