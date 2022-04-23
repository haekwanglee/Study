package com.sec.bestreviewer.store;

import java.util.List;

public interface EmployeeStore {
    String FIELD_EMPLOYEE_NUMBER = "employeeNumber";
    String FIELD_NAME = "name";
    String FIELD_CAREER_LEVEL = "careerLevel";
    String FIELD_PHONE_NUMBER = "phoneNumber";
    String FIELD_BIRTH_DAY = "birthDay";

    List<Employee> search(String fieldName, String value);
    List<Employee> delete(String fieldName, String value);
    void add(Employee employee);
    int count();
}
