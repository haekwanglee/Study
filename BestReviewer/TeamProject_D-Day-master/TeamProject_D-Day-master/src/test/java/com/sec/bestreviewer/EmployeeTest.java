package com.sec.bestreviewer;

import com.sec.bestreviewer.store.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testEqual() {
        Employee firstEmployee = new Employee("18050301", "KYUMOK KIM", "CL2", "010-9777-6055", "19980906", "PRO");
        Employee SameEmployee = new Employee("18050301", "SEO KFI", "CL1", "010-1234-5678", "20190101", "EX");
        Employee DifferentEmployee = new Employee("18050302", "SEO KFI", "CL1", "010-1234-5678", "20190101", "ADV");
        Employee SameEmployeeWithObject = new Employee(SameEmployee);

        assertTrue(firstEmployee.equals(firstEmployee));
        assertFalse(firstEmployee.equals(null));
        assertTrue(firstEmployee.equals(SameEmployee));
        assertFalse(firstEmployee.equals(DifferentEmployee));
        assertTrue(SameEmployee.equals(SameEmployeeWithObject));
    }
}