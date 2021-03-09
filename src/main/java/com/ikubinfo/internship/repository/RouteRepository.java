package com.ikubinfo.internship.repository;

import com.ikubinfo.internship.entity.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends CommonRepository<RouteEntity>, JpaRepository<RouteEntity, Long> {


}
