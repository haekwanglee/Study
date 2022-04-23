package com.sec.bestreviewer.store.searchfilter;

import com.sec.bestreviewer.base.*;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.test.CommandGenerator;
import com.sec.bestreviewer.test.OptionIdEnumForTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeNumberFilterTest {

    @Test
    @DisplayName("동일 사원 번호 필드 값을 가진 Employee 검색")
    void searchByEmployeeNumberReturnExpected() {

        // prepare
        final List<Employee> employees = new CommandGenerator().generateEmployees(5);
        Employee targetEmployee = employees.get(2);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_EMPLOYEE_NUMBER,
                        targetEmployee.getEmployeeNumber(),
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_NONE
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = new EmployeeNumberFilter().search(employees, param);

        // validate
        assertEquals(1, ret.size());
        assertEquals(targetEmployee.getEmployeeNumber(), ret.get(0).getEmployeeNumber());
    }

    @ParameterizedTest
    @DisplayName("사원 번호 부등호를 활용한 검색 테스트")
    @CsvSource({
            // "OptionIdEnumForTest", "expected employee size", "message"
            "GreaterThan, 6, '초과 필드 값을 가진 Employee 검색'",
            "SmallerThan, 3, '미만 필드 값을 가진 Employee 검색'",
            "GreaterThanEqualsTo, 7, '이상 필드 값을 가진 Employee 검색'",
            "SmallerThanEqualsTo, 4, '이하 필드 값을 가진 Employee 검색'",
    })
    void searchEmployeeNumberBySignReturnExpected(String signTag, int expectedSize, String message) {

        // prepare
        final List<Employee> employees = new CommandGenerator().generateEmployees(10).stream()
                .sorted(Comparator.comparing(Employee::getEmployeeNumberDateFormatYyyy))
                .collect(Collectors.toList());

        Employee targetEmployee = employees.get(3);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_EMPLOYEE_NUMBER,
                        targetEmployee.getEmployeeNumber(),
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_NONE,
                        OptionIdEnumForTest.valueOf(signTag).id
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = new EmployeeNumberFilter().search(employees, param);

        // validate
        assertEquals(expectedSize, ret.size(), message);
    }
}