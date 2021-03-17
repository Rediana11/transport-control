package com.ikubinfo.internship.service;

import com.ikubinfo.internship.dto.PersonDTO;
import com.ikubinfo.internship.dto.RoleDTO;
import com.ikubinfo.internship.entity.PersonEntity;
import com.ikubinfo.internship.entity.RoleEntity;
import com.ikubinfo.internship.exception.EntityNotFoundException;
import com.ikubinfo.internship.mapper.PersonMapper;
import com.ikubinfo.internship.mapper.RoleMapper;
import com.ikubinfo.internship.repository.PersonRepository;
import com.ikubinfo.internship.repository.RoleRepository;
import com.ikubinfo.internship.security.model.SecurityUser;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private PersonMapper mapper
            = Mappers.getMapper(PersonMapper.class);

    private RoleMapper roleMapper
            = Mappers.getMapper(RoleMapper.class);

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        PersonEntity personEntity =  personRepository.findPersonByUsername(s);
        return new SecurityUser(personEntity);
    }

    public PersonDTO loadPersonByUsername(String s){
        PersonEntity personEntity =  personRepository.findPersonByUsername(s);
        return mapper.toDto(personEntity);
    }

    public PersonDTO createUser(PersonDTO person) {

            Set<RoleDTO> roles = new HashSet<>();
            RoleEntity roleEntity = roleRepository.findByCode("2R");
            roles.add(roleMapper.toDto(roleEntity));
            person.setPassword(bCryptPasswordEncoder.encode(mapper.toEntity(person).getPassword()));
            person.setRoles(roles);
            PersonEntity personEntity = personRepository.save(mapper.toEntity(person));
            return mapper.toDto(personEntity);
        }



}
