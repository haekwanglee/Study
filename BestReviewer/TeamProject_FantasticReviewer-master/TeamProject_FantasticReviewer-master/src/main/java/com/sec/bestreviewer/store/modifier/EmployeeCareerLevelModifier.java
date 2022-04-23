package com.sec.bestreviewer.store.modifier;

import com.sec.bestreviewer.store.Employee;

public class EmployeeCareerLevelModifier implements EmployeeModifier {
    private final String careerLevel;

    public EmployeeCareerLevelModifier(String careerLevel) {
        this.careerLevel = careerLevel;
    }

    @Override
    public Employee modify(Employee employee) {
        employee.setCareerLevel(careerLevel);
        return employee;
    }

    public String getCareerLevel() {
        return careerLevel;
    }
}
