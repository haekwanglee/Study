package com.sec.bestreviewer.store;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    private final String EMPLOYEE_NUMBER = "12212418";
    private final String FIRST_NAME = "SANGWOO";
    private final String LAST_NAME = "LEE";
    private final String NAME = FIRST_NAME + " " + LAST_NAME;
    private final String CAREER_LEVEL = "CL3";
    private final String PHONE_MIDDLE_NUMBER = "3485";
    private final String PHONE_LAST_NUMBER = "6916";
    private final String PHONE_NUMBER = "010-" + PHONE_MIDDLE_NUMBER + "-" + PHONE_LAST_NUMBER;
    private final String BIRTHDAY_YEAR = "1987";
    private final String BIRTHDAY_MONTH = "03";
    private final String BIRTHDAY_DAY = "08";
    private final String BIRTHDAY = BIRTHDAY_YEAR + BIRTHDAY_MONTH + BIRTHDAY_DAY;
    private final String CERTI = "PRO";
    private final Employee employee = new Employee(
            EMPLOYEE_NUMBER, NAME, CAREER_LEVEL, PHONE_NUMBER, BIRTHDAY, CERTI);
    private final Employee employeeWithSameEmployeeNumber = new Employee(
            EMPLOYEE_NUMBER, "SAMSUNG KIM", "CL4", "010-1234-5678", "20211207", CERTI);
    private final Employee employeeWithDifferentEmployeeNumber = new Employee(
            "21212418", NAME, CAREER_LEVEL, PHONE_NUMBER, BIRTHDAY, CERTI);

    @Test
    void testGetMethods() {
        assertEquals(EMPLOYEE_NUMBER, employee.getEmployeeNumber());
        assertEquals(NAME, employee.getName());
        assertEquals(FIRST_NAME, employee.getFirstName());
        assertEquals(LAST_NAME, employee.getLastName());
        assertEquals(CAREER_LEVEL, employee.getCareerLevel());
        assertEquals(PHONE_NUMBER, employee.getPhoneNumber());
        assertEquals(PHONE_MIDDLE_NUMBER, employee.getPhoneMiddleNumber());
        assertEquals(PHONE_LAST_NUMBER, employee.getPhoneLastNumber());
        assertEquals(Integer.parseInt(PHONE_MIDDLE_NUMBER), employee.getPhoneMiddleNumberDigit());
        assertEquals(Integer.parseInt(PHONE_LAST_NUMBER), employee.getPhoneLastNumberDigit());
        assertEquals(BIRTHDAY, employee.getBirthday());
        assertEquals(BIRTHDAY_YEAR, employee.getBirthdayYear());
        assertEquals(BIRTHDAY_MONTH, employee.getBirthdayMonth());
        assertEquals(BIRTHDAY_DAY, employee.getBirthdayDay());
        assertEquals(Integer.parseInt(BIRTHDAY), employee.getBirthdayDigit());
        assertEquals(Integer.parseInt(BIRTHDAY_YEAR), employee.getBirthdayYearDigit());
        assertEquals(Integer.parseInt(BIRTHDAY_MONTH), employee.getBirthdayMonthDigit());
        assertEquals(Integer.parseInt(BIRTHDAY_DAY), employee.getBirthdayDayDigit());
        assertEquals(CERTI, employee.getCerti());
    }

    @Test
    void testSetNameMethod() {
        // given
        Employee employeeToChangeName = new Employee(EMPLOYEE_NUMBER, NAME, CAREER_LEVEL, PHONE_NUMBER, BIRTHDAY, CERTI);
        assertEquals(NAME, employeeToChangeName.getName());
        assertEquals(FIRST_NAME, employeeToChangeName.getFirstName());
        assertEquals(LAST_NAME, employeeToChangeName.getLastName());

        // when
        employeeToChangeName.setName("Clean Code");

        // then
        assertEquals("Clean Code", employeeToChangeName.getName());
        assertEquals("Clean", employeeToChangeName.getFirstName());
        assertEquals("Code", employeeToChangeName.getLastName());
    }

    @Test
    void testSetPhoneNumberMethod() {
        // given
        Employee employeeToChangePhoneNumber =
                new Employee(EMPLOYEE_NUMBER, NAME, CAREER_LEVEL, PHONE_NUMBER, BIRTHDAY, CERTI);
        assertEquals(PHONE_NUMBER, employeeToChangePhoneNumber.getPhoneNumber());
        assertEquals(PHONE_MIDDLE_NUMBER, employeeToChangePhoneNumber.getPhoneMiddleNumber());
        assertEquals(PHONE_LAST_NUMBER, employeeToChangePhoneNumber.getPhoneLastNumber());
        assertEquals(Integer.parseInt(PHONE_MIDDLE_NUMBER), employeeToChangePhoneNumber.getPhoneMiddleNumberDigit());
        assertEquals(Integer.parseInt(PHONE_LAST_NUMBER), employeeToChangePhoneNumber.getPhoneLastNumberDigit());

        // when
        employeeToChangePhoneNumber.setPhoneNumber("010-1234-5678");

        // then
        assertEquals("010-1234-5678", employeeToChangePhoneNumber.getPhoneNumber());
        assertEquals("1234", employeeToChangePhoneNumber.getPhoneMiddleNumber());
        assertEquals("5678", employeeToChangePhoneNumber.getPhoneLastNumber());
        assertEquals(1234, employeeToChangePhoneNumber.getPhoneMiddleNumberDigit());
        assertEquals(5678, employeeToChangePhoneNumber.getPhoneLastNumberDigit());
    }

    @Test
    void testSetBirthdayMethod() {
        // given
        Employee employeeToChangeBirthday =
                new Employee(EMPLOYEE_NUMBER, NAME, CAREER_LEVEL, PHONE_NUMBER, BIRTHDAY, CERTI);
        assertEquals(BIRTHDAY, employeeToChangeBirthday.getBirthday());
        assertEquals(BIRTHDAY_YEAR, employeeToChangeBirthday.getBirthdayYear());
        assertEquals(BIRTHDAY_MONTH, employeeToChangeBirthday.getBirthdayMonth());
        assertEquals(BIRTHDAY_DAY, employeeToChangeBirthday.getBirthdayDay());
        assertEquals(Integer.parseInt(BIRTHDAY), employeeToChangeBirthday.getBirthdayDigit());
        assertEquals(Integer.parseInt(BIRTHDAY_YEAR), employeeToChangeBirthday.getBirthdayYearDigit());
        assertEquals(Integer.parseInt(BIRTHDAY_MONTH), employeeToChangeBirthday.getBirthdayMonthDigit());
        assertEquals(Integer.parseInt(BIRTHDAY_DAY), employeeToChangeBirthday.getBirthdayDayDigit());

        // when
        employeeToChangeBirthday.setBirthday("20211208");

        // then
        assertEquals("20211208", employeeToChangeBirthday.getBirthday());
        assertEquals("2021", employeeToChangeBirthday.getBirthdayYear());
        assertEquals("12", employeeToChangeBirthday.getBirthdayMonth());
        assertEquals("08", employeeToChangeBirthday.getBirthdayDay());
        assertEquals(20211208, employeeToChangeBirthday.getBirthdayDigit());
        assertEquals(2021, employeeToChangeBirthday.getBirthdayYearDigit());
        assertEquals(12, employeeToChangeBirthday.getBirthdayMonthDigit());
        assertEquals(8, employeeToChangeBirthday.getBirthdayDayDigit());
    }

    @Test
    void testEqualsMethod() {
        assertTrue(employee.equals(employee));
        assertTrue(employee.equals(employeeWithSameEmployeeNumber));
        assertFalse(employee.equals(null));
        assertFalse(employee.equals(new Object()));
        assertFalse(employee.equals(employeeWithDifferentEmployeeNumber));
    }

    @Test
    void testHashCodeMethod() {
        assertEquals(employeeWithSameEmployeeNumber.hashCode(), employee.hashCode());
        assertNotEquals(employeeWithDifferentEmployeeNumber.hashCode(), employee.hashCode());
    }
}
