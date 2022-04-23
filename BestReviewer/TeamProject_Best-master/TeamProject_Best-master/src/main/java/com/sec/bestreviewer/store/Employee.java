package com.sec.bestreviewer.store;

import java.util.Objects;

public class Employee {

    private String employeeNumber;
    private String careerLevel;
    private String certi;
    private final ProfileName profileName;
    private final ProfileBirthday profileBirthday;
    private final ProfilePhoneNumber profilePhoneNumber;

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
        this.employeeNumber = employeeNumber;
        this.careerLevel = careerLevel;
        this.certi = certi;
        this.profileName = new ProfileName(name);
        this.profileBirthday = new ProfileBirthday(birthday);
        this.profilePhoneNumber = new ProfilePhoneNumber(phoneNumber);
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getName() {
        return profileName.getFullName();
    }

    public void setName(String name) {
        this.profileName.setName(name);
    }

    public String getCareerLevel() {
        return careerLevel;
    }

    public void setCareerLevel(String careerLevel) {
        this.careerLevel = careerLevel;
    }

    public String getPhoneNumber() {
        return profilePhoneNumber.getPhoneNumber();
    }

    public void setPhoneNumber(String phoneNumber) {
        profilePhoneNumber.setPhoneNumber(phoneNumber);
    }

    public String getBirthday() {
        return profileBirthday.getBirthday();
    }

    public void setBirthday(String birthday) {
        profileBirthday.setBirthday(birthday);
    }

    public String getCerti() {
        return certi;
    }

    public void setCerti(String certi) {
        this.certi = certi;
    }

    public ProfileName getProfileName() {
        return profileName;
    }

    public ProfileBirthday getProfileBirthday() {
        return profileBirthday;
    }

    public ProfilePhoneNumber getProfilePhoneNumber() {
        return profilePhoneNumber;
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
