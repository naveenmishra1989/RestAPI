package com.example.restapidevelopment.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private  Integer id;
  private  String make;
  private  String model;
  private  String color;
  private  Integer year;
  private  Double price;


}
