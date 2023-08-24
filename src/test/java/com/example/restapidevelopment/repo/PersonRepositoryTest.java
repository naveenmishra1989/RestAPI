package com.example.restapidevelopment.repo;

import com.example.restapidevelopment.dto.PersonDto;
import com.example.restapidevelopment.entity.Person;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Log4j2
class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    @Transactional
    void findAllByAge ( ) {
        List<Person> personList = personRepository.findAllByAge(30).get();
        assertNotNull(personList);
        log.info("personList :" + personList);
    }

    @Test
    @Transactional
    void deleteByAge ( ) {
        personRepository.deleteById(30);
    }

    @Test
    void getListOfMail ( ) {
        List<String> email = personRepository.getEmail();
        if (!CollectionUtils.isEmpty(email)) {
            log.info(email);
        }
    }


    @Test
    void findFirstName ( ) {
        List<String> firstName = personRepository.findFirstName();
        if (!CollectionUtils.isEmpty(firstName)) {
            log.info(firstName);
        }
    }

    @Test
    void changeName ( ) {
        personRepository.changeName(70, "naveen");
        Optional<Person> byId = personRepository.findById(70);
        if (byId.isPresent())
            log.info(byId.get());
    }

    @Test
    void getPersonCount ( ) {
        long personCount = personRepository.getPersonCount();
        log.info(personCount);
    }

    @Test
    void findByGenderAndAge ( ) {
        List<Person> peopleList = personRepository.findByGenderAndAge("Male", 61);
        if (!CollectionUtils.isEmpty(peopleList))
            log.info(peopleList);
    }

    @Test
    void changeGender ( ) {
        Integer male = personRepository.changeGender(54, "Male");
        log.info(male);
    }

    @Test
    void getPartialData ( ) {
        Optional<List<PersonDto>> partialData = Optional.ofNullable(personRepository.findByPartialData());
        if (partialData.isPresent())
            log.info(partialData.get());
    }

    @Test
    void getPage ( ) {
        //Pageable
        Pageable pageable = PageRequest.of(58, 17);
        final Page<Person> all = personRepository.findAll(pageable);
        all.stream().forEach(System.out::println);
        log.info(all.getNumberOfElements());
        log.info(all.getTotalPages());
    }

    @Test
    void findByAgeIn ( ) {
        List<Object[]> byAgeIn = personRepository.findByAgeIn(Arrays.asList(55,66));
        for (int i = 0; i < byAgeIn.size(); i++) {
            Object[] objects = byAgeIn.get(i);
            System.out.println(objects[0] + "," + objects[1] + "," + objects[2] + "," + objects[3]);
        }
    }

    @Test
    void testExample ( ) {

        Example<Person> example =  Example.<Person>of(Person.builder().id(null).age(55).build(), ExampleMatcher.matching().withIgnoreNullValues());
        final List<Person> people = personRepository.findAll(example,Sort.by(Sort.Direction.DESC,"firstName"));
        people.forEach(System.out::println);
    }

}