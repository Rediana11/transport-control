package com.ikubinfo.internship.mapper;


import com.ikubinfo.internship.dto.TravelCardTypeDTO;
import com.ikubinfo.internship.entity.TravelCardTypeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TravelCardTypeMapper extends CommonDataMapper<TravelCardTypeDTO,TravelCardTypeEntity> {
}
