package com.ikubinfo.internship.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class HelloController {

  @GetMapping("greetings")
  public ResponseEntity<String> greetings() {
    return new ResponseEntity<String>("Hello from spring boot rest api", HttpStatus.OK);
  }
}
