package com.ikubinfo.internship.repository;

import com.ikubinfo.internship.entity.TravelCardTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelCardTypeRepository extends CommonRepository<TravelCardTypeEntity>, JpaRepository<TravelCardTypeEntity, Long> {


}
