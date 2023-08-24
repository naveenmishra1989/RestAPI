package com.example.restapidevelopment.repo;

import com.example.restapidevelopment.entity.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EmpRepository extends JpaRepository<Emp,Integer> {

    //2nd way to update
    @Modifying
    @Query("update Emp e set e.name= ?2 , e.leavingDate =?3 where e.id= ?1")
    public Integer update(Integer id, String name,LocalDateTime localDateTime);
    List<Emp> findByName(String name);
}
