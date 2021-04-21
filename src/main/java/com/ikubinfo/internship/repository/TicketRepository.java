package com.ikubinfo.internship.repository;

import com.ikubinfo.internship.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository  extends JpaRepository<TicketEntity, Long> {

    TicketEntity findByRouteId(Long routeId);
}
