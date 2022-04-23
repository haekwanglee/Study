package com.sec.bestreviewer.store.searchfilter;

import com.sec.bestreviewer.store.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeNumberFilter extends SearchFilterSearcher {

    @Override
    protected List<Employee> searchEquals(List<Employee> employees, String value) {
        return employees.stream()
                .filter(it -> it.getEmployeeNumber().equals(value))
                .collect(Collectors.toList());
    }

    @Override
    public String getIndexKey(Employee employee) {
        return employee.getEmployeeNumber();
    }

    @Override
    public Comparator<String> getIndexKeyComparator() {
        return Comparator.comparing(Employee::appendDateFormatYy);
    }
}
