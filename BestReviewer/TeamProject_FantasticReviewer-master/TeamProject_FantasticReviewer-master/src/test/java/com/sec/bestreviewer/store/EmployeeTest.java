package com.sec.bestreviewer.store;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {
    @Test
    @DisplayName("Employee 생성 테스트")
    void testNewEmployee() {
        String employeeNumber = "01011010";
        String name = "TIGER WOODS";
        String careerLevel = "CL4";
        String phoneNumber = "010-9999-1111";
        String birthday = "20001010";
        String certi = "EX";

        Employee employee = new Employee(employeeNumber, name, careerLevel, phoneNumber, birthday, certi);

        assertEquals("01011010", employee.getEmployeeNumber());
        assertEquals("TIGER WOODS", employee.getName());
        assertEquals("CL4", employee.getCareerLevel());
        assertEquals("010-9999-1111", employee.getPhoneNumber());
        assertEquals("20001010", employee.getBirthday());
        assertEquals("EX", employee.getCerti());
    }

    @Test
    @DisplayName("Employee 생성자 카피 테스트")
    void testEmployeeCopy() {
        String employeeNumber = "01011010";
        String name = "TIGER WOODS";
        String careerLevel = "CL4";
        String phoneNumber = "010-9999-1111";
        String birthday = "20001010";
        String certi = "EX";

        Employee employee = new Employee(employeeNumber, name, careerLevel, phoneNumber, birthday, certi);
        Employee newEmployee = new Employee(employee);

        employee.setName("SERI PAK");
        employee.setCareerLevel("CL2");
        employee.setPhoneNumber("010-1111-9999");
        employee.setBirthday("20001111");
        employee.setCerti("PRO");

        assertEquals(employeeNumber, newEmployee.getEmployeeNumber());
        assertEquals(name, newEmployee.getName());
        assertEquals(careerLevel, newEmployee.getCareerLevel());
        assertEquals(phoneNumber, newEmployee.getPhoneNumber());
        assertEquals(birthday, newEmployee.getBirthday());
        assertEquals(certi, newEmployee.getCerti());
    }
}
