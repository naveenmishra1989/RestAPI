package com.example.restapidevelopment;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Log4j2
@DisplayName("Example of Junit 5")
class SampleJunit5Example {

    @BeforeAll //set up for all
    static  void beforeClass(){
    log.info("BeforeAll");
    }

    @BeforeEach //execute before for each test
    void beforeMethod(){
        log.info("beforeMethod");
    }

    @Test  //declaring a test
    void test1(){
        log.info("test1");
    }

    @Test  //declaring a test
    void test2(){
        log.info("test2");
    }

    @Disabled // Disabling a test
    @Test //declaring a test
    void test3(){
        log.info("test3");
    }

    @DisplayName("Example of multiple string value")
    @ParameterizedTest
    @ValueSource(strings = {"naveen","mishra"})
    void testParam(String fName){
        log.info(fName);
        assertNotNull(fName);
    }

    static Stream<String> stringProvider(){//provider method should be static
        return Stream.of("sarita","shaw");
    }

    static Stream<Arguments> argsProvider(){// for multiple diff values
        return Stream.of(Arguments.of("sarita",123));
    }
    @ParameterizedTest
    @MethodSource("stringProvider")
     void testMethodSource(String fName){
        log.info(fName);
        assertNotNull(fName);
    }

    @ParameterizedTest
    @MethodSource("argsProvider")
    void testArgMethodSource(String fName,int value){
        log.info(fName+" "+value);
        assertNotNull(fName);
        assertEquals(123,value);
    }


    @AfterEach //execute after for each test
    void afterMethod(){
        log.info("afterMethod");
    }

    @AfterAll//teardown for all
    static void afterClass(){
        log.info("afterClass");
    }



}
