package com.example.restapidevelopment.controller;

import com.example.restapidevelopment.dto.EmpRequest;
import com.example.restapidevelopment.dto.EmpResponse;
import com.example.restapidevelopment.entity.Emp;
import com.example.restapidevelopment.service.EmpService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/app")
public class EmpController {

    @Autowired
    private EmpService empService;


    //localhost:8384//app/all
    @GetMapping("/all")
    public ResponseEntity<List<EmpResponse>> getAll() {
        List<EmpResponse> all = empService.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    //localhost:8384//app/findById/{id}
    @GetMapping("/findById/{id}")
    public ResponseEntity<EmpResponse> findById(Integer id) {
        log.info("findById");
        EmpResponse mep = empService.findById(id);
        return new ResponseEntity<>(mep, HttpStatus.OK);
    }

    //localhost:8384//app/save
    @PostMapping("/save")
    public ResponseEntity<EmpResponse> saveEmp(@RequestBody Emp emp){
        EmpResponse empResponse = empService.save(emp);
        return new ResponseEntity<>(empResponse, HttpStatus.OK);
    }

    //localhost:8384//app/update
    @PutMapping("/update")
    public ResponseEntity<EmpResponse> updateEmp(@RequestBody EmpRequest emp){
        EmpResponse empResponse = empService.update(emp);
        return new ResponseEntity<>(empResponse, HttpStatus.OK);
    }

    //localhost:8384//app/updateData
    @PutMapping("/updateData")
    public ResponseEntity<EmpResponse> updateData(@RequestBody EmpRequest emp){
        EmpResponse empResponse = empService.updateData(emp);
        return new ResponseEntity<>(empResponse, HttpStatus.OK);
    }

    @GetMapping("/clearAllCaches")
    public void clearAllCaches() {
     log.info("clearAllCaches");
        empService.evictAllCaches();
    }

    //AutoCached after 6 second delete
    @Scheduled(fixedRate = 6000)
    public void evictAllCachesAtIntervals() {
        empService.evictAllCaches();
    }

    //localhost:8384//app/getEmpByName/{name}
    @GetMapping("/getEmpByName/{name}")
    public ResponseEntity<List<Emp>> getEmpByName(String name) {
        return new ResponseEntity<>(empService.findByName(name), HttpStatus.OK);
    }
}
