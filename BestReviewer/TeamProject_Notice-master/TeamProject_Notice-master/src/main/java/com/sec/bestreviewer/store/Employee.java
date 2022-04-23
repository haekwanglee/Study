package com.sec.bestreviewer.store;

import java.util.Objects;

public class Employee {
    private String employeeNumber;
    private String name;
    private String careerLevel;
    private String phoneNumber;
    private String birthday;

    public Employee(String employeeNumber,
                    String name,
                    String careerLevel,
                    String phoneNumber,
                    String birthday) {
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.careerLevel = careerLevel;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getName() {
        return name;
    }

    public String getCareerLevel() {
        return careerLevel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeNumber, employee.employeeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNumber);
    }
}
