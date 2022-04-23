package com.sec.bestreviewer.store.finder;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeFieldType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldFinderTest {

    private final List<Employee> employees = Arrays.asList(
            new Employee(
                    Integer.toString(90_000000 + (10_000000)),
                    "AAA LastName",
                    "CL1",
                    "010-1111-5678",
                    "20190101",
                    "ADV"
            ),
            new Employee(
                    Integer.toString(90_000000 + (20_000000)),
                    "BBB LastName",
                    "CL2",
                    "010-2222-2222",
                    "20180505",
                    "PRO"
            ),
            new Employee(
                    Integer.toString(90_000000 + (30_000000)),
                    "BBB LastName",
                    "CL2",
                    "010-3333-1111",
                    "20011231",
                    "PRO"
            ),
            new Employee(
                    Integer.toString(90_000000 + (40_000000)),
                    "CCC LastName",
                    "CL3",
                    "010-4444-1234",
                    "19980101",
                    "ADV"
            ),
            new Employee(
                    Integer.toString(90_000000 + (50_000000)),
                    "DDD LastName",
                    "CL3",
                    "010-4444-5555",
                    "19500707",
                    "PRO"
            ),
            new Employee(
                    Integer.toString(90_000000 + (60_000000)),
                    "EEE LastName",
                    "CL3",
                    "010-5555-6666",
                    "20140505",
                    "EX"
            ),
            new Employee(
                    Integer.toString(90_000000 + (70_000000)),
                    "FFF LastName",
                    "CL2",
                    "010-5555-6666",
                    "20180505",
                    "PRO"
            )
    );

    @Test
    public void testFieldBirthDayFinder() {
        EmployeeFieldType employeeFieldType = EmployeeFieldType.BIRTH_DAY;
        FieldFinder fieldFinder = new FieldFinder(employeeFieldType, employees);

        List<Employee> result;
        result = fieldFinder.find("20190101");
        assertEquals(1, result.size());

        result = fieldFinder.find("20180505");
        assertEquals(2, result.size());

        result = fieldFinder.find("11111111");
        assertEquals(0, result.size());
    }

    @Test
    public void testFieldCareerLevelFinder() {
        EmployeeFieldType employeeFieldType = EmployeeFieldType.CAREER_LEVEL;
        FieldFinder fieldFinder = new FieldFinder(employeeFieldType, employees);

        List<Employee> result;
        result = fieldFinder.find("CL1");
        assertEquals(1, result.size());

        result = fieldFinder.find("CL3");
        assertEquals(3, result.size());

        result = fieldFinder.find("CL4");
        assertEquals(0, result.size());
    }

    @Test
    public void testFieldCertiFinder() {
        EmployeeFieldType employeeFieldType = EmployeeFieldType.CERTI;
        FieldFinder fieldFinder = new FieldFinder(employeeFieldType, employees);

        List<Employee> result;
        result = fieldFinder.find("ADV");
        assertEquals(2, result.size());

        result = fieldFinder.find("PRO");
        assertEquals(4, result.size());

        result = fieldFinder.find("EX");
        assertEquals(1, result.size());
    }

    @Test
    public void testFieldEmployeeNumberFinder() {
        EmployeeFieldType employeeFieldType = EmployeeFieldType.EMPLOYEE_NUMBER;
        FieldFinder fieldFinder = new FieldFinder(employeeFieldType, employees);

        List<Employee> result;
        result = fieldFinder.find(Integer.toString(90_000000 + (10_000000)));
        assertEquals(1, result.size());

        result = fieldFinder.find(Integer.toString(90_000000 + (30_000000)));
        assertEquals(1, result.size());

        result = fieldFinder.find("1111");
        assertEquals(0, result.size());
    }

    @Test
    public void testFieldNameFinder() {
        EmployeeFieldType employeeFieldType = EmployeeFieldType.NAME;
        FieldFinder fieldFinder = new FieldFinder(employeeFieldType, employees);

        List<Employee> result;
        result = fieldFinder.find("AAA LastName");
        assertEquals(1, result.size());

        result = fieldFinder.find("BBB LastName");
        assertEquals(2, result.size());

        result = fieldFinder.find("ZZZ LastName");
        assertEquals(0, result.size());
    }

    @Test
    public void testPhoneNumberNameFinder() {
        EmployeeFieldType employeeFieldType = EmployeeFieldType.PHONE_NUMBER;
        FieldFinder fieldFinder = new FieldFinder(employeeFieldType, employees);

        List<Employee> result;
        result = fieldFinder.find("010-1111-5678");
        assertEquals(1, result.size());

        result = fieldFinder.find("010-5555-6666");
        assertEquals(2, result.size());

        result = fieldFinder.find("011-123-4567");
        assertEquals(0, result.size());
    }
}