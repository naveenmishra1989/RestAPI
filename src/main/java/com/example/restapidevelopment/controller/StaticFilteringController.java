package com.example.restapidevelopment.controller;

import com.example.restapidevelopment.dto.OtherBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/static")
public class StaticFilteringController {

    //localhost:8384/static/details
    @GetMapping("/details")
    public OtherBean getOtherBean(){
        final OtherBean otherBean = new OtherBean("naveen","mishra",30,111,"R1");
        return otherBean;
    }
}
