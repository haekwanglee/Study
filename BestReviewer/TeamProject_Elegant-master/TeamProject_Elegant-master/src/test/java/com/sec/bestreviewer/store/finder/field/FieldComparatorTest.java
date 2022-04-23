package com.sec.bestreviewer.store.finder.field;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeFieldType;
import com.sec.bestreviewer.store.OptionAttributeType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldComparatorTest {

    FieldComparator fieldComparator;

    @Test
    void testFieldComparatorEqual() {
        Employee employee = new Employee("17905165", "Angelina Lee", "CL4", "010-1234-4998", "20090618", "ADV");

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.EMPLOYEE_NUMBER, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.equals(employee, "17905165"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.equals(employee, "Angelina Lee"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.CAREER_LEVEL, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.equals(employee, "CL4"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.PHONE_NUMBER, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.equals(employee, "010-1234-4998"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.BIRTH_DAY, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.equals(employee, "20090618"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.CERTI, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.equals(employee, "ADV"));
    }

    @Test
    void testFieldComparatorEqualFailed() {
        Employee employee = new Employee("17905165", "Angelina Lee", "CL4", "010-1234-4998", "20090618", "ADV");

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.EMPLOYEE_NUMBER, OptionAttributeType.DEFAULT);
        assertFalse(fieldComparator.equals(employee, "00000000"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.DEFAULT);
        assertFalse(fieldComparator.equals(employee, "00000000"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.CAREER_LEVEL, OptionAttributeType.DEFAULT);
        assertFalse(fieldComparator.equals(employee, "00000000"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.PHONE_NUMBER, OptionAttributeType.DEFAULT);
        assertFalse(fieldComparator.equals(employee, "00000000"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.BIRTH_DAY, OptionAttributeType.DEFAULT);
        assertFalse(fieldComparator.equals(employee, "00000000"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.CERTI, OptionAttributeType.DEFAULT);
        assertFalse(fieldComparator.equals(employee, "00000000"));
    }

    @Test
    void testFieldComparatorGreater() {
        Employee employee = new Employee("17905165", "Angelina Lee", "CL4", "010-1234-4998", "20090618", "PRO");

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.EMPLOYEE_NUMBER, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.greaterThan(employee, "16905135"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.greaterThan(employee, "Aagelina"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.CAREER_LEVEL, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.greaterThan(employee, "CL3"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.PHONE_NUMBER, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.greaterThan(employee, "010-1111-4998"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.BIRTH_DAY, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.greaterThan(employee, "20030618"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.CERTI, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.greaterThan(employee, "ADV"));
    }

    @Test
    void testFieldComparatorGreaterEqual() {
        Employee employee = new Employee("17905165", "Angelina Lee", "CL4", "010-1234-4998", "20090618", "PRO");

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.EMPLOYEE_NUMBER, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.greaterThanOrEqualTo(employee, "16905135"));
        assertTrue(fieldComparator.greaterThanOrEqualTo(employee, "17905165"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.greaterThanOrEqualTo(employee, "Aagelina"));
        assertTrue(fieldComparator.greaterThanOrEqualTo(employee, "Angelina Lee"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.CAREER_LEVEL, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.greaterThanOrEqualTo(employee, "CL3"));
        assertTrue(fieldComparator.greaterThanOrEqualTo(employee, "CL4"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.PHONE_NUMBER, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.greaterThanOrEqualTo(employee, "010-1111-4998"));
        assertTrue(fieldComparator.greaterThanOrEqualTo(employee, "010-1234-4998"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.BIRTH_DAY, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.greaterThanOrEqualTo(employee, "20030618"));
        assertTrue(fieldComparator.greaterThanOrEqualTo(employee, "20090618"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.CERTI, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.greaterThanOrEqualTo(employee, "ADV"));
        assertTrue(fieldComparator.greaterThanOrEqualTo(employee, "PRO"));
    }

    @Test
    void testFieldComparatorSmaller() {
        Employee employee = new Employee("17905165", "Angelina Lee", "CL2", "010-1234-4998", "20090618", "ADV");

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.EMPLOYEE_NUMBER, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.smallerThan(employee, "18905135"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.smallerThan(employee, "Aogelina"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.CAREER_LEVEL, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.smallerThan(employee, "CL3"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.PHONE_NUMBER, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.smallerThan(employee, "010-9111-4998"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.BIRTH_DAY, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.smallerThan(employee, "20220618"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.CERTI, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.smallerThan(employee, "PRO"));
    }

    @Test
    void testFieldComparatorSmallerEqual() {
        Employee employee = new Employee("17905165", "Angelina Lee", "CL2", "010-1234-4998", "20090618", "ADV");

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.EMPLOYEE_NUMBER, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.smallerThanOrEqualTo(employee, "18905135"));
        assertTrue(fieldComparator.smallerThanOrEqualTo(employee, "17905165"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.smallerThanOrEqualTo(employee, "Aogelina"));
        assertTrue(fieldComparator.smallerThanOrEqualTo(employee, "Angelina Lee"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.CAREER_LEVEL, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.smallerThanOrEqualTo(employee, "CL3"));
        assertTrue(fieldComparator.smallerThanOrEqualTo(employee, "CL2"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.PHONE_NUMBER, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.smallerThanOrEqualTo(employee, "010-9111-4998"));
        assertTrue(fieldComparator.smallerThanOrEqualTo(employee, "010-1234-4998"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.BIRTH_DAY, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.smallerThanOrEqualTo(employee, "20220618"));
        assertTrue(fieldComparator.smallerThanOrEqualTo(employee, "20090618"));

        fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.CERTI, OptionAttributeType.DEFAULT);
        assertTrue(fieldComparator.smallerThanOrEqualTo(employee, "PRO"));
        assertTrue(fieldComparator.smallerThanOrEqualTo(employee, "ADV"));
    }

    @Nested
    class OptionTest {

        @Test
        void testFirstNameFieldComparator() {
            Employee employee = new Employee("17905165", "Angelina Lee", "CL2", "010-1234-4998", "20090618", "ADV");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_FIRST_NAME);
            fieldComparator.equals(employee, "Angelina");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_FIRST_NAME);
            fieldComparator.greaterThan(employee, "AA");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_FIRST_NAME);
            fieldComparator.greaterThanOrEqualTo(employee, "AA");
            fieldComparator.greaterThanOrEqualTo(employee, "Angelina");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_FIRST_NAME);
            fieldComparator.smallerThan(employee, "BB");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_FIRST_NAME);
            fieldComparator.smallerThanOrEqualTo(employee, "BB");
            fieldComparator.smallerThanOrEqualTo(employee, "Angelina");
        }

        @Test
        void testLastNameFieldComparator() {
            Employee employee = new Employee("17905165", "Angelina Lee", "CL2", "010-1234-4998", "20090618", "ADV");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_LAST_NAME);
            fieldComparator.equals(employee, "Lee");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_LAST_NAME);
            fieldComparator.greaterThan(employee, "AA");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_LAST_NAME);
            fieldComparator.greaterThanOrEqualTo(employee, "AA");
            fieldComparator.greaterThanOrEqualTo(employee, "Lee");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_LAST_NAME);
            fieldComparator.smallerThan(employee, "ZZ");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_LAST_NAME);
            fieldComparator.smallerThanOrEqualTo(employee, "ZZ");
            fieldComparator.smallerThanOrEqualTo(employee, "Lee");
        }

        @Test
        void testYearFieldComparator() {
            Employee employee = new Employee("17905165", "Angelina Lee", "CL2", "010-1234-4998", "20090618", "ADV");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_YEAR);
            fieldComparator.equals(employee, "2009");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_YEAR);
            fieldComparator.greaterThan(employee, "2008");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_YEAR);
            fieldComparator.greaterThanOrEqualTo(employee, "2008");
            fieldComparator.greaterThanOrEqualTo(employee, "2009");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_YEAR);
            fieldComparator.smallerThan(employee, "2010");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_YEAR);
            fieldComparator.smallerThanOrEqualTo(employee, "2010");
            fieldComparator.smallerThanOrEqualTo(employee, "2009");
        }

        @Test
        void testMonthFieldComparator() {
            Employee employee = new Employee("17905165", "Angelina Lee", "CL2", "010-1234-4998", "20090618", "ADV");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_MONTH);
            fieldComparator.equals(employee, "06");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_MONTH);
            fieldComparator.greaterThan(employee, "0");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_MONTH);
            fieldComparator.greaterThanOrEqualTo(employee, "01");
            fieldComparator.greaterThanOrEqualTo(employee, "06");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_MONTH);
            fieldComparator.smallerThan(employee, "12");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_MONTH);
            fieldComparator.smallerThanOrEqualTo(employee, "12");
            fieldComparator.smallerThanOrEqualTo(employee, "06");
        }

        @Test
        void testDayFieldComparator() {
            Employee employee = new Employee("17905165", "Angelina Lee", "CL2", "010-1234-4998", "20090618", "ADV");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_DAY);
            fieldComparator.equals(employee, "18");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_DAY);
            fieldComparator.greaterThan(employee, "01");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_DAY);
            fieldComparator.greaterThanOrEqualTo(employee, "01");
            fieldComparator.greaterThanOrEqualTo(employee, "18");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_DAY);
            fieldComparator.smallerThan(employee, "31");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_DAY);
            fieldComparator.smallerThanOrEqualTo(employee, "31");
            fieldComparator.smallerThanOrEqualTo(employee, "18");
        }

        @Test
        void testMiddleNumberFieldComparator() {
            Employee employee = new Employee("17905165", "Angelina Lee", "CL2", "010-1234-4998", "20090618", "ADV");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_MIDDLE_NUMBER);
            fieldComparator.equals(employee, "1234");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_MIDDLE_NUMBER);
            fieldComparator.greaterThan(employee, "1000");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_MIDDLE_NUMBER);
            fieldComparator.greaterThanOrEqualTo(employee, "1000");
            fieldComparator.greaterThanOrEqualTo(employee, "1234");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_MIDDLE_NUMBER);
            fieldComparator.smallerThan(employee, "9999");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_MIDDLE_NUMBER);
            fieldComparator.smallerThanOrEqualTo(employee, "9999");
            fieldComparator.smallerThanOrEqualTo(employee, "1234");
        }

        @Test
        void testLastNumberFieldComparator() {
            Employee employee = new Employee("17905165", "Angelina Lee", "CL2", "010-1234-4998", "20090618", "ADV");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_LAST_NUMBER);
            fieldComparator.equals(employee, "4998");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_LAST_NUMBER);
            fieldComparator.greaterThan(employee, "1000");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_LAST_NUMBER);
            fieldComparator.greaterThanOrEqualTo(employee, "1000");
            fieldComparator.greaterThanOrEqualTo(employee, "4998");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_LAST_NUMBER);
            fieldComparator.smallerThan(employee, "9999");

            fieldComparator = FieldComparatorFactory.createFieldComparator(EmployeeFieldType.NAME, OptionAttributeType.ATTRIBUTE_LAST_NUMBER);
            fieldComparator.smallerThanOrEqualTo(employee, "9999");
            fieldComparator.smallerThanOrEqualTo(employee, "4998");
        }
    }

}