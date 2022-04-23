package com.sec.bestreviewer.store;

import com.sec.bestreviewer.store.filter.Filter;
import com.sec.bestreviewer.store.filter.FilterType;
import com.sec.bestreviewer.util.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

class EmployeeStoreImpl implements EmployeeStore {
    private final HashSet<Employee> employees = new HashSet<>();

    @Override
    public List<Employee> search(Filter filter) {
        return employees.stream().parallel().filter(filter::predicate).collect(Collectors.toList());
    }

    public List<Employee> delete(Filter filter) {
        List<Employee> searchedEmployees = search(filter);
        searchedEmployees.forEach(employees::remove);
        return searchedEmployees;
    }

    @Override
    public List<Employee> modify(Filter filter, Pair<String, String> modifyPair) {
        List<Employee> searchedEmployees = search(filter);
        List<Employee> newEmployees = searchedEmployees.stream().parallel()
                .map(employee -> FilterType.forFieldName(modifyPair.fieldName).createFilter().modify(employee, modifyPair.value))
                .collect(Collectors.toList());
        searchedEmployees.forEach(employees::remove);
        employees.addAll(newEmployees);
        return searchedEmployees;
    }

    @Override
    public void add(Employee employee) {
        employees.add(employee);
    }

    @Override
    public int count() {
        return employees.size();
    }
}