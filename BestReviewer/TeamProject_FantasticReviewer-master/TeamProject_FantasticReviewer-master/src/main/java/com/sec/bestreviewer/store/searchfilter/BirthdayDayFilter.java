package com.sec.bestreviewer.store.searchfilter;

import com.sec.bestreviewer.store.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class BirthdayDayFilter extends SearchFilterSearcher {

    public static final int DAY_BEGIN_INDEX = 6;
    public static final int DAY_END_INDEX = 8;

    @Override
    protected List<Employee> searchEquals(List<Employee> employees, String value) {
        return employees.stream()
                .filter(it -> it.getBirthday().substring(DAY_BEGIN_INDEX, DAY_END_INDEX).equals(value))
                .collect(Collectors.toList());
    }

    @Override
    public String getIndexKey(Employee employee) {
        return employee.getBirthday().substring(DAY_BEGIN_INDEX, DAY_END_INDEX);
    }
}
