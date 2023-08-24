package com.example.restapidevelopment.controller;

import com.example.restapidevelopment.dto.SomeBean;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value="Field Management System", description="Operations pertaining to fields in Field Management System")
@RestController
@RequestMapping("/filter")
public class DynamicFilteringController {

    //localhost:8384/filter/v1/dynamic
    @GetMapping(value = {"/v1/dynamic"})
    @ApiOperation(value = "View a list of available Field1,Field2 ", response = MappingJacksonValue.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public MappingJacksonValue getSomeBeanDynamic1(){
        SomeBean someBean = new SomeBean("Value1","Value2","Value3","Value4");
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
        FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue (someBean);
        mapping.setFilters(filters);
        return mapping;
    }


    @GetMapping(value = {"/dynamic/param"},params = "version=1")
    public MappingJacksonValue getSomeBeanParam1(){
        SomeBean someBean = new SomeBean("Value1","Value2","Value3","Value4");
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
        FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue (someBean);
        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping(value = {"/dynamic/header"},headers = "X-API-VERSION=1")
    public MappingJacksonValue getSomeBeanHeader1(){
        SomeBean someBean = new SomeBean("Value1","Value2","Value3","Value4");
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
        FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue (someBean);
        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping(value = {"/dynamic/produce"},produces = "application/vnd.company.app-v1+json")
    public MappingJacksonValue getSomeBeanProduce1(){
        SomeBean someBean = new SomeBean("Value1","Value2","Value3","Value4");
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
        FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue (someBean);
        mapping.setFilters(filters);
        return mapping;
    }


    @GetMapping(value = {"/v2/dynamic"})
            @ApiOperation(value = "View a list of available Field3,Field4 ", response = MappingJacksonValue.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public MappingJacksonValue getSomeBeanDynamic2(){
        SomeBean someBean = new SomeBean("Value1","Value2","Value3","Value4");
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field3","field4");
        FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue (someBean);
        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping(value = {"/dynamic/param"},params = "version=2")
    public MappingJacksonValue getSomeBeanParam2(){
        SomeBean someBean = new SomeBean("Value1","Value2","Value3","Value4");
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field3","field4");
        FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue (someBean);
        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping(value = {"/dynamic/header"},headers = "X-API-VERSION=2")
    public MappingJacksonValue getSomeBeanHeader2(){
        SomeBean someBean = new SomeBean("Value1","Value2","Value3","Value4");
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field3","field4");
        FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue (someBean);
        mapping.setFilters(filters);
        return mapping;
    }
    @GetMapping(value = {"/dynamic/produce"},produces = "application/vnd.company.app-v2+json")
    public MappingJacksonValue getSomeBeanProduce2(){
        SomeBean someBean = new SomeBean("Value1","Value2","Value3","Value4");
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field3","field4");
        FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue (someBean);
        mapping.setFilters(filters);
        return mapping;
    }
}
