package com.ikubinfo.internship.repository;


import com.ikubinfo.internship.entity.RouteTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteTypeRepository extends JpaRepository<RouteTypeEntity, Long> {

  RouteTypeEntity findByName(String name);
}