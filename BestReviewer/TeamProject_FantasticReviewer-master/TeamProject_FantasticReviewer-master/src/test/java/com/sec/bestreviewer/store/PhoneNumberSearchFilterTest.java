package com.sec.bestreviewer.store;

import com.sec.bestreviewer.base.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhoneNumberSearchFilterTest {
    @Test
    @DisplayName("PhoneNumber 로 Employee 검색 확인")
    void searchByPhoneNumberReturnEmployee() {
        // prepare
        EmployeeStore store = new EmployeeStoreImpl();
        Employee jack = createEmployeeJack();
        Employee tom = createEmployeeTom();

        store.add(jack);
        store.add(tom);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_PHONE_NUMBER,
                        "010-4837-5959",
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
        assertEquals("010-4837-5959", ret.get(0).getPhoneNumber());
    }

    @Test
    @DisplayName("MiddlePhoneNumber 로 Employee 검색 확인")
    void searchByMiddlePhoneNumberReturnEmployee() {
        // prepare
        EmployeeStore store = new EmployeeStoreImpl();
        Employee jack = createEmployeeJack();
        Employee tom = createEmployeeTom();

        store.add(jack);
        store.add(tom);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_PHONE_NUMBER,
                        "4837",
                        OperatorId.NONE,
                        OptionId.OPTION_PHONE_NUMBER_MIDDLE,
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
        assertEquals("010-4837-5959", ret.get(0).getPhoneNumber());
    }

    @Test
    @DisplayName("MiddlePhoneNumber, GreaterThan 으로  Employee 검색 확인")
    void searchByMiddlePhoneNumberGreaterThanReturnEmployee() {
        // prepare
        EmployeeStore store = new EmployeeStoreImpl();
        store.add(createEmployeeJack());
        store.add(createEmployeeTom());
        store.add(createEmployeePig());

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_PHONE_NUMBER,
                        "6666",
                        OperatorId.NONE,
                        OptionId.OPTION_PHONE_NUMBER_MIDDLE,
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
        assertEquals("010-7777-1324", ret.get(0).getPhoneNumber());
    }

    @Test
    @DisplayName("LastPhoneNumber 로 Employee 검색 확인")
    void searchByLastPhoneNumberReturnEmployee() {
        // prepare
        EmployeeStore store = new EmployeeStoreImpl();
        Employee jack = createEmployeeJack();
        Employee tom = createEmployeeTom();

        store.add(jack);
        store.add(tom);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_PHONE_NUMBER,
                        "5959",
                        OperatorId.NONE,
                        OptionId.OPTION_PHONE_NUMBER_LAST,
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
        assertEquals("010-4837-5959", ret.get(0).getPhoneNumber());
    }

    private EmployeeStore preparePhoneNumberWithSignTest() {
        EmployeeStore store = new EmployeeStoreImpl();
        Employee jack = createEmployeeJack();
        Employee tom = createEmployeeTom();
        Employee pig = createEmployeePig();
        store.add(jack);
        store.add(tom);
        store.add(pig);
        return store;
    }

    @Test
    @DisplayName("LastPhoneNumber, GreaterThan 로 Employee 검색 확인")
    void searchByLastPhoneNumberGreaterThanReturnEmployee() {
        EmployeeStore store = preparePhoneNumberWithSignTest();
        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_PHONE_NUMBER,
                        "5959",
                        OperatorId.NONE,
                        OptionId.OPTION_PHONE_NUMBER_LAST,
                        OptionId.OPTION_SIGN_GREATER_THAN
                ),
                null,
                null,
                OperatorId.NONE
        );
        List<Employee> ret = store.search(param);
        assertEquals(1, ret.size());
        assertEquals("010-6666-6716", ret.get(0).getPhoneNumber());
    }

    @Test
    @DisplayName("LastPhoneNumber, GreaterThanEqual 로 Employee 검색 확인")
    void searchByLastPhoneNumberGreaterThanEqualReturnEmployee() {
        EmployeeStore store = preparePhoneNumberWithSignTest();
        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_PHONE_NUMBER,
                        "5959",
                        OperatorId.NONE,
                        OptionId.OPTION_PHONE_NUMBER_LAST,
                        OptionId.OPTION_SIGN_GREATER_THAN_OR_EQUAL_TO
                ),
                null,
                null,
                OperatorId.NONE
        );
        List<Employee> ret = store.search(param);
        assertEquals(2, ret.size());
        assertEquals("010-4837-5959", ret.get(0).getPhoneNumber());
        assertEquals("010-6666-6716", ret.get(1).getPhoneNumber());
    }

    @Test
    @DisplayName("LastPhoneNumber, SmallerThan 로 Employee 검색 확인")
    void searchByLastPhoneNumberSmallerThanReturnEmployee() {
        EmployeeStore store = preparePhoneNumberWithSignTest();
        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_PHONE_NUMBER,
                        "5959",
                        OperatorId.NONE,
                        OptionId.OPTION_PHONE_NUMBER_LAST,
                        OptionId.OPTION_SIGN_SMALLER_THAN
                ),
                null,
                null,
                OperatorId.NONE
        );
        List<Employee> ret = store.search(param);
        assertEquals(1, ret.size());
        assertEquals("010-7777-1324", ret.get(0).getPhoneNumber());
    }

    @Test
    @DisplayName("LastPhoneNumber, SmallerThanEqual 로 Employee 검색 확인")
    void searchByLastPhoneNumberSmallerThanEqualReturnEmployee() {
        EmployeeStore store = preparePhoneNumberWithSignTest();
        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_PHONE_NUMBER,
                        "5959",
                        OperatorId.NONE,
                        OptionId.OPTION_PHONE_NUMBER_LAST,
                        OptionId.OPTION_SIGN_SMALLER_THAN_OR_EQUAL_TO
                ),
                null,
                null,
                OperatorId.NONE
        );
        List<Employee> ret = store.search(param);
        assertEquals(2, ret.size());
        assertEquals("010-7777-1324", ret.get(0).getPhoneNumber());
        assertEquals("010-4837-5959", ret.get(1).getPhoneNumber());
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

    private Employee createEmployeeTom () {
        return new Employee.Builder()
                .setEmployeeNumber("17530481")
                .setName("TOM TIM")
                .setCareerLevel("CL2")
                .setPhoneNumber("010-4837-5959")
                .setBirthday("20010601")
                .setCerti("PRO")
                .build();
    }

    private Employee createEmployeePig () {
        return new Employee.Builder()
                .setEmployeeNumber("17324123")
                .setName("PIG TIM")
                .setCareerLevel("CL3")
                .setPhoneNumber("010-7777-1324")
                .setBirthday("20040101")
                .setCerti("EX")
                .build();
    }
}
