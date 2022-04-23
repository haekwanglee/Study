package com.sec.bestreviewer.store;

import com.sec.bestreviewer.command.queryprocessor.QueryProcessor;
import com.sec.bestreviewer.util.Pair;

import java.util.ArrayList;
import java.util.List;

class EmployeeStoreImpl implements EmployeeStore {
    private final List<Employee> employees = new ArrayList<>();

    @Override
    public List<Employee> modify(QueryProcessor queryProcessor, Pair<String, String> newFieldValue) {
        List<Employee> searchedEmployees = search(queryProcessor);
        employees.removeAll(searchedEmployees);
        addModifiedEmployees(searchedEmployees, newFieldValue);
        return searchedEmployees;
    }

    private void addModifiedEmployees(List<Employee> searchedEmployees, Pair<String, String> newFieldValue) {
        String newField = newFieldValue.first;
        String newValue = newFieldValue.second;
        for (Employee employee : searchedEmployees) {
            add(addModifiedEmployee(newField, newValue, employee));
        }
    }

    private Employee addModifiedEmployee(String targetColumn, String targetValue, Employee employee) {
        switch (targetColumn) {
            case FIELD_BIRTH_DAY:
                return new Employee(employee.getEmployeeNumber(), employee.getName(),
                        employee.getCareerLevel(), employee.getPhoneNumber(),
                        targetValue, employee.getCertification());
            case FIELD_CAREER_LEVEL:
                return new Employee(employee.getEmployeeNumber(), employee.getName(),
                        targetValue, employee.getPhoneNumber(),
                        employee.getBirthday(), employee.getCertification());
            case FIELD_CERTIFICATION:
                return new Employee(employee.getEmployeeNumber(), employee.getName(),
                        employee.getCareerLevel(), employee.getPhoneNumber(),
                        employee.getBirthday(), targetValue);
            case FIELD_NAME:
                return new Employee(employee.getEmployeeNumber(), targetValue,
                        employee.getCareerLevel(), employee.getPhoneNumber(),
                        employee.getBirthday(), employee.getCertification());
            case FIELD_PHONE_NUMBER:
                return new Employee(employee.getEmployeeNumber(), employee.getName(),
                        employee.getCareerLevel(), targetValue,
                        employee.getBirthday(), employee.getCertification());
            default:
                throw new RuntimeException("Unsupported field : " + targetColumn);
        }
    }

    @Override
    public List<Employee> search(QueryProcessor queryProcessor) {
        List<Employee> result = new ArrayList<>();
        for(Employee employee : employees) {
            if (queryProcessor.process(employee)) {
                result.add(employee);
            }
        }

        return result;
    }

    @Override
    public List<Employee> delete(QueryProcessor queryProcessor) {
        List<Employee> searchedEmployees = search(queryProcessor);
        employees.removeAll(searchedEmployees);
        return searchedEmployees;
    }

    @Override
    public void add(Employee employee) {
        if (!isDuplicatedEmployeeNumber(employee.getEmployeeNumber())) {
            employees.add(employee);
        }
    }

    private boolean isDuplicatedEmployeeNumber(String employeeNumber) {
        for (Employee employee : employees) {
            if (employee.getEmployeeNumber().equals(employeeNumber))
                return true;
        }
        return false;
    }

    @Override
    public int count() {
        return employees.size();
    }

}
