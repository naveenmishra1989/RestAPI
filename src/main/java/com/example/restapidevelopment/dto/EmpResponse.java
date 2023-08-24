package com.example.restapidevelopment.dto;

import com.example.restapidevelopment.entity.Emp;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Delegate;

@Builder
@Setter
@RequiredArgsConstructor
public class EmpResponse {

    @JsonIgnore
    @Delegate
    public final Emp emp;
}
