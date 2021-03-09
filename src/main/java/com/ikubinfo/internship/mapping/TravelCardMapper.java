package com.ikubinfo.internship.mapping;

import com.ikubinfo.internship.dto.TravelCardDTO;
import com.ikubinfo.internship.entity.TravelCardEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TravelCardMapper {


    TravelCardDTO cardToCardDTO(TravelCardEntity entity);
    TravelCardEntity cardDTOToCard(TravelCardDTO dto);
}
