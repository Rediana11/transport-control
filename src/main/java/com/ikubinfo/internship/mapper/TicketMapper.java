package com.ikubinfo.internship.mapper;

import com.ikubinfo.internship.dto.TicketDTO;
import com.ikubinfo.internship.entity.TicketEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TicketMapper  extends CommonDataMapper<TicketDTO, TicketEntity>{
}
