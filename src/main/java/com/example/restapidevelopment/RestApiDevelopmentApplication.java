package com.example.restapidevelopment;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.example.restapidevelopment.config.DummyData;
import com.example.restapidevelopment.entity.Car;
import com.example.restapidevelopment.entity.Emp;
import com.example.restapidevelopment.entity.Person;
import com.example.restapidevelopment.repo.CarRepository;
import com.example.restapidevelopment.repo.EmpRepository;
import com.example.restapidevelopment.repo.PersonRepository;

import lombok.extern.log4j.Log4j2;

/**
 * http://localhost:8384/swagger-ui.html   <i>swagger home page</i>
 */

@SpringBootApplication
@EnableCaching  //enabling cache
@Log4j2
public class RestApiDevelopmentApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private CarRepository carRepository;

	@Autowired
	private EmpRepository empRepository;

	void empInitData() {
		empRepository.saveAll(Arrays.asList(
				Emp.builder()
						.name("naveen")
						.build(),
				Emp.builder()
						.name("Kumar")
						.build(),
				Emp.builder()
						.name("Kumar")
						.build()));
	}


	public static void main(String[] args) {
		SpringApplication.run(RestApiDevelopmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		empInitData();
		List<Person> personList = DummyData.getPeople();
		List<Car> carList = DummyData.getCars();
		long personCount = personRepository.count();
		long carCount = carRepository.count();
		if (personCount>=50)
			log.info("Person record more than 50 no longer deletion records : "+personCount +" >= 50");
		else {
			personRepository.deleteAll();
			List<Person> personList1  = personRepository.saveAll(personList);
			if(!personList1.isEmpty()){
				log.info("List of people table is created and records are : "+personRepository.count());
			}else {
				log.info("List of people table is not created");
			}
		}
		if(carCount>=50)
			log.info("Car record more than 50 no longer deletion records :"+carCount +" >= 50");
		else {
			carRepository.deleteAll();
			List<Car> carList1 =  carRepository.saveAll(carList);
			if(!carList1.isEmpty()){
				log.info("List of car table is created and records are :"+carRepository.count());
			}else {
				log.info("List of car table is not created");
			}
		}



	}
}
