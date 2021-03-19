package com.ikubinfo.internship.controller;

import com.ikubinfo.internship.dto.AuthDTO;
import com.ikubinfo.internship.dto.PersonDTO;
import com.ikubinfo.internship.exception.RestExceptionHandler;
import com.ikubinfo.internship.service.AuthService;
import com.ikubinfo.internship.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    public static Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login",produces = {"application/json"},consumes = {"application/json"})
    public ResponseEntity<PersonDTO> createAuthenticationToken(@RequestBody AuthDTO authDTO) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION,"Bearer " + authService.authenticate(authDTO));
        logger.info("Successful login");

      return new ResponseEntity<>((userService.loadPersonByUsername(authDTO.getUsername())),headers, HttpStatus.OK);
    }

    @PostMapping(value = "/create-user",produces = {"application/json"},consumes = {"application/json"})
    public ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok().body(userService.createUser(personDTO));
    }

}
