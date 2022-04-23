package com.sec.bestreviewer.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class EmployeeStoreImpl implements EmployeeStore {
    private final List<Employee> employees = new ArrayList<>();

    @Override
    public List<Employee> search(String fieldName, String value) {
        switch (fieldName) {
            case EmployeeStore.FIELD_EMPLOYEE_NUMBER:
                return employees.stream()
                        .filter(employee -> employee.getEmployeeNumber().equals(value))
                        .collect(Collectors.toList());
            case EmployeeStore.FIELD_NAME:
                return employees.stream()
                        .filter(employee -> employee.getName().equals(value))
                        .collect(Collectors.toList());
            case EmployeeStore.FIELD_CAREER_LEVEL:
                return employees.stream()
                        .filter(employee -> employee.getCareerLevel().equals(value))
                        .collect(Collectors.toList());
            case EmployeeStore.FIELD_PHONE_NUMBER:
                return employees.stream()
                        .filter(employee -> employee.getPhoneNumber().equals(value))
                        .collect(Collectors.toList());
            case EmployeeStore.FIELD_BIRTH_DAY:
                return employees.stream()
                        .filter(employee -> employee.getBirthday().equals(value))
                        .collect(Collectors.toList());
            default:
                return Collections.emptyList();
        }
    }

    @Override
    public List<Employee> delete(String fieldName, String value) {
        List<Employee> searchedEmployees = search(fieldName, value);
        employees.removeAll(searchedEmployees);
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
