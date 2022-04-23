package com.sec.bestreviewer.store;

import com.sec.bestreviewer.base.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NameSearchFilterTest {

    private final EmployeeStore store = new EmployeeStoreImpl();
    private final Employee jack = createEmployeeJack();
    private final Employee tom = createEmployeeTom();
    private final Employee jackSeol = createEmployeeJackSeol();
    private final Employee pig = createEmployeePig();
    private final Employee pig2 = createEmployeePig2();
    private final Employee pigTime = createEmployeePigTime();

    @Test
    @DisplayName("Full Name 으로 Employee 검색 확인")
    void searchByNameReturnEmployee() {
        store.add(jack);
        store.add(tom);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_NAME,
                        "TOM TIM",
                        OperatorId.NONE,
                        OperatorId.NONE,
                        OperatorId.NONE
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(1, ret.size());
        assertEquals("17530481", ret.get(0).getEmployeeNumber());
        assertEquals("TOM TIM", ret.get(0).getName());
    }

    @Test
    @DisplayName("Full Name 으로 Grater Than Employee 검색 확인")
    void searchByNameGreaterThanReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(jackSeol);
        store.add(pig);
        store.add(pig2);
        store.add(pigTime);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_NAME,
                        "PIG TIM",
                        OperatorId.NONE,
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_SIGN_GREATER_THAN
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(2, ret.size());
    }

    @Test
    @DisplayName("Full Name 으로 Grater Than Equal Employee 검색 확인")
    void searchByNameGreaterThanEqualsReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(jackSeol);
        store.add(pig);
        store.add(pig2);
        store.add(pigTime);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_NAME,
                        "PIG TIM",
                        OperatorId.NONE,
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_SIGN_GREATER_THAN_OR_EQUAL_TO
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(4, ret.size());
    }

    @Test
    @DisplayName("Full Name 으로 Smaller Than Employee 검색 확인")
    void searchByNameSmallerThanReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(jackSeol);
        store.add(pig);
        store.add(pig2);
        store.add(pigTime);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_NAME,
                        "PIG TIM",
                        OperatorId.NONE,
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_SIGN_SMALLER_THAN
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(2, ret.size());
    }

    @Test
    @DisplayName("Full Name 으로 Smaller Than Equal Employee 검색 확인")
    void searchByNameSmallerThanEqualReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(jackSeol);
        store.add(pig);
        store.add(pig2);
        store.add(pigTime);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_NAME,
                        "PIG TIM",
                        OperatorId.NONE,
                        OptionId.OPTION_NONE,
                        OptionId.OPTION_SIGN_SMALLER_THAN_OR_EQUAL_TO
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(4, ret.size());
    }

    @Test
    @DisplayName("First Name 으로 Employee 검색 확인")
    void searchByFirstNameReturnEmployee() {
        store.add(jack);
        store.add(tom);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_NAME,
                        "TOM",
                        OperatorId.NONE,
                        OptionId.OPTION_NAME_FIRST,
                        OperatorId.NONE
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(1, ret.size());
        assertEquals("17530481", ret.get(0).getEmployeeNumber());
        assertEquals("TOM TIM", ret.get(0).getName());
    }

    @Test
    @DisplayName("First Name 으로 Grater Than Employee 검색 확인")
    void searchByFirstNameGreaterThanReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(jackSeol);
        store.add(pig);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_NAME,
                        "JACK",
                        OperatorId.NONE,
                        OptionId.OPTION_NAME_FIRST,
                        OptionId.OPTION_SIGN_GREATER_THAN
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(2, ret.size());
    }

    @Test
    @DisplayName("First Name 으로 Grater Than Equal Employee 검색 확인")
    void searchByFirstNameGreaterThanEqualsReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(jackSeol);
        store.add(pig);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_NAME,
                        "JACK",
                        OperatorId.NONE,
                        OptionId.OPTION_NAME_FIRST,
                        OptionId.OPTION_SIGN_GREATER_THAN_OR_EQUAL_TO
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(4, ret.size());
    }

    @Test
    @DisplayName("First Name 으로 Smaller Than Employee 검색 확인")
    void searchByFirstNameSmallerThanReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(jackSeol);
        store.add(pig);
        store.add(pigTime);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_NAME,
                        "PIG",
                        OperatorId.NONE,
                        OptionId.OPTION_NAME_FIRST,
                        OptionId.OPTION_SIGN_SMALLER_THAN
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(2, ret.size());
    }

    @Test
    @DisplayName("First Name 으로 Smaller Than Equal Employee 검색 확인")
    void searchByFirstNameSmallerThanEqualReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(jackSeol);
        store.add(pig);
        store.add(pigTime);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_NAME,
                        "PIG",
                        OperatorId.NONE,
                        OptionId.OPTION_NAME_FIRST,
                        OptionId.OPTION_SIGN_SMALLER_THAN_OR_EQUAL_TO
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(4, ret.size());
    }

    @Test
    @DisplayName("Last Name 으로 Employee 검색 확인")
    void searchByLastNameReturnEmployee() {
        store.add(jack);
        store.add(tom);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_NAME,
                        "TIM",
                        OperatorId.NONE,
                        OptionId.OPTION_NAME_LAST,
                        OperatorId.NONE
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(1, ret.size());
        assertEquals("17530481", ret.get(0).getEmployeeNumber());
        assertEquals("TOM TIM", ret.get(0).getName());
    }

    @Test
    @DisplayName("Last Name 으로 Grater Than Employee 검색 확인")
    void searchByLastNameGreaterThanReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(jackSeol);
        store.add(pig);
        store.add(pigTime);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_NAME,
                        "TIM",
                        OperatorId.NONE,
                        OptionId.OPTION_NAME_LAST,
                        OptionId.OPTION_SIGN_GREATER_THAN
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(1, ret.size());
    }

    @Test
    @DisplayName("Last Name 으로 Grater Than Equal Employee 검색 확인")
    void searchByLastNameGreaterThanEqualsReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(jackSeol);
        store.add(pig);
        store.add(pigTime);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_NAME,
                        "TIM",
                        OperatorId.NONE,
                        OptionId.OPTION_NAME_LAST,
                        OptionId.OPTION_SIGN_GREATER_THAN_OR_EQUAL_TO
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(3, ret.size());
        assertEquals("17324123", ret.get(0).getEmployeeNumber());
        assertEquals("17324166", ret.get(1).getEmployeeNumber());
        assertEquals("17530481", ret.get(2).getEmployeeNumber());
    }

    @Test
    @DisplayName("Last Name 으로 Smaller Than Employee 검색 확인")
    void searchByLastNameSmallerThanReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(jackSeol);
        store.add(pig);
        store.add(pigTime);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_NAME,
                        "TIM",
                        OperatorId.NONE,
                        OptionId.OPTION_NAME_LAST,
                        OptionId.OPTION_SIGN_SMALLER_THAN
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(2, ret.size());
        assertEquals("17843022", ret.get(0).getEmployeeNumber());
        assertEquals("17843077", ret.get(1).getEmployeeNumber());
    }

    @Test
    @DisplayName("Last Name 으로 Smaller Than Equal Employee 검색 확인")
    void searchByLastNameSmallerThanEqualReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(jackSeol);
        store.add(pig);
        store.add(pigTime);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_NAME,
                        "TIM",
                        OperatorId.NONE,
                        OptionId.OPTION_NAME_LAST,
                        OptionId.OPTION_SIGN_SMALLER_THAN_OR_EQUAL_TO
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(4, ret.size());
        assertEquals("17324123", ret.get(0).getEmployeeNumber());
        assertEquals("17530481", ret.get(1).getEmployeeNumber());
        assertEquals("17843022", ret.get(2).getEmployeeNumber());
        assertEquals("17843077", ret.get(3).getEmployeeNumber());
    }

    private Employee createEmployeeJack() {
        return new Employee.Builder()
                .setEmployeeNumber("17843022")
                .setName("JACK SEO")
                .setCareerLevel("CL3")
                .setPhoneNumber("010-6666-6716")
                .setBirthday("19810630")
                .setCerti("ADV")
                .build();
    }

    private Employee createEmployeeJackSeol() {
        return new Employee.Builder()
                .setEmployeeNumber("17843077")
                .setName("JACK SEOL")
                .setCareerLevel("CL3")
                .setPhoneNumber("010-5555-6716")
                .setBirthday("19810731")
                .setCerti("ADV")
                .build();
    }

    private Employee createEmployeeTom() {
        return new Employee.Builder()
                .setEmployeeNumber("17530481")
                .setName("TOM TIM")
                .setCareerLevel("CL2")
                .setPhoneNumber("010-4837-5959")
                .setBirthday("20010601")
                .setCerti("PRO")
                .build();
    }

    private Employee createEmployeePig() {
        return new Employee.Builder()
                .setEmployeeNumber("17324123")
                .setName("PIG TIM")
                .setCareerLevel("CL3")
                .setPhoneNumber("010-7777-1324")
                .setBirthday("20040101")
                .setCerti("EX")
                .build();
    }

    private Employee createEmployeePig2() {
        return new Employee.Builder()
                .setEmployeeNumber("17324100")
                .setName("PIG TIM")
                .setCareerLevel("CL3")
                .setPhoneNumber("010-7777-1324")
                .setBirthday("20040101")
                .setCerti("EX")
                .build();
    }

    private Employee createEmployeePigTime() {
        return new Employee.Builder()
                .setEmployeeNumber("17324166")
                .setName("PIG TIME")
                .setCareerLevel("CL3")
                .setPhoneNumber("010-5555-1324")
                .setBirthday("20040101")
                .setCerti("EX")
                .build();
    }
}