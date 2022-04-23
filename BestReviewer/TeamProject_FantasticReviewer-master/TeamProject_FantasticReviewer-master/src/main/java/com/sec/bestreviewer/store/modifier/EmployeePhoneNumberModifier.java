package com.sec.bestreviewer.store.modifier;

import com.sec.bestreviewer.store.Employee;

public class EmployeePhoneNumberModifier implements EmployeeModifier {
    private final String phoneNumber;
    public EmployeePhoneNumberModifier(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public Employee modify(Employee employee) {
        employee.setPhoneNumber(phoneNumber);
        return employee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}