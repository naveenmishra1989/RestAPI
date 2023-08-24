package com.example.restapidevelopment.service;

import com.example.restapidevelopment.dto.EmpRequest;
import com.example.restapidevelopment.dto.EmpResponse;
import com.example.restapidevelopment.entity.Emp;
import com.example.restapidevelopment.repo.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpService {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private EmpRepository empRepository;

    // @Transactional(propagation = Propagation.MANDATORY)
    public List<EmpResponse> getAll ( ) {
        System.out.println("getAll");
        final List<EmpResponse> empResponses = new LinkedList<>();
        List<Emp> all = empRepository.findAll();
        if (!CollectionUtils.isEmpty(all)) {
            all.forEach(x -> {
                empResponses.add(EmpResponse.builder()
                        .emp(x)
                        .build());
            });

        }

        return empResponses;
    }

    public EmpResponse save (Emp emp) {
        final Emp save = empRepository.save(emp);
        EmpResponse empResponse = EmpResponse.builder()
                .emp(save)
                .build();
        return empResponse;
    }

    //One way
    public EmpResponse update (EmpRequest emp) {
        Optional<Emp> byId = empRepository.findById(emp.getId());
        if (byId.isPresent()) {
            final Emp emp1 = byId.get();
            emp1.setName(emp.getName());
            final Emp save = empRepository.save(emp1);
            EmpResponse empResponse = EmpResponse.builder()
                    .emp(save)
                    .build();
            return empResponse;
        }
        return null;
    }

    @Transactional
    public EmpResponse updateData (EmpRequest emp) {
        Integer update = empRepository.update(emp.getId(), emp.getName(), LocalDateTime.now());
        if (update >= 1) {
            Optional<Emp> byId = empRepository.findById(emp.getId());
            if (byId.isPresent())
                return EmpResponse.builder().emp(byId.get()).build();
        }
        return null;
    }

    @Cacheable("emp")  // cache created with emp name
    public EmpResponse findById (Integer id) {
        System.out.println("-------->findById:" + id);
        cacheManager.getCache("emp").clear(); //clean cache for every new entry
        return EmpResponse.builder().emp(empRepository.findById(id).get()).build();
    }

    public void evictAllCaches ( ) {
        cacheManager.getCacheNames().stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

    public List<Emp> findByName (String name) {
        return empRepository.findByName(name);
    }

}
