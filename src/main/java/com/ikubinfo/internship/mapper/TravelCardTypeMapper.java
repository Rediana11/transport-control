package com.ikubinfo.internship.mapper;


import com.ikubinfo.internship.dto.TravelCardTypeDTO;
import com.ikubinfo.internship.entity.TravelCardTypeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TravelCardTypeMapper extends DataMapper<TravelCardTypeDTO,TravelCardTypeEntity>{
}
