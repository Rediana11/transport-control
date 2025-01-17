package com.ikubinfo.internship.repository;

import com.ikubinfo.internship.entity.TravelCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelCardRepository extends CommonRepository<TravelCardEntity>,JpaRepository<TravelCardEntity, Long> {



}
