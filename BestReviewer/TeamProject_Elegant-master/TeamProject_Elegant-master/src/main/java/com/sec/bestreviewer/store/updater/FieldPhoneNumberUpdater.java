package com.sec.bestreviewer.store.updater;

import com.sec.bestreviewer.store.Employee;

import java.util.List;

public class FieldPhoneNumberUpdater extends FieldUpdater {

    public FieldPhoneNumberUpdater(List<Employee> employees) {
        super(employees);
    }

    @Override
    public List<Employee> update(String value) {
        employees.forEach(employee -> employee.setPhoneNumber(value));
        return employees;
    }
}
