package com.sec.bestreviewer.store;

import com.sec.bestreviewer.base.ConditionParameter;

import java.util.List;

public interface EmployeeStore {
    void add(Employee employee);

    int count();

    List<Employee> search(ConditionParameter parameter);
    List<Employee> delete(ConditionParameter parameter);
    List<Employee> modify(ConditionParameter parameter);
}
