package com.sec.bestreviewer.store.searchfilter;

import com.sec.bestreviewer.store.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class BirthdayMonthFilter extends SearchFilterSearcher {

    public static final int MONTH_BEGIN_INDEX = 4;
    public static final int MONTH_END_INDEX = 6;

    @Override
    protected List<Employee> searchEquals(List<Employee> employees, String value) {
        return employees.stream()
                .filter(it -> it.getBirthday().substring(MONTH_BEGIN_INDEX, MONTH_END_INDEX).equals(value))
                .collect(Collectors.toList());
    }

    @Override
    public String getIndexKey(Employee employee) {
        return employee.getBirthday().substring(MONTH_BEGIN_INDEX, MONTH_END_INDEX);
    }
}
