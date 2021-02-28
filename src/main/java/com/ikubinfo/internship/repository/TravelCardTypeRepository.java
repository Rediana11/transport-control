package com.ikubinfo.internship.repository;

import com.ikubinfo.internship.dto.TravelCardDTO;
import com.ikubinfo.internship.dto.TravelCardTypeDTO;
import com.ikubinfo.internship.entity.TravelCardTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TravelCardTypeRepository extends JpaRepository<TravelCardTypeEntity, Long> {

    @Query(
            value = "select * from travel_card_type where is_deleted=false",
            nativeQuery = true)
    List<TravelCardTypeEntity> findAllValidCardTypes();
}
