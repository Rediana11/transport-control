package com.ikubinfo.internship.repository;

import com.ikubinfo.internship.entity.TravelCardTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelCardTypeRepository extends JpaRepository<TravelCardTypeEntity, Long> {


    List<TravelCardTypeEntity> findByIsDeleted(boolean deleted);


}
