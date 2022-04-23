package com.sec.bestreviewer.store.searchfilter;

import com.sec.bestreviewer.base.*;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.test.CommandGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CareerLevelFilterTest {
    private List<Employee> employees = new CommandGenerator().generateEmployees(5);

    @Test
    @DisplayName("CL Employee 검색")
    void searchByClTest() {
        employees.get(0).setCareerLevel("CL2");
        employees.get(1).setCareerLevel("CL1");
        employees.get(2).setCareerLevel("CL3");
        employees.get(3).setCareerLevel("CL3");
        employees.get(4).setCareerLevel("CL4");


        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_CAREER_LEVEL,
                        "CL3",
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_NONE
                ),
                null,
                null,
                OperatorId.NONE
        );

        List<Employee> ret = new CareerLevelFilter().search(employees, param);

        assertEquals(2, ret.size());
    }

    @Test
    @DisplayName("CL grater than Employee 검색")
    void searchByClGraterThanTest() {
        employees.get(0).setCareerLevel("CL2");
        employees.get(1).setCareerLevel("CL1");
        employees.get(2).setCareerLevel("CL3");
        employees.get(3).setCareerLevel("CL3");
        employees.get(4).setCareerLevel("CL4");

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_CAREER_LEVEL,
                        "CL3",
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_SIGN_GREATER_THAN
                ),
                null,
                null,
                OperatorId.NONE
        );

        List<Employee> ret = new CareerLevelFilter().search(employees, param);

        assertEquals(1, ret.size());
    }

    @Test
    @DisplayName("CL grater than equal Employee 검색")
    void searchByClGreaterThanEqualTest() {
        employees.get(0).setCareerLevel("CL2");
        employees.get(1).setCareerLevel("CL1");
        employees.get(2).setCareerLevel("CL3");
        employees.get(3).setCareerLevel("CL3");
        employees.get(4).setCareerLevel("CL4");

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_CAREER_LEVEL,
                        "CL3",
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_SIGN_GREATER_THAN_OR_EQUAL_TO
                ),
                null,
                null,
                OperatorId.NONE
        );

        List<Employee> ret = new CareerLevelFilter().search(employees, param);

        assertEquals(3, ret.size());
    }

    @Test
    @DisplayName("CL smalller than 가진 Employee 검색")
    void searchByClsmallerThanTest() {
        employees.get(0).setCareerLevel("CL2");
        employees.get(1).setCareerLevel("CL1");
        employees.get(2).setCareerLevel("CL3");
        employees.get(3).setCareerLevel("CL3");
        employees.get(4).setCareerLevel("CL4");

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_CAREER_LEVEL,
                        "CL3",
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_SIGN_SMALLER_THAN
                ),
                null,
                null,
                OperatorId.NONE
        );

        List<Employee> ret = new CareerLevelFilter().search(employees, param);

        assertEquals(2, ret.size());
    }

    @Test
    @DisplayName("CL smalller than eqaul 가진 Employee 검색")
    void searchByClsmallerThanEqaulTest() {
        employees.get(0).setCareerLevel("CL2");
        employees.get(1).setCareerLevel("CL1");
        employees.get(2).setCareerLevel("CL3");
        employees.get(3).setCareerLevel("CL3");
        employees.get(4).setCareerLevel("CL4");

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_CAREER_LEVEL,
                        "CL3",
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_SIGN_SMALLER_THAN_OR_EQUAL_TO
                ),
                null,
                null,
                OperatorId.NONE
        );

        List<Employee> ret = new CareerLevelFilter().search(employees, param);

        assertEquals(4, ret.size());
    }
}