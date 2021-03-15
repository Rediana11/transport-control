package com.ikubinfo.internship.service.serviceImpl;

import com.ikubinfo.internship.dto.PersonDTO;
import com.ikubinfo.internship.dto.RoleDTO;
import com.ikubinfo.internship.entity.PersonEntity;
import com.ikubinfo.internship.entity.RoleEntity;
import com.ikubinfo.internship.mapping.PersonMapper;
import com.ikubinfo.internship.mapping.RoleMapper;
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
public class UserServiceImpl  implements UserDetailsService {

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
        return mapper.personToPersonDTO(personEntity);
    }

    public PersonDTO createUser(PersonDTO person) {
        if (!personRepository.existsById(person.getId())) {

            List<RoleDTO> roles = new ArrayList<>();
            RoleEntity roleEntity = roleRepository.findByCode("2R");
            roles.add(roleMapper.roleToRoleDTO(roleEntity));
            person.setPassword(bCryptPasswordEncoder.encode(mapper.personDTOToPerson(person).getPassword()));
            person.setRoles(roles);
            PersonEntity personEntity = personRepository.save(mapper.personDTOToPerson(person));
            return mapper.personToPersonDTO(personEntity);
        }
        else {
            throw new IllegalArgumentException("User already exists: " + person.getId());
        }

    }
}
