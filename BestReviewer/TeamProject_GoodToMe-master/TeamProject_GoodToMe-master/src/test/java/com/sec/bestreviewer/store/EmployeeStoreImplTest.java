package com.sec.bestreviewer.store;

import com.sec.bestreviewer.command.querycondition.QueryCondition;
import com.sec.bestreviewer.command.queryprocessor.QueryProcessor;
import com.sec.bestreviewer.util.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeStoreImplTest {

    EmployeeStore employeeStore;
    QueryProcessor queryProcessor;

    @BeforeEach
    void setup() {
        employeeStore = new EmployeeStoreImpl();
        employeeStore.add(new Employee("18050301", "KYUMOK KIM",
                "CL2", "010-9777-6055", "19980906", "PRO"));
        employeeStore.add(new Employee("17302853", "KYUMOK Lee",
                "CL2", "010-2345-2222", "19980321", "EX"));
        employeeStore.add(new Employee("16301767", "Kildong Hong",
                "CL3", "010-5555-3333", "19900507", "PRO"));
        employeeStore.add(new Employee("15301767", "Kildong Lee",
                "CL3", "010-5555-2222", "19880607", "PRO"));
        employeeStore.add(new Employee("02301523", "MiJung Choi",
                "CL4", "010-5555-6055", "19800507", "ADV"));
    }

    QueryCondition getQueryCondition(String storeField, String value, String option2, String option3) {
        QueryCondition queryCondition = new QueryCondition();
        queryCondition.setColumn(storeField);
        queryCondition.setValue(value);
        queryCondition.setOption2(option2);
        queryCondition.setOption3(option3);
        return queryCondition;
    }

    @Test
    void modify() {
        List<QueryCondition> queryConditions = new ArrayList<>();
        queryConditions.add(getQueryCondition("careerLevel", "CL2", " ", ""));
        String joinOperator = "";
        queryProcessor = new QueryProcessor(queryConditions, joinOperator);

        List<Employee> employeeList = employeeStore.modify(queryProcessor, Pair.create("careerLevel","CL3"));
        assertEquals(2, employeeList.size());
        List<Employee> employeeListAfterModify = employeeStore.search(queryProcessor);
        assertEquals(0, employeeListAfterModify.size());
    }

    @Test
    void modifyWrongField() {
        List<QueryCondition> queryConditions = new ArrayList<>();
        queryConditions.add(getQueryCondition("careerLevel", "CL2", " ", ""));
        String joinOperator = "";
        queryProcessor = new QueryProcessor(queryConditions, joinOperator);

        Assertions.assertThrows(RuntimeException.class, () -> {
            employeeStore.modify(queryProcessor, Pair.create("cl","CL3"));
        });
    }

    @ParameterizedTest
    @CsvSource({
            "name,KYUMOK KIM,' ',1",
            "name,KYUMOK,-f,2",
            "name,Lee,-l,2",
            "employeeNumber,18050301,' ',1",
            "employeeNumber,18050300,' ',0",
            "careerLevel,CL2,' ',2",
            "careerLevel,CL4,' ',1",
            "phoneNumber,010-5555-3333,' ',1",
            "phoneNumber,5555,-m,3",
            "phoneNumber,2222,-l,2",
            "birthDay,19980906,' ',1",
            "birthDay,1998,-y,2",
            "birthDay,05,-m,2",
            "birthDay,07,-d,3",
            "certi,PRO,' ',3",
            "certi,EX,' ',1",
    })
    void searchCount(String storeField, String value, String option2, int expectedCount) {
        List<QueryCondition> queryConditions = new ArrayList<>();

        queryConditions.add(getQueryCondition(storeField, value, option2, ""));
        String joinOperator = "";

        queryProcessor = new QueryProcessor(queryConditions, joinOperator);

        List<Employee> employeeList = employeeStore.search(queryProcessor);
        assertEquals(expectedCount, employeeList.size());
    }

    @ParameterizedTest
    @CsvSource({
            "name,KYUMOK KIM,' ',4",
            "name,KYUMOK,-f,3",
            "name,Lee,-l,3",
            "employeeNumber,18050301,' ',4",
            "employeeNumber,18050300,' ',5",
            "careerLevel,CL2,' ',3",
            "careerLevel,CL4,' ',4",
            "phoneNumber,010-5555-3333,' ',4",
            "phoneNumber,5555,-m,2",
            "phoneNumber,2222,-l,3",
            "birthDay,19980906,' ',4",
            "birthDay,1998,-y,3",
            "birthDay,05,-m,3",
            "birthDay,07,-d,2",
            "certi,PRO,' ',2",
            "certi,EX,' ',4",
    })
    void storeCountAfterDelete(String storeField, String value, String option2, int expectedStoreCount) {
        List<QueryCondition> queryConditions = new ArrayList<>();

        queryConditions.add(getQueryCondition(storeField, value, option2, ""));
        String joinOperator = "";

        queryProcessor = new QueryProcessor(queryConditions, joinOperator);

        employeeStore.delete(queryProcessor);
        assertEquals(expectedStoreCount, employeeStore.count());
    }

    @Test
    void addNewEmployee() {
        employeeStore.add(new Employee("13301767", "Chulsoo Kim",
                "CL3", "010-4433-3333", "20010507", "PRO"));
        assertEquals(6, employeeStore.count());
    }

    @Test
    @DisplayName("사원번호는 중복될 수 없다")
    void addDuplicatedEmployeeNumber() {
        employeeStore.add(new Employee("15301767", "Chulsoo Kim",
                "cl3", "010-4433-3333", "20010507", "PRO"));
        assertEquals(5, employeeStore.count());
    }
}
