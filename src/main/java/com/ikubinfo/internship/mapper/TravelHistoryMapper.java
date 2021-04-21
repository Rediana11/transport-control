package com.ikubinfo.internship.mapper;

import com.ikubinfo.internship.dto.TravelHistoryDTO;
import com.ikubinfo.internship.entity.TravelHistoryEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TravelHistoryMapper extends CommonDataMapper<TravelHistoryDTO, TravelHistoryEntity>{
}
