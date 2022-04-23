package com.sec.bestreviewer.store;

import com.sec.bestreviewer.store.field.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeStoreImplTest {
    @Test
    public void testModify() {
        EmployeeStore store = new EmployeeStoreImpl();
        List<Employee> employees = new ArrayList<>();
        String name = "ABC DEF";
        for (int i = 0; i < 10; i++) {
            Employee employee = new Employee(
                    Integer.toString(17000002 + i),
                    name + i,
                    "CL2",
                    "010-1234-1234",
                    "19900909",
                    "PRO"
            );
            employees.add(employee);
            store.add(employee);
        }

        String newName = "GILDONG HONG";
        store.modify(employees, EmployeeStore.FIELD_NAME, newName);
        for (Employee employee : employees) {
            assertEquals(newName, employee.getName().toString());
        }
    }

    @Test
    public void testGetField() {
        EmployeeStoreImpl store = new EmployeeStoreImpl();
        Employee employee = new Employee(
                "17000002",
                "ABC DEF",
                "CL2",
                "010-1234-1234",
                "19900909",
                "PRO"
        );
        assertTrue(store.getFieldGetter(EmployeeStore.FIELD_EMPLOYEE_NUMBER).apply(employee)
                instanceof EmployeeNumber);
        assertTrue(store.getFieldGetter(EmployeeStore.FIELD_NAME).apply(employee)
                instanceof Name);
        assertTrue(store.getFieldGetter(EmployeeStore.FIELD_CAREER_LEVEL).apply(employee)
                instanceof CareerLevel);
        assertTrue(store.getFieldGetter(EmployeeStore.FIELD_PHONE_NUMBER).apply(employee)
                instanceof PhoneNumber);
        assertTrue(store.getFieldGetter(EmployeeStore.FIELD_BIRTH_DAY).apply(employee)
                instanceof Birthday);
        assertTrue(store.getFieldGetter(EmployeeStore.FIELD_CERTI).apply(employee)
                instanceof Certificate);
        assertThrows(RuntimeException.class, () -> store.getFieldGetter("WRONG"));
    }
}
