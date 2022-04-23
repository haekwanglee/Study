package com.sec.bestreviewer.store;

import java.util.Objects;

public class Employee {
    private final String employeeNumber;
    private String name;
    private String careerLevel;
    private String phoneNumber;
    private String birthday;
    private String certi;

    private final String employeeNumberDateFormatYyyy;

    public Employee(String employeeNumber,
                    String name,
                    String careerLevel,
                    String phoneNumber,
                    String birthday,
                    String certi) {
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.careerLevel = careerLevel;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.certi = certi;

        this.employeeNumberDateFormatYyyy = appendDateFormatYy(employeeNumber);
    }

    public Employee(Employee employee) {
        this(employee.employeeNumber,
                employee.name,
                employee.careerLevel,
                employee.phoneNumber,
                employee.birthday,
                employee.certi);
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getEmployeeNumberDateFormatYyyy() {
        return employeeNumberDateFormatYyyy;
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

    public String getCerti() {
        return certi;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCareerLevel(String careerLevel) {
        this.careerLevel = careerLevel;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setCerti(String certi) {
        this.certi = certi;
    }

    public static String appendDateFormatYy(String employeeNumber) {
        if (employeeNumber.startsWith("9")) {
            return "19" + employeeNumber;
        }
        return "20" + employeeNumber;
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

    public static class Builder {

        private String employeeNumber;
        private String name;
        private String careerLevel;
        private String phoneNumber;
        private String birthday;
        private String certi;

        public Builder setEmployeeNumber(String employeeNumber) {
            this.employeeNumber = employeeNumber;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setCareerLevel(String careerLevel) {
            this.careerLevel = careerLevel;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setBirthday(String birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder setCerti(String certi) {
            this.certi = certi;
            return this;
        }

        public Employee build() {
            Objects.requireNonNull(employeeNumber);
            Objects.requireNonNull(name);
            Objects.requireNonNull(careerLevel);
            Objects.requireNonNull(phoneNumber);
            Objects.requireNonNull(birthday);

            return new Employee(
                    employeeNumber,
                    name,
                    careerLevel,
                    phoneNumber,
                    birthday,
                    certi
            );
        }
    }
}
