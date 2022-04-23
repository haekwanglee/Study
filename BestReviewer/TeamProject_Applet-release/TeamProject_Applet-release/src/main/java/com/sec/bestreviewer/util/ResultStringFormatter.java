package com.sec.bestreviewer.util;

import com.sec.bestreviewer.database.Row;
import com.sec.bestreviewer.database.field.EmployeeSchema;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ResultStringFormatter {
    private static final int YEAR_1990_EMPLOYEE_NUMBER = 90_000000;
    private static final int YEAR_PREFIX_19 = 1900_000000;
    private static final int YEAR_PREFIX_20 = 2000_000000;

    public static List<String> getEmployeeListToFormattedString(List<Row> employeeList) {
        return employeeList.stream()
                .sorted(Comparator.comparing(ResultStringFormatter::getEmployeeNumber))
                .map(ResultStringFormatter::getEmployeeToFormattedString)
                .collect(Collectors.toList());
    }

    private static Integer getEmployeeNumber(Row employee) {
        int employeeNumber = Integer.parseInt(employee.getValue(EmployeeSchema.getPrimaryKeyIndex()));
        return employeeNumber >= YEAR_1990_EMPLOYEE_NUMBER ?
                YEAR_PREFIX_19 + employeeNumber : YEAR_PREFIX_20 + employeeNumber;
    }

    public static String getEmployeeToFormattedString(Row employee) {
        return employee.getValue(EmployeeSchema.EMPLOYEE_NUMBER.getIndex()) + "," +
                employee.getValue(EmployeeSchema.NAME.getIndex()) + "," +
                employee.getValue(EmployeeSchema.CAREER_LEVEL.getIndex()) + "," +
                employee.getValue(EmployeeSchema.PHONE_NUMBER.getIndex()) + "," +
                employee.getValue(EmployeeSchema.BIRTHDAY.getIndex()) + "," +
                employee.getValue(EmployeeSchema.CERTI.getIndex());

    }
}