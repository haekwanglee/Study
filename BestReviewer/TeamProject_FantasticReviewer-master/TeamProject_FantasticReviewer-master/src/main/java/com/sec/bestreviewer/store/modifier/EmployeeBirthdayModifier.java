package com.sec.bestreviewer.store.modifier;

import com.sec.bestreviewer.store.Employee;

public class EmployeeBirthdayModifier implements EmployeeModifier {
    private final String birthday;

    public EmployeeBirthdayModifier(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public Employee modify(Employee employee) {
        employee.setBirthday(birthday);
        return employee;
    }

    public String getBirthday() {
        return birthday;
    }
}
