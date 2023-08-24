package com.example.restapidevelopment.controller;

import com.example.restapidevelopment.dto.Lombok;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class LombokController {


    //localhost:8384//api/lombok
    @GetMapping("/lombok")
    public ResponseEntity<Lombok> getResponse(){
        final Lombok lombok = Lombok.builder()
                .id(25254)
                .name("sonu")
                .sub(Arrays.asList("English","Hindi","Science"))
                .build();
        System.out.println(lombok);
        return new ResponseEntity<>(lombok, HttpStatus.OK);
    }
}
