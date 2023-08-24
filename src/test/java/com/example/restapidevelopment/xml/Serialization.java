package com.example.restapidevelopment.xml;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Serialization {

    @Test
    void testSerialization() throws IOException {
        SimpleBean simpleBean = SimpleBean.builder()
                .x(234)
                .y(789)
                .build();

        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(simpleBean);
        assertNotNull(xml);
        System.out.println(xml);

        //xmlMapper.writeValue(new File("simple_bean.xml"), simpleBean);

        SimpleBean simpleBean1 = xmlMapper.readValue(xml, SimpleBean.class);
        System.out.println(simpleBean1);


    }
}
