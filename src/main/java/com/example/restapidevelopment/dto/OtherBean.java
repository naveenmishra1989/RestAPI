package com.example.restapidevelopment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * If we are using both filtering class and field level then class level filtering override field level
 * filtering .
 */
@Data
@AllArgsConstructor
@JsonIgnoreProperties(value = {"age","clg"}) //class level
public class OtherBean {
    private String fName;
    @JsonIgnore //field level
    private String lName;
    private Integer age;
    private Integer id;
    private String clg;

}
