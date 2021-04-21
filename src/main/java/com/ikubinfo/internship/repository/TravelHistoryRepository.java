package com.ikubinfo.internship.repository;

import com.ikubinfo.internship.entity.TravelCardEntity;
import com.ikubinfo.internship.entity.TravelHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelHistoryRepository extends  JpaRepository<TravelHistoryEntity, Long> {

    TravelHistoryEntity findTravelHistoryEntityByTravelCardId(Long id);
}
