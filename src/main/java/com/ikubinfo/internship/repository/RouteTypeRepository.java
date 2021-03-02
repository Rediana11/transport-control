package com.ikubinfo.internship.repository;


import com.ikubinfo.internship.entity.RouteTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteTypeRepository extends JpaRepository<RouteTypeEntity, Long> {

  List<RouteTypeEntity> findByIsDeleted(boolean deleted);

}