package com.sec.bestreviewer.command.queryprocessor;

import com.sec.bestreviewer.store.Employee;

public class CareerLevelOptionHandler implements FieldOptionHandler {
    @Override
    public String process(String option, Employee employee) {
        return employee.getCareerLevel();
    }
}
