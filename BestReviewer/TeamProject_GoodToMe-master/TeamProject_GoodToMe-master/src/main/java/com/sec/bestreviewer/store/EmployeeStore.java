package com.sec.bestreviewer.store;

import com.sec.bestreviewer.command.queryprocessor.QueryProcessor;
import com.sec.bestreviewer.util.Pair;

import java.util.List;

public interface EmployeeStore {
    String FIELD_EMPLOYEE_NUMBER = "employeeNumber";
    String FIELD_NAME = "name";
    String FIELD_CAREER_LEVEL = "careerLevel";
    String FIELD_PHONE_NUMBER = "phoneNumber";
    String FIELD_BIRTH_DAY = "birthDay";
    String FIELD_CERTIFICATION = "certi";

    List<Employee> search(QueryProcessor queryProcessor);

    List<Employee> delete(QueryProcessor queryProcessor);
    void add(Employee employee);
    int count();

    List<Employee> modify(QueryProcessor queryProcessor, Pair<String,String> newFieldValue);
}
