package com.sec.bestreviewer.store.searchfilter;

import com.sec.bestreviewer.base.*;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.test.CommandGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CertiFilterTest {

    @Test
    @DisplayName("써티 ADV 필드 값을 가진 Employee 검색")
    void searchByCertiAdvReturnExpected() {

        // prepare
        final List<Employee> employees = new CommandGenerator().generateEmployees(5);
        employees.get(0).setCerti("PRO");
        employees.get(1).setCerti("ADV");
        employees.get(2).setCerti("EX");
        employees.get(3).setCerti("ADV");
        employees.get(4).setCerti("PRO");

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_CERTI,
                        "ADV",
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_NONE
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = new CertiFilter().search(employees, param);


        // validate
        assertEquals(2, ret.size());
    }

    @Test
    @DisplayName("써티 PRO 이하 필드 값을 가진 Employee 검색")
    void searchByCertiProSmallerThanEqualToReturnExpected() {

        // prepare
        final List<Employee> employees = new CommandGenerator().generateEmployees(5);
        employees.get(0).setCerti("PRO");
        employees.get(1).setCerti("ADV");
        employees.get(2).setCerti("EX");
        employees.get(3).setCerti("ADV");
        employees.get(4).setCerti("PRO");

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_CERTI,
                        "PRO",
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_SIGN_SMALLER_THAN_OR_EQUAL_TO
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = new CertiFilter().search(employees, param);


        // validate
        assertEquals(4, ret.size());
    }

    @Test
    @DisplayName("써티 PRO 초과 필드 값을 가진 Employee 검색")
    void searchByCertiExGreaterThanReturnExpected() {

        // prepare
        final List<Employee> employees = new CommandGenerator().generateEmployees(5);
        employees.get(0).setCerti("PRO");
        employees.get(1).setCerti("ADV");
        employees.get(2).setCerti("EX");
        employees.get(3).setCerti("ADV");
        employees.get(4).setCerti("PRO");

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_CERTI,
                        "PRO",
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_SIGN_GREATER_THAN
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = new CertiFilter().search(employees, param);


        // validate
        assertEquals(1, ret.size());
    }

    @Test
    @DisplayName("써티 ADV 이상 필드 값을 가진 Employee 검색")
    void searchByCertiAdvGreaterThanEqualsToReturnExpected() {

        // prepare
        final List<Employee> employees = new CommandGenerator().generateEmployees(5);
        employees.get(0).setCerti("PRO");
        employees.get(1).setCerti("ADV");
        employees.get(2).setCerti("ADV");
        employees.get(3).setCerti("ADV");
        employees.get(4).setCerti("PRO");

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_CERTI,
                        "ADV",
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_SIGN_GREATER_THAN_OR_EQUAL_TO
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = new CertiFilter().search(employees, param);


        // validate
        assertEquals(5, ret.size());
    }


    @Test
    @DisplayName("써티 PRO 미만 필드 값을 가진 Employee 검색")
    void searchByCertiProSmallerThanReturnExpected() {

        // prepare
        final List<Employee> employees = new CommandGenerator().generateEmployees(5);
        employees.get(0).setCerti("PRO");
        employees.get(1).setCerti("ADV");
        employees.get(2).setCerti("ADV");
        employees.get(3).setCerti("ADV");
        employees.get(4).setCerti("PRO");

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_CERTI,
                        "PRO",
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_SIGN_SMALLER_THAN
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = new CertiFilter().search(employees, param);


        // validate
        assertEquals(3, ret.size());
    }
}