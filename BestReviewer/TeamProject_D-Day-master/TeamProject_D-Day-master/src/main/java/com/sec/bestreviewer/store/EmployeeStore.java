package com.sec.bestreviewer.store;

import java.util.List;

public interface EmployeeStore {
    String FIELD_EMPLOYEE_NUMBER = "employeeNum";
    String FIELD_NAME = "name";
    String FIELD_CAREER_LEVEL = "cl";
    String FIELD_PHONE_NUMBER = "phoneNum";
    String FIELD_BIRTH_DAY = "birthday";
    String FIELD_CERTI = "certi";
    List<Employee> search(String fieldName, String value);

    List<Employee> search(String fieldName, String value, String compareOption);

    List<Employee> search(String fieldName, String value, String compareOption, String filterOption);

    List<Employee> delete(String fieldName, String value);

    void delete(List<Employee> list);

    List<Employee> modify(String changeThisField, String asThisValue, List<Employee> list);

    void add(Employee employee);

    int count();
}