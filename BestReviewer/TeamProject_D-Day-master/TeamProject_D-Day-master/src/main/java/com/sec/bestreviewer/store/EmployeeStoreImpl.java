package com.sec.bestreviewer.store;

import com.sec.bestreviewer.util.Options;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

class EmployeeStoreImpl implements EmployeeStore {
    private final List<Employee> employees = new ArrayList<>();
    private final HashMap<String, String> employeeNumbers = new HashMap<>();

    @Override
    public List<Employee> search(String fieldName, String value) {
        return search(fieldName, value, "-eq");
    }

    @Override
    public List<Employee> search(String fieldName, String value, String compareOption) {
        return search(fieldName, value, compareOption, "");
    }

    @Override
    public List<Employee> search(String fieldName, String value, String compareOption, String filterOption) {
        return employees.stream().filter(employee -> satisfyCondition(employee, fieldName, value, compareOption, filterOption)).collect(Collectors.toList());
    }

    private boolean satisfyCondition(Employee employee, String fieldName, String value, String compareOption, String filterOption) {
        Field field = employee.getField(fieldName);
        switch (compareOption) {
            case "-g":
                return field.toCompareTo(value, filterOption) > 0;
            case "-ge":
                return field.toCompareTo(value, filterOption) >= 0;
            case "-s":
                return field.toCompareTo(value, filterOption) < 0;
            case "-se":
                return field.toCompareTo(value, filterOption) <= 0;
            case "-eq":
            case Options.EMPTY_OPTION:
                return field.toCompareTo(value, filterOption) == 0;
        }
        return false;
    }


    @Override
    public List<Employee> delete(String fieldName, String value) {
        //TODO 성능 최적화 필요, 10000 이상넘어갈 경우, 속도 저하
        List<Employee> searchedEmployees = search(fieldName, value);
        delete(searchedEmployees);
        return searchedEmployees;
    }

    @Override
    public void delete(List<Employee> list) {
        employees.removeAll(list);
        for (Employee employee : list) {
            employeeNumbers.remove(employee.getEmployeeNumber());
        }
    }

    @Override
    public void add(Employee employee) {
        if (isAlreadyAdded(employee)) return;
        employees.add(employee);
        employeeNumbers.put(employee.getEmployeeNumber(), employee.getEmployeeNumber());
    }

    private boolean isAlreadyAdded(Employee employee) {
        return employeeNumbers.containsKey(employee.getEmployeeNumber());
    }

    @Override
    public List<Employee> modify(String ChangeThisField, String asThisValue, List<Employee> list) {
        for (Employee employee : list) {
            update(employee, ChangeThisField, asThisValue);
        }

        return list;
    }

    private void update(Employee employee, String fieldName, String value) {
        switch (fieldName) {
            case EmployeeStore.FIELD_NAME:
                employee.setName(value);
                break;
            case EmployeeStore.FIELD_BIRTH_DAY:
                employee.setBirthday(value);
                break;
            case EmployeeStore.FIELD_CAREER_LEVEL:
                employee.setCareerLevel(value);
                break;
            case EmployeeStore.FIELD_PHONE_NUMBER:
                employee.setPhoneNumber(value);
                break;
            case EmployeeStore.FIELD_CERTI:
                employee.setCerti(value);
                break;
        }
        delete(EmployeeStore.FIELD_EMPLOYEE_NUMBER, employee.getEmployeeNumber());
        add(employee);
    }

    @Override
    public int count() {
        return employees.size();
    }
}