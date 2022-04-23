package com.sec.bestreviewer.command;

import com.sec.bestreviewer.store.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UnionSet implements Set {

    @Override
    public List<Employee> execute(List<Employee> list1, List<Employee> list2) {
        List<Employee> resultList = list1.stream()
                .filter(employee -> !list2.contains(employee))
                .collect(Collectors.toList());
        // if we use java 11 or above , can try .filter(Predicate.not(secondList::contains

        resultList.addAll(list2);
        return resultList;
    }
}
