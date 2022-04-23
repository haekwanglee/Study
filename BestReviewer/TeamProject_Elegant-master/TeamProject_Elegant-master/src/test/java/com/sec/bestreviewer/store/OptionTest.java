package com.sec.bestreviewer.store;

import com.sec.bestreviewer.store.finder.FieldFinder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionTest {

    final List<Employee> employees = Arrays.asList(
            new Employee(
                    "10000001", "AA KIM", "CL1", "010-1111-5678", "20190101", "ADV"
            ),
            new Employee(
                    "10000002", "BB KIM", "CL1", "010-1111-5678", "20190101", "ADV"
            ),
            new Employee(
                    "10000003", "BB LEE", "CL1", "010-1111-5678", "20190101", "ADV"
            )
    );

    @Test
    public void testOptionFirstName() {
        EmployeeFieldType employeeFieldType = EmployeeFieldType.NAME;
        FieldFinder fieldFinder = new FieldFinder(employeeFieldType, employees);

        OptionProperty optionProperty = new OptionProperty();
        optionProperty.setOptionAttribute(OptionAttributeType.ATTRIBUTE_FIRST_NAME);

        fieldFinder.setOption(optionProperty);

        List<Employee> result;

        result = fieldFinder.find("AA");
        assertEquals(1, result.size());

        result = fieldFinder.find("BB");
        assertEquals(2, result.size());
    }

    @Test
    public void testOptionLastName() {
        EmployeeFieldType employeeFieldType = EmployeeFieldType.NAME;
        FieldFinder fieldFinder = new FieldFinder(employeeFieldType, employees);

        OptionProperty optionProperty = new OptionProperty();
        optionProperty.setOptionAttribute(OptionAttributeType.ATTRIBUTE_LAST_NAME);

        fieldFinder.setOption(optionProperty);

        List<Employee> result;

        result = fieldFinder.find("LEE");
        assertEquals(1, result.size());

        result = fieldFinder.find("KIM");
        assertEquals(2, result.size());
    }

    @Test
    public void testOptionMiddleNumber() {
        EmployeeFieldType employeeFieldType = EmployeeFieldType.PHONE_NUMBER;
        FieldFinder fieldFinder = new FieldFinder(employeeFieldType, employees);

        OptionProperty optionProperty = new OptionProperty();
        optionProperty.setOptionAttribute(OptionAttributeType.ATTRIBUTE_MIDDLE_NUMBER);

        fieldFinder.setOption(optionProperty);

        List<Employee> result;

        result = fieldFinder.find("1111");
        assertEquals(3, result.size());

        result = fieldFinder.find("5678");
        assertEquals(0, result.size());
    }

    @Test
    public void testOptionLastNumber() {
        EmployeeFieldType employeeFieldType = EmployeeFieldType.PHONE_NUMBER;
        FieldFinder fieldFinder = new FieldFinder(employeeFieldType, employees);

        OptionProperty optionProperty = new OptionProperty();
        optionProperty.setOptionAttribute(OptionAttributeType.ATTRIBUTE_LAST_NUMBER);

        fieldFinder.setOption(optionProperty);

        List<Employee> result;

        result = fieldFinder.find("1111");
        assertEquals(0, result.size());

        result = fieldFinder.find("5678");
        assertEquals(3, result.size());
    }

    @Test
    public void testOptionYear() {
        EmployeeFieldType employeeFieldType = EmployeeFieldType.BIRTH_DAY;
        FieldFinder fieldFinder = new FieldFinder(employeeFieldType, employees);

        OptionProperty optionProperty = new OptionProperty();
        optionProperty.setOptionAttribute(OptionAttributeType.ATTRIBUTE_YEAR);

        fieldFinder.setOption(optionProperty);

        List<Employee> result;

        result = fieldFinder.find("2019");
        assertEquals(3, result.size());

        result = fieldFinder.find("2018");
        assertEquals(0, result.size());
    }

    @Test
    public void testOptionMonth() {
        EmployeeFieldType employeeFieldType = EmployeeFieldType.BIRTH_DAY;
        FieldFinder fieldFinder = new FieldFinder(employeeFieldType, employees);

        OptionProperty optionProperty = new OptionProperty();
        optionProperty.setOptionAttribute(OptionAttributeType.ATTRIBUTE_MONTH);

        fieldFinder.setOption(optionProperty);

        List<Employee> result;

        result = fieldFinder.find("01");
        assertEquals(3, result.size());

        result = fieldFinder.find("11");
        assertEquals(0, result.size());
    }

    @Test
    public void testOptionDay() {
        EmployeeFieldType employeeFieldType = EmployeeFieldType.BIRTH_DAY;
        FieldFinder fieldFinder = new FieldFinder(employeeFieldType, employees);

        OptionProperty optionProperty = new OptionProperty();
        optionProperty.setOptionAttribute(OptionAttributeType.ATTRIBUTE_DAY);

        fieldFinder.setOption(optionProperty);

        List<Employee> result;

        result = fieldFinder.find("01");
        assertEquals(3, result.size());

        result = fieldFinder.find("31");
        assertEquals(0, result.size());
    }

}
