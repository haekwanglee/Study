package com.sec.bestreviewer.store;

import java.util.List;

public interface EmployeeStore {
    String FIELD_EMPLOYEE_NUMBER = "employeeNumber";
    String FIELD_NAME = "name";
    String FIELD_CAREER_LEVEL = "cl";
    String FIELD_PHONE_NUMBER = "phoneNum";
    String FIELD_BIRTH_DAY = "birthday";
    String FIELD_CERTI = "certi";


    List<Employee> search(ConditionParam param);
    List<Employee> searchAnd(ConditionParam param1, ConditionParam param2);
    List<Employee> searchOr(ConditionParam param1, ConditionParam param2);

    void delete(List<Employee> employeesToDelete);

    void modify(List<Employee> employeesToModify, String newField, String newValue);

    void add(Employee employee);
    int count();
}
