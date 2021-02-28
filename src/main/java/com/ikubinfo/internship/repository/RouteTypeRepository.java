package com.ikubinfo.internship.repository;


import com.ikubinfo.internship.dto.RouteTypeDTO;
import com.ikubinfo.internship.entity.RouteTypeEntity;
import com.ikubinfo.internship.entity.TravelCardTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteTypeRepository extends JpaRepository<RouteTypeEntity, Long> {

  @Query(
          value = "select * from route_type where is_deleted=false",
          nativeQuery = true)
  List<RouteTypeEntity> findAllValidRouteTypes();
}