package com.sec.bestreviewer.store;

import java.util.List;

public interface EmployeeStore {

    void add(Employee employee);

    List<Employee> search(ModelParams params);

    List<Employee> delete(ModelParams params);

    List<Employee> modify(ModelParams params);

    int count();
}
