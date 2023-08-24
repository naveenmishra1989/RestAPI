package com.example.restapidevelopment.config;

import com.example.restapidevelopment.entity.Person;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

class DummyDataTest {
    @Test
    void test() throws IOException {

        List<Person> people1 = DummyData.getPeople();
        List<Person> result = new ArrayList<>();
        List<Person> people2 = DummyData.getPeoplev2();

        people2.forEach(data -> {

            Optional<Person> findAny = people1.stream().filter(p -> p.getId().equals(data.getId())).findAny();
            if (findAny.isPresent()) {

                if (!Objects.equals(data, findAny.get())) {
                    result.add(data);

                }
            }

        });


        result.forEach(System.out::println);

    }
}