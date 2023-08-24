package com.example.restapidevelopment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.restapidevelopment.entity.Person;
import com.example.restapidevelopment.exception.UnAuthenticException;
import com.example.restapidevelopment.repo.PersonRepository;

@ExtendWith(MockitoExtension.class)
class PersonJPAControllerTest {

    @InjectMocks
    PersonJPAController personJPAController;

    @Mock
    private PersonRepository personRepository;


    ArrayList<Person>  setUp() {
        ArrayList<Person>  personList = new ArrayList<>();
        personList.add(Person.builder().id(12345).firstName("naveen").lastName("kumar").age(30).gender("Male").email("kumar@gmail.com").build());
        personList.add(Person.builder().id(12346).firstName("naveen").lastName("mishra").age(35).gender("Male").email("naveen@gmail.com").build());
        return personList;
    }

    @Test
    void getPersonList() {
        when(personRepository.findAll()).thenReturn(setUp());
        ResponseEntity<List<Person>> entity = personJPAController.getPersonList();
        List<Person> personLists = entity.getBody();
        assertEquals(HttpStatus.OK,entity.getStatusCode());
        assertEquals(2,personLists.size());

    }

    @Test
    void testGetPersonList() {
        when(personRepository.findAll()).thenReturn(setUp());
        ResponseEntity<List<Person>> entity = personJPAController.getPersonList(1);
        List<Person> personLists = entity.getBody();
        assertEquals(HttpStatus.OK,entity.getStatusCode());
        assertEquals(1,personLists.size());
    }

    private static Stream<Integer> getIntValues(){
        return Stream.of(12345,12346);
    }
    @ParameterizedTest
    @MethodSource("getIntValues")
    void findById(Integer val) {
        when(personRepository.findById(anyInt())).thenReturn(Optional.of(setUp().get(0)));
        ResponseEntity<?> entity = personJPAController.findById(val);
        assertEquals( HttpStatus.OK,entity.getStatusCode());
    }

    @Test
    void deleteById() {
        when(personRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(personRepository).deleteById(anyInt());
        ResponseEntity<?> entity = personJPAController.deleteById(123);
        String result = (String) entity.getBody();
        assertEquals( HttpStatus.OK ,entity.getStatusCode());
        assertEquals(true,result.contains("123"));
    }

    @Test
    void create() {
        when(personRepository.saveAndFlush(any(Person.class))).thenReturn(setUp().get(0));
        when(personRepository.existsById(anyInt())).thenReturn(true);
        ResponseEntity<?> entity = personJPAController.create(setUp().get(0));
        String result = (String) entity.getBody();
        assertEquals(HttpStatus.CREATED,entity.getStatusCode());
        assertEquals(true,result.contains("12345"));
    }

    @Test
    void createBadRequest() {
        when(personRepository.saveAndFlush(any(Person.class))).thenReturn(setUp().get(1));

        Exception exception = assertThrows(
                UnAuthenticException.class,
                () -> personJPAController.create(setUp().get(0)));
        assertEquals("New person record not created",exception.getMessage());

    }

    @Test
    void update() {
        when(personRepository.save(any(Person.class))).thenReturn(setUp().get(0));
        ResponseEntity<?> entity = personJPAController.update(setUp().get(0));
        assertEquals(HttpStatus.CREATED,entity.getStatusCode());
    }
}