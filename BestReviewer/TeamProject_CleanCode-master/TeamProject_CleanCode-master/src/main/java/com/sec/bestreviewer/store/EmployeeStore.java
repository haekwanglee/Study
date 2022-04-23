package com.sec.bestreviewer.store;

import java.util.List;
import java.util.function.Predicate;

public interface EmployeeStore {
    String FIELD_EMPLOYEE_NUMBER = "employeeNum";
    String FIELD_NAME = "name";
    String FIELD_CAREER_LEVEL = "cl";
    String FIELD_PHONE_NUMBER = "phoneNum";
    String FIELD_BIRTH_DAY = "birthday";
    String FIELD_CERTI = "certi";

    List<Employee> search(Predicate<Employee> filter);

    List<Employee> delete(List<Employee> searchedEmployeeList);

    void add(Employee employee);

    int count();

    List<Employee> modify(List<Employee> searchedEmployList, String modifyFieldName, String ModifyValue);
}
