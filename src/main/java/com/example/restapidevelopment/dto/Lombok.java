package com.example.restapidevelopment.dto;

import lombok.*;

import java.util.List;

@Builder
@Data
@ToString(exclude = { "sub" })
public class Lombok {
    private Integer id;
    private String name;
    private List<String> sub;
}
