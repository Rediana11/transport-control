package com.ikubinfo.internship.controller;

import com.ikubinfo.internship.dto.AuthDTO;
import com.ikubinfo.internship.dto.PersonDTO;
import com.ikubinfo.internship.service.AuthService;
import com.ikubinfo.internship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login",produces = {"application/json"},consumes = {"application/json"})
    public ResponseEntity<PersonDTO> createAuthenticationToken(@RequestBody AuthDTO authDTO) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION,"Bearer " + authService.authenticate(authDTO));

      return new ResponseEntity<>((userService.loadPersonByUsername(authDTO.getUsername())),headers, HttpStatus.OK);
    }

    @PostMapping(value = "/create-user",produces = {"application/json"},consumes = {"application/json"})
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok().body(userService.createUser(personDTO));
    }

}
