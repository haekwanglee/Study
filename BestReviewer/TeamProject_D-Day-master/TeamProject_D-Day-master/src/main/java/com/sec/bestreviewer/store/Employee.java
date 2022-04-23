package com.sec.bestreviewer.store;

import java.util.Objects;

public class Employee {
    private Field employeeNumber;
    private Field name;
    private Field careerLevel;
    private Field phoneNumber;
    private Field birthday;
    private Field certi;

    public Employee(Employee employee) {
        this(employee.getEmployeeNumber(), employee.getName(), employee.getCareerLevel(),
                employee.getPhoneNumber(), employee.getBirthday(), employee.getCerti());
    }

    public Employee(String employeeNumber,
                    String name,
                    String careerLevel,
                    String phoneNumber,
                    String birthday,
                    String certi) {
        this.employeeNumber = new EmployeeNumberField(employeeNumber);
        this.name = new EmployeeNameField(name);
        this.careerLevel = new Field(careerLevel);
        this.phoneNumber = new EmployeePhoneNumberField(phoneNumber);
        this.birthday = new BirthdayField(birthday);
        this.certi = new CertiField(certi);
    }

    public String getEmployeeNumber() {
        return employeeNumber.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public String getCareerLevel() {
        return careerLevel.getValue();
    }

    public String getPhoneNumber() {
        return phoneNumber.getValue();
    }

    public String getBirthday() {
        return birthday.getValue();
    }

    public void setName(String name) {
        this.name.setField(name);
    }

    public void setCareerLevel(String careerLevel) {
        this.careerLevel.setField(careerLevel);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.setField(phoneNumber);
    }

    public void setBirthday(String birthday) {
        this.birthday.setField(birthday);
    }

    public String getCerti() {
        return certi.getValue();
    }

    public void setCerti(String certi) {
        this.certi.setField(certi);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeNumber.getValue(), employee.getEmployeeNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNumber);
    }

    public Field getField(String fieldName) {
        switch (fieldName) {
            case EmployeeStore.FIELD_EMPLOYEE_NUMBER:
                return employeeNumber;
            case EmployeeStore.FIELD_NAME:
                return name;
            case EmployeeStore.FIELD_CAREER_LEVEL:
                return careerLevel;
            case EmployeeStore.FIELD_PHONE_NUMBER:
                return phoneNumber;
            case EmployeeStore.FIELD_BIRTH_DAY:
                return birthday;
            case EmployeeStore.FIELD_CERTI:
                return certi;
            default:
                return new Field("");
        }
    }
}