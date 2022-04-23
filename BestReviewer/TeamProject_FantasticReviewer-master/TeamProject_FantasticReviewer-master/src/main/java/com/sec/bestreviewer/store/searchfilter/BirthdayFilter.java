package com.sec.bestreviewer.store.searchfilter;

import com.sec.bestreviewer.store.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class BirthdayFilter extends SearchFilterSearcher {
    @Override
    protected List<Employee> searchEquals(List<Employee> employees, String value) {
        return employees.stream()
                .filter(it -> it.getBirthday().equals(value))
                .collect(Collectors.toList());
    }

    @Override
    public String getIndexKey(Employee employee) {
        return employee.getBirthday();
    }
}
