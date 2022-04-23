package com.sec.bestreviewer.util;

import com.sec.bestreviewer.store.Employee;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ResultStringFormatter {

    public static List<String> getEmployeeListToFormattedString(List<Employee> employeeList,
                                                                String commandType, int count) {
        if (employeeList == null || employeeList.size() == 0) {
            return Collections.singletonList(commandType + ",NONE");
        }
        return employeeList.stream()
                .sorted(Comparator.comparing(Employee::getEmployeeNumber))
                .limit(count)
                .map(employee -> getEmployeeToFormattedString(commandType, employee))
                .collect(Collectors.toList());
    }

    public static String getEmployeeToFormattedString(String commandType, Employee employee) {
        return  commandType + "," + employee.getEmployeeNumber() + "," +
                employee.getName() + "," +
                employee.getCareerLevel() + "," +
                employee.getPhoneNumber() + "," +
                employee.getBirthday() + "," +
                employee.getCerti();
    }

    public static List<String> getEmployeeListToFormattedString(List<Employee> employeeList, String commandType) {
        final String formattedString = employeeList.size() == 0 ? "NONE" : Integer.toString(employeeList.size());
        return Collections.singletonList(commandType + "," + formattedString);
    }
}