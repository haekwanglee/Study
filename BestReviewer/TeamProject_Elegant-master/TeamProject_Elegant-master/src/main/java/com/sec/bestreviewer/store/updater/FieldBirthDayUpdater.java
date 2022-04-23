package com.sec.bestreviewer.store.updater;

import com.sec.bestreviewer.store.Employee;

import java.util.List;

public class FieldBirthDayUpdater extends FieldUpdater {

    public FieldBirthDayUpdater(List<Employee> employees) {
        super(employees);
    }

    @Override
    public List<Employee> update(String value) {
        employees.forEach(employee -> employee.setBirthday(value));
        return employees;
    }
}
