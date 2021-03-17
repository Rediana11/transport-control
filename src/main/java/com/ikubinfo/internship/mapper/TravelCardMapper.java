package com.ikubinfo.internship.mapper;

import com.ikubinfo.internship.dto.TravelCardDTO;
import com.ikubinfo.internship.entity.TravelCardEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TravelCardMapper extends CommonDataMapper<TravelCardDTO,TravelCardEntity> {
}
