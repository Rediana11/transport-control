package com.ikubinfo.internship.repository;

import com.ikubinfo.internship.entity.TravelCardTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelCardTypeRepository extends CommonRepository<TravelCardTypeEntity>, JpaRepository<TravelCardTypeEntity, Long> {

    @Query(value= "Select count(travel_card_type_id) as count from travel_card where travel_card_type_id = ? ", nativeQuery = true)
    Long isCardTypeInUse(Long id);

    TravelCardTypeEntity findByName(String name);
}
