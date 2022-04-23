package com.sec.bestreviewer.store;

import java.util.Objects;

public class Employee {
    private String employeeNumber;
    private Name name;
    private String careerLevel;
    private PhoneNumber phoneNumber;
    private Birthday birthday;
    private Certi certi;

    public Employee(String employeeNumber,
                    String name,
                    String careerLevel,
                    String phoneNumber,
                    String birthday,
                    String certi) {
        this.employeeNumber = employeeNumber;
        this.name = new Name(name);
        this.careerLevel = careerLevel;
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.birthday = new Birthday(birthday);
        this.certi = Certi.valueOf(certi);
    }

    public Employee(Employee employee) {
        this.employeeNumber = employee.getEmployeeNumber();
        this.name = new Name(employee.getNameObject());
        this.careerLevel = employee.getCareerLevel();
        this.phoneNumber = new PhoneNumber(employee.getPhoneNumber());
        this.birthday = new Birthday(employee.getBirthday());
        this.certi = Certi.valueOf(employee.getCerti());
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public Name getNameObject() {
        return name;
    }

    public String getName() {
        return name.getFullName();
    }

    public String getFirstName() {
        return name.getFirstName();
    }

    public String getLastName() {
        return name.getLastName();
    }

    public String getCareerLevel() {
        return careerLevel;
    }

    public String getPhoneNumber() {
        return phoneNumber.getFullNumber();
    }

    public String getPhoneMiddleNumber() {
        return phoneNumber.getMiddleNumber();
    }

    public String getPhoneLastNumber() {
        return phoneNumber.getLastNumber();
    }

    public int getPhoneMiddleNumberDigit() {
        return phoneNumber.getMiddleNumberDigit();
    }

    public int getPhoneLastNumberDigit() {
        return phoneNumber.getLastNumberDigit();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = new PhoneNumber(phoneNumber);
    }

    public String getBirthday() {
        return birthday.getYearMonthDay();
    }

    public String getBirthdayYear() {
        return birthday.getYear();
    }

    public String getBirthdayMonth() {
        return birthday.getMonth();
    }

    public String getBirthdayDay() {
        return birthday.getDay();
    }

    public int getBirthdayDigit() {
        return birthday.getYearMonthDayDigit();
    }

    public int getBirthdayYearDigit() {
        return birthday.getYearDigit();
    }

    public int getBirthdayMonthDigit() {
        return birthday.getMonthDigit();
    }

    public int getBirthdayDayDigit() {
        return birthday.getDayDigit();
    }

    public void setBirthday(String birthday) {
        this.birthday = new Birthday(birthday);
    }

    public void setName(String name) {
        this.name = new Name(name);
    }

    public void setCareerLevel(String careerLevel) {
        this.careerLevel = careerLevel;
    }

    public String getCerti() {
        return certi.name();
    }

    public void setCerti(String certi) {
        this.certi = Certi.valueOf(certi);
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
