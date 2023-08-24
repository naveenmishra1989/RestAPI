package com.example.restapidevelopment.list;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ListNullCheck {

    private boolean isNullOrEmpty(Collection<String> collection) {
        return collection == null || collection.isEmpty() ? Boolean.TRUE : Boolean.FALSE;
    }

    private boolean isNotNullAndEmpty(Collection<String> collection) {
        return collection == null || collection.isEmpty() ? Boolean.FALSE : Boolean.TRUE;
    }

    @Test
    void testE() {
        List<String> strings = getList();
        System.out.println(isNullOrEmpty(strings));
        System.out.println(isNotNullAndEmpty(strings));

    }

    private List<String> getList() {
        List<String> strings = new LinkedList<>();
        strings.add("naveen");
        return strings;
    }
}
