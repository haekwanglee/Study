package com.sec.bestreviewer.store;

import com.sec.bestreviewer.store.filter.Filter;
import com.sec.bestreviewer.util.Pair;

import java.util.List;

public interface EmployeeStore {
    String FIELD_EMPLOYEE_NUMBER = "employeeNum";
    String FIELD_NAME = "name";
    String FIELD_CAREER_LEVEL = "cl";
    String FIELD_PHONE_NUMBER = "phoneNum";
    String FIELD_BIRTH_DAY = "birthday";
    String FIELD_CERTI = "certi";

    List<Employee> search(Filter filter);

    List<Employee> delete(Filter filter);

    List<Employee> modify(Filter filter, Pair<String, String> modifyPair);

    void add(Employee employee);

    int count();
}