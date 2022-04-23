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

class LastPhoneNumberFilterTest {

    @Test
    @DisplayName("동일 필드 값을 가진 Employee 검색")
    void searchByLastPhoneNumberReturnExpected() {

        // prepare
        final List<Employee> employees = new CommandGenerator().generateEmployees(5);
        Employee targetEmployee = employees.get(2);
        String lastNumber = getLastPhoneNumber(targetEmployee.getPhoneNumber());

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_PHONE_NUMBER,
                        lastNumber,
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_PHONE_NUMBER_LAST,
                        OptionId.OPTION_NONE
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = new LastPhoneNumberFilter().search(employees, param);

        // validate
        assertEquals(1, ret.size());
        assertEquals(targetEmployee.getPhoneNumber(), ret.get(0).getPhoneNumber());
    }

    @ParameterizedTest
    @DisplayName("전화번호 뒷 자리 부등호를 활용한 검색 테스트")
    @CsvSource({
            // "OptionIdEnumForTest", "expected employee size", "message"
            "GreaterThan, 6, '초과 필드 값을 가진 Employee 검색'",
            "SmallerThan, 3, '미만 필드 값을 가진 Employee 검색'",
            "GreaterThanEqualsTo, 7, '이상 필드 값을 가진 Employee 검색'",
            "SmallerThanEqualsTo, 4, '이하 필드 값을 가진 Employee 검색'",
    })
    void searchLastPhoneNumberBySignReturnExpected(String signTag, int expectedSize, String message) {

        // prepare
        final List<Employee> employees = new CommandGenerator().generateEmployees(10).stream()
                .sorted(Comparator.comparing(it -> getLastPhoneNumber(it.getPhoneNumber())))
                .collect(Collectors.toList());

        Employee targetEmployee = employees.get(3);
        String lastNumber = getLastPhoneNumber(targetEmployee.getPhoneNumber());

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_PHONE_NUMBER,
                        lastNumber,
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_PHONE_NUMBER_LAST,
                        OptionIdEnumForTest.valueOf(signTag).id
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = new LastPhoneNumberFilter().search(employees, param);

        // validate
        assertEquals(expectedSize, ret.size(), message);
    }

    private String getLastPhoneNumber(String phoneNumber) {
        return phoneNumber.substring(9, 13);
    }
}