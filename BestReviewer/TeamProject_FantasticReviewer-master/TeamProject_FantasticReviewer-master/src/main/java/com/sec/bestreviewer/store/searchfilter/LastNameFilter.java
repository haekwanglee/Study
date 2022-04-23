package com.sec.bestreviewer.store.searchfilter;

import com.sec.bestreviewer.store.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class LastNameFilter extends SearchFilterSearcher {

    private static final int LAST_NAME_INDEX = 1;

    protected List<Employee> searchEquals(List<Employee> employees, String value) {
        return employees.stream()
                .filter(it -> getLastName(it.getName()).equals(value))
                .collect(Collectors.toList());
    }

    private String getLastName(String name) {
        return name.substring(name.indexOf(REGEX_FIRST_LAST_NAME) + LAST_NAME_INDEX);
    }

    @Override
    public String getIndexKey(Employee employee) {
        return getLastName(employee.getName());
    }
}