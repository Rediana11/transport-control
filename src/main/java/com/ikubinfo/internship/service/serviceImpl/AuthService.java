package com.ikubinfo.internship.service.serviceImpl;

import com.ikubinfo.internship.dto.AuthDTO;
import com.ikubinfo.internship.entity.PersonEntity;
import com.ikubinfo.internship.repository.PersonRepository;
import com.ikubinfo.internship.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private PersonRepository personRepository;

    private AuthenticationManagerBuilder authenticationManagerBuilder;

    private TokenProvider tokenProvider;

    @Autowired
    public AuthService(PersonRepository personRepository, AuthenticationManagerBuilder authenticationManagerBuilder, TokenProvider tokenProvider) {
        this.personRepository = personRepository;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.tokenProvider = tokenProvider;
    }

    public String authenticate (AuthDTO authDTO){
        PersonEntity user = personRepository.findPersonByUsername(authDTO.getUsername());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.createToken(authentication);
    }
}
