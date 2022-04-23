package com.sec.bestreviewer.store;

import com.sec.bestreviewer.command.SearchEmployeeNumberCommand;
import com.sec.bestreviewer.command.SearchFilterMaker;
import com.sec.bestreviewer.data.InqualitySignOption;
import com.sec.bestreviewer.data.SearchOption;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class EmployeeStoreImplTest {

    private static int employeeCount;
    private static EmployeeStoreImpl employeeStore;

    @BeforeAll
    public static void setUpAll() {
        employeeCount = 0;
        employeeStore = new EmployeeStoreImpl();
    }

    @ParameterizedTest
    @MethodSource
    void testAdd(String id, String name, String careerLevel, String phoneNumber, String birthday, String certi) {
        Employee employee = new Employee(id, name, careerLevel, phoneNumber, birthday, certi);

        employeeStore.add(employee);
        employeeCount++;
        SearchFilterMaker filterMaker = new SearchEmployeeNumberCommand();
        List<Employee> employees = employeeStore.search(filterMaker.createFilter(SearchOption.EMPLOYEE_NUMBER, InqualitySignOption.NONE, id));
        assertEquals(1, employees.size());
        assertTrue(employees.contains(employee));

        assertEquals(employeeCount, employeeStore.count());
    }

    static Stream<Arguments> testAdd() {
        return Stream.of(
                arguments("01010101", "DKDKDA KIM", "CL3", "010-1111-2222", "19930607", "EX"),
                arguments("11082202", "DIIDID GO", "CL2", "010-1234-5678", "19930607", "PRO"),
                arguments("94040499", "QQQQ LEE", "CL4", "010-5555-6666", "19740404", "ADV"),
                arguments("03010302", "DKDKDA KIM", "CL3", "010-3333-4444", "19830302", "EX"),
                arguments("89123133", "AIPODF OH", "CL1", "010-7777-8888", "20691231", "PRO"),
                arguments("13060742", "POIEFGG CHOI", "CL2", "010-9999-0000", "19930607", "ADV")
        );
    }
}