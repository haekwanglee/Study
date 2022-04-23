package com.sec.bestreviewer.store;

import java.util.Objects;

public class Employee {
    private String employeeNumber;
    private String name;
    private String careerLevel;
    private String phoneNumber;
    private String birthday;
    private String certi;

    // Attributes(Fields) for fast search
    private int employeeNumberValue;
    private int dayOfBirthday;
    private int monthOfBirthday;
    private int yearOfBirthday;
    private String firstName;
    private String lastName;
    private int middlePhoneNumber;
    private int lastPhoneNumber;


    public Employee(String employeeNumber,
                    String name,
                    String careerLevel,
                    String phoneNumber,
                    String birthday,
                    String certi) {
        setEmployeeNumber(employeeNumber);
        setName(name);
        setCareerLevel(careerLevel);
        setPhoneNumber(phoneNumber);
        setBirthday(birthday);
        setCerti(certi);
    }

    public Employee(Employee employee) {
        setEmployeeNumber(employee.getEmployeeNumber());
        setName(employee.getName());
        setCareerLevel(employee.getCareerLevel());
        setPhoneNumber(employee.getPhoneNumber());
        setBirthday(employee.getBirthday());
        setCerti(employee.getCerti());
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }
    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
        setEmployeeNumberValue(employeeNumber);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        setFirstName(name.split(" ")[0]);
        setLastName(name.split(" ")[1]);
    }

    public String getCareerLevel() {
        return careerLevel;
    }
    public void setCareerLevel(String careerLevel) {
        this.careerLevel = careerLevel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        setMiddlePhoneNumber(phoneNumber.split("-")[1]);
        setLastPhoneNumber(phoneNumber.split("-")[2]);
    }

    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
        setYearOfBirthday(birthday.substring(0, 4));
        setMonthOfBirthday(birthday.substring(4, 6));
        setDayOfBirthday(birthday.substring(6, 8));
    }

    public String getCerti() {
        return certi;
    }
    public void setCerti(String certi) {
        this.certi = certi;
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

    public int getEmployeeNumberValue() {
        return employeeNumberValue;
    }

    private int toEmployeeNumber(String employeeNumber){
        String year = employeeNumber.substring(0, 2);
        if (year.compareTo("90") < 0) {
            return 2000_000000 + Integer.parseInt(employeeNumber);
        } else {
            return 1900_000000 + Integer.parseInt(employeeNumber);
        }
    }
    public int  compareEmployeeNumberValue(String employeeNumber){
        return (employeeNumberValue - toEmployeeNumber(employeeNumber));
    }
    public void setEmployeeNumberValue(String employeeNumber) {
        this.employeeNumberValue = toEmployeeNumber(employeeNumber);
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMiddlePhoneNumber() {
        return middlePhoneNumber;
    }

    public void setMiddlePhoneNumber(String middlePhoneNumber) {
        this.middlePhoneNumber = Integer.parseInt(middlePhoneNumber);
    }

    public int getLastPhoneNumber() {
        return lastPhoneNumber;
    }

    public void setLastPhoneNumber(String lastPhoneNumber) {
        this.lastPhoneNumber = Integer.parseInt(lastPhoneNumber);
    }

    public int getDayOfBirthday() {
        return dayOfBirthday;
    }

    public void setDayOfBirthday(String dayOfBirthday) {
        this.dayOfBirthday = Integer.parseInt(dayOfBirthday);
    }

    public int getMonthOfBirthday() {
        return monthOfBirthday;
    }

    public void setMonthOfBirthday(String monthOfBirthday) {
        this.monthOfBirthday = Integer.parseInt(monthOfBirthday);
    }

    public int getYearOfBirthday() {
        return yearOfBirthday;
    }

    public void setYearOfBirthday(String yearOfBirthday) {
        this.yearOfBirthday = Integer.parseInt(yearOfBirthday);
    }


}
