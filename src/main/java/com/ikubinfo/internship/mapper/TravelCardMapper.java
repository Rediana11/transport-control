package com.ikubinfo.internship.mapper;

import com.ikubinfo.internship.dto.TravelCardDTO;
import com.ikubinfo.internship.entity.TravelCardEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TravelCardMapper extends DataMapper<TravelCardDTO,TravelCardEntity>{
}
