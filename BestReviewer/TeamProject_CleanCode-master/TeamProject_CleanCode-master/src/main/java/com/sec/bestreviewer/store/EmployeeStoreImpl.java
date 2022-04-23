package com.sec.bestreviewer.store;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class EmployeeStoreImpl implements EmployeeStore {
    private final List<Employee> employees = new ArrayList<>();

    @Override
    public List<Employee> search(Predicate<Employee> filter) {
        return employees.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> delete(List<Employee> searchedEmployeeList) {
        employees.removeAll(searchedEmployeeList);
        return searchedEmployeeList;
    }

    @Override
    public void add(Employee employee) {
        employees.add(employee);
    }

    @Override
    public int count() {
        return employees.size();
    }

    @Override
    public List<Employee> modify(List<Employee> searchedEmployees, String modifyFieldName, String modifyValue) {
        List<Employee> searchedEmployeeClone = new ArrayList<>();
        for (Employee employee : searchedEmployees) {
            searchedEmployeeClone.add(new Employee(employee));
        }

        switch (modifyFieldName) {
            case EmployeeStore.FIELD_NAME:
                searchedEmployees.forEach(employee -> employee.setName(modifyValue));
                break;
            case EmployeeStore.FIELD_CAREER_LEVEL:
                searchedEmployees.forEach(employee -> employee.setCareerLevel(modifyValue));
                break;
            case EmployeeStore.FIELD_PHONE_NUMBER:
                searchedEmployees.forEach(employee -> employee.setPhoneNumber(modifyValue));
                break;
            case EmployeeStore.FIELD_BIRTH_DAY:
                searchedEmployees.forEach(employee -> employee.setBirthday(modifyValue));
                break;
            case EmployeeStore.FIELD_CERTI:
                searchedEmployees.forEach(employee -> employee.setCerti(modifyValue));
                break;
            default:
                throw new IllegalArgumentException("Wrong modifyFieldName: " + modifyFieldName);
        }

        return searchedEmployeeClone;
    }
}
