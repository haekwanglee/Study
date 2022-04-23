package com.sec.bestreviewer.store.searchfilter;

import com.sec.bestreviewer.store.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class BirthdayYearFilter extends SearchFilterSearcher {

    public static final int YEAR_BEGIN_INDEX = 0;
    public static final int YEAR_END_INDEX = 4;

    @Override
    protected List<Employee> searchEquals(List<Employee> employees, String value) {
        return employees.stream()
                .filter(it -> it.getBirthday().substring(YEAR_BEGIN_INDEX, YEAR_END_INDEX).equals(value))
                .collect(Collectors.toList());
    }

    @Override
    public String getIndexKey(Employee employee) {
        return employee.getBirthday().substring(YEAR_BEGIN_INDEX, YEAR_END_INDEX);
    }
}
