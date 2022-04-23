package com.sec.bestreviewer.store.searchfilter;

import com.sec.bestreviewer.store.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class FirstNameFilter extends SearchFilterSearcher {

    private static final int FIRST_NAME_INDEX = 0;

    protected List<Employee> searchEquals(List<Employee> employees, String value) {
        return employees.stream()
                .filter(it -> getFirstName(it.getName()).equals(value))
                .collect(Collectors.toList());
    }

    private String getFirstName(String name) {
        return name.substring(FIRST_NAME_INDEX, name.indexOf(REGEX_FIRST_LAST_NAME));
    }

    @Override
    public String getIndexKey(Employee employee) {
        return getFirstName(employee.getName());
    }
}

