package com.example.restapidevelopment.repo;

import com.example.restapidevelopment.dto.PersonDto;
import com.example.restapidevelopment.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
   Optional<List<Person>> findAllByAge(Integer age);
   List<Person> findByGenderAndAge(String gender,Integer age);
   List<Person> findByGenderOrAge(String gender,Integer age);
   List<Person> findByIdBetween(String gender,Integer age);
   List<Person> findByAgeLessThan(Integer age);
   List<Person> findByAgeLessThanEqual(Integer age);
   List<Person> findByAgeGreaterThan(Integer age);
   List<Person> findByAgeGreaterThanEqual(Integer age);

   @Query(value = "Select ID,AGE,EMAIL,FIRST_NAME From PERSON where AGE in(?1)",nativeQuery = true)
   List<Object[]> findByAgeIn(Collection<Integer> ages);

   @Override
   Page<Person> findAll(Pageable pageable);

   @Query("Select p.email From Person p")  //LIMIT 1
   List<String> getEmail();
   //findTop1ByOrderByKpmcEocRegionidUpdateDatetimeDesc

   //Partial data fetching
   @Query("Select new com.example.restapidevelopment.dto.PersonDto(firstName,age) From Person")
   List<PersonDto> findByPartialData();


   @Query(value = "Select FIRST_NAME From PERSON",nativeQuery = true)
   List<String> findFirstName();

   @Query("SELECT COUNT(*) FROM Person")
   long getPersonCount();

   //Named parameter
   @Modifying
   @Query("UPDATE Person p SET p.firstName = :firstName WHERE p.id = :idNum")
   Integer changeName(@Param("idNum") Integer id, @Param("firstName") String name);

   //Indexed parameter
   @Modifying
   @Query("UPDATE Person p SET p.gender = ?2 WHERE p.id = ?1 ")
   Integer changeGender(Integer id,String gender);

}
