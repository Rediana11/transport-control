package com.ikubinfo.internship.repository;


import com.ikubinfo.internship.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity,Long> {

    PersonEntity findPersonByUsername(String username);

    PersonEntity findPersonByEmail(String email);
}
