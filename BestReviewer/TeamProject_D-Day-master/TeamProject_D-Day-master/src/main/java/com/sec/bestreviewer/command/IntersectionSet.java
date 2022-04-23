package com.sec.bestreviewer.command;

import com.sec.bestreviewer.store.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class IntersectionSet implements Set {

    @Override
    public List<Employee> execute(List<Employee> list1, List<Employee> list2) {
        return  list1.stream()
                 .filter(list2::contains)
                   .collect(Collectors.toList());
    }
}
