package com.sec.bestreviewer.util;

import com.sec.bestreviewer.command.CommandType;
import com.sec.bestreviewer.store.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultStringFormatterTest {

    List<Employee> employeeList;

    @BeforeEach
    void beforeEach() {
        employeeList = new ArrayList<>();
        employeeList.add(new Employee("17905165", "Angelina Lee", "CL4", "010-1234-4998", "20090618", "ADV"));
        employeeList.add(new Employee("19172754", "Anybody Park", "CL2", "010-8211-3991", "20111113", "ADV"));
        employeeList.add(new Employee("90456837", "Daniel Yoon", "CL3", "010-1234-3991", "20090618", "PRO"));
        employeeList.add(new Employee("93201880", "Smith Yoon", "CL2", "010-1211-3991", "20160505", "ADV"));
        employeeList.add(new Employee("10258522", "Leo Kim", "CL2", "010-2347-5678", "20190101", "EX"));
        employeeList.add(new Employee("98599136", "Justin Kim", "CL1", "010-1234-4639", "19970423", "ADV"));
        employeeList.add(new Employee("11067095", "Sunny Moon", "CL3", "010-7491-9246", "19970423", "PRO"));
        employeeList.add(new Employee("90023138", "Anybody Lim", "CL1", "010-2347-8546", "19911214", "PRO"));
    }

    @Test
    void TestGetEmployeeListToFormattedString_직원이없는경우() {
        List<String> expected = Collections.singletonList("MOD,NONE");
        List<String> actual = ResultStringFormatter.getEmployeeListToFormattedString(new ArrayList<>(), CommandType.MOD, 5);
        assertEquals(expected, actual);

        actual = ResultStringFormatter.getEmployeeListToFormattedString(new ArrayList<>(), CommandType.MOD);
        assertEquals(expected, actual);
    }

    @Test
    void TestGetEmployeeListToFormattedString_리미트있는경우() {
        List<String> expected = Arrays.asList(
                "MOD,90023138,Anybody Lim,CL1,010-2347-8546,19911214,PRO",
                "MOD,90456837,Daniel Yoon,CL3,010-1234-3991,20090618,PRO",
                "MOD,93201880,Smith Yoon,CL2,010-1211-3991,20160505,ADV",
                "MOD,98599136,Justin Kim,CL1,010-1234-4639,19970423,ADV",
                "MOD,10258522,Leo Kim,CL2,010-2347-5678,20190101,EX"
        );
        List<String> actual = ResultStringFormatter.getEmployeeListToFormattedString(employeeList, CommandType.MOD, 5);
        assertEquals(expected, actual);
    }

    @Test
    void TestGetEmployeeListToFormattedString_리미트없는경우() {
        List<String> expected = Collections.singletonList("MOD,8");
        List<String> actual = ResultStringFormatter.getEmployeeListToFormattedString(employeeList, CommandType.MOD);
        assertEquals(expected, actual);
    }

}