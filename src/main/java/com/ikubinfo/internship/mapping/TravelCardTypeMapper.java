package com.ikubinfo.internship.mapping;


import com.ikubinfo.internship.dto.TravelCardTypeDTO;
import com.ikubinfo.internship.entity.TravelCardTypeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TravelCardTypeMapper {


    TravelCardTypeDTO cardTypeToCardTypeDTO(TravelCardTypeEntity entity);
    TravelCardTypeEntity cardTypeDTOToCardType(TravelCardTypeDTO dto);
}
