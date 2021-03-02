package com.ikubinfo.internship.repository;

import com.ikubinfo.internship.entity.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, Long> {

    List<RouteEntity> findByIsDeleted(boolean deleted);

}
