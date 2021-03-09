package com.ikubinfo.internship.mapping;

import com.ikubinfo.internship.dto.PersonDTO;
import com.ikubinfo.internship.entity.PersonEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {

    PersonDTO personToPersonDTO(PersonEntity entity);
    PersonEntity personDTOToPerson(PersonDTO dto);
}
