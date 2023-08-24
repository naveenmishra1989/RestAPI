package com.example.restapidevelopment.config;

import com.example.restapidevelopment.entity.Car;
import com.example.restapidevelopment.entity.Person;
import com.example.restapidevelopment.entity.Todo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class DummyData {
  @Autowired
  private ResourceLoader resourceLoader;

    public static List<Person> getPeople() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = DummyData.class.getClassLoader();
        //using file
        File file = new File(classLoader.getResource("people.json").getFile());
        List<Person> personList = objectMapper.readValue(file, new TypeReference<List<Person>>(){});
        return  personList;
    }

    public static List<Person> getPeoplev2() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = DummyData.class.getClassLoader();
        //using file
        File file = new File(classLoader.getResource("peoplev2.json").getFile());
        List<Person> personList = objectMapper.readValue(file, new TypeReference<List<Person>>(){});
        return  personList;
    }

    public static List<Car> getCars() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = DummyData.class.getClassLoader();
        //using InputStream
        InputStream input = classLoader.getResourceAsStream("cars.json");
        List<Car> carList = objectMapper.readValue(input, new TypeReference<List<Car>>(){});
        return carList;
    }

    public static List<Todo> getTodo() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //using URL
        URL url = new URL("https://jsonplaceholder.typicode.com/todos");
        List<Todo> todoList = objectMapper.readValue(url, new TypeReference<List<Todo>>(){});
        return  todoList;
    }
    
        public  List<Person> personList() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Resource resource = resourceLoader.getResource("classpath:people.json");
        InputStream input = resource.getInputStream();
        List<Person> personList = objectMapper.readValue(input, new TypeReference<List<Person>>() {
        });
        return personList;
    }

    public static List<Car> carList() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Resource resource = new ClassPathResource("cars.json");
        InputStream input = resource.getInputStream();
        List<Car> cars = objectMapper.readValue(input, new TypeReference<List<Car>>() {
        });
        return cars;
    }

}
