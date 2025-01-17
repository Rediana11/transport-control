package com.ikubinfo.internship.repository;


import com.ikubinfo.internship.entity.RouteTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteTypeRepository extends CommonRepository<RouteTypeEntity>, JpaRepository<RouteTypeEntity, Long> {

    @Query(value= "Select count(route_type_id) as count from route_route_type where route_type_id = ? ", nativeQuery = true)
    Long isRouteTypeInUse(Long id);

}