package com.sec.bestreviewer.store.searchfilter;

import com.sec.bestreviewer.store.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CertiFilter extends SearchFilterSearcher {

    private static final List<String> certies = Arrays.asList("ADV", "PRO", "EX");

    @Override
    protected List<Employee> searchEquals(List<Employee> employees, String value) {
        return employees.stream()
                .filter(it -> it.getCerti().equals(value))
                .collect(Collectors.toList());
    }

    @Override
    public String getIndexKey(Employee employee) {
        return employee.getCerti();
    }

    @Override
    public Comparator<String> getIndexKeyComparator() {
        return Comparator.comparingInt(certies::indexOf);
    }
}
