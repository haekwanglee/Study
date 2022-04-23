package com.sec.bestreviewer.store;

import com.sec.bestreviewer.base.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BirthdaySearchFilterTest {
    private EmployeeStore store = new EmployeeStoreImpl();
    private Employee jack = createEmployeeJack();
    private Employee tom = createEmployeeTom();
    private Employee linda = createEmployeeLinda();
    private Employee jane = createEmployeeJane();

    @Test
    @DisplayName("Birthday 로 Employee 검색 확인")
    void searchByBirthdayReturnEmployee() {
        store.add(jack);
        store.add(tom);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "20010608",
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
    @DisplayName("Birthday 로 Grater than Employee 검색 확인")
    void searchByBirthdayGraterThanReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(linda);
        store.add(jane);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "20010601",
                        OperatorId.NONE,
                        OperatorId.NONE,
                        OptionId.OPTION_SIGN_GREATER_THAN
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(3, ret.size());
        assertEquals("17530481", ret.get(0).getEmployeeNumber());
        assertEquals("17530482", ret.get(1).getEmployeeNumber());
        assertEquals("17843021", ret.get(2).getEmployeeNumber());
    }

    @Test
    @DisplayName("Birthday 로 Grater than equal Employee 검색 확인")
    void searchByBirthdayGraterThanEqualReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(linda);
        store.add(jane);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "20010601",
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
        assertEquals(3, ret.size());
        assertEquals("17530481", ret.get(0).getEmployeeNumber());
        assertEquals("17530482", ret.get(1).getEmployeeNumber());
        assertEquals("17843021", ret.get(2).getEmployeeNumber());
    }

    @Test
    @DisplayName("Birthday 로 smaller than Employee 검색 확인")
    void searchByBirthdaySamllerThanReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(linda);
        store.add(jane);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "20010608",
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
        assertEquals(1, ret.size());
        assertEquals("17843022", ret.get(0).getEmployeeNumber());
    }

    @Test
    @DisplayName("Birthday 로 smaller than eqaul Employee 검색 확인")
    void searchByBirthdaySmallerThanEqualReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(linda);
        store.add(jane);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "20010608",
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
        assertEquals(3, ret.size());
        assertEquals("17530481", ret.get(0).getEmployeeNumber());
        assertEquals("17843021", ret.get(1).getEmployeeNumber());
        assertEquals("17843022", ret.get(2).getEmployeeNumber());
    }

    @Test
    @DisplayName("Year 로 Employee 검색 확인")
    void searchByBirthdayYearReturnEmployee() {
        // prepare
        EmployeeStore store = new EmployeeStoreImpl();
        Employee jack = createEmployeeJack();
        Employee tom = createEmployeeTom();

        store.add(jack);
        store.add(tom);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "2001",
                        OperatorId.NONE,
                        OptionId.OPTION_BIRTHDAY_YEAR,
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
    @DisplayName("Birthday Year로 Grater than Employee 검색 확인")
    void searchByBirthdayYearGraterThanReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(linda);
        store.add(jane);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "2001",
                        OperatorId.NONE,
                        OptionId.OPTION_BIRTHDAY_YEAR,
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
        assertEquals("17530482", ret.get(0).getEmployeeNumber());
    }

    @Test
    @DisplayName("Birthday Year로 Grater than equal Employee 검색 확인")
    void searchByBirthdayYearGraterThanEqualReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(linda);
        store.add(jane);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "2001",
                        OperatorId.NONE,
                        OptionId.OPTION_BIRTHDAY_YEAR,
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
        assertEquals("17530481", ret.get(0).getEmployeeNumber());
        assertEquals("17530482", ret.get(1).getEmployeeNumber());
        assertEquals("17843021", ret.get(2).getEmployeeNumber());
    }

    @Test
    @DisplayName("Birthday Year로 smaller than Employee 검색 확인")
    void searchByBirthdayYearSamllerThanReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(linda);
        store.add(jane);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "2001",
                        OperatorId.NONE,
                        OptionId.OPTION_BIRTHDAY_YEAR,
                        OptionId.OPTION_SIGN_SMALLER_THAN
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(1, ret.size());
        assertEquals("17843022", ret.get(0).getEmployeeNumber());
    }

    @Test
    @DisplayName("Birthday Year로 smaller than eqaul Employee 검색 확인")
    void searchByBirthdayYearSmallerThanEqualReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(linda);
        store.add(jane);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "2001",
                        OperatorId.NONE,
                        OptionId.OPTION_BIRTHDAY_YEAR,
                        OptionId.OPTION_SIGN_SMALLER_THAN_OR_EQUAL_TO
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(3, ret.size());
        assertEquals("17530481", ret.get(0).getEmployeeNumber());
        assertEquals("17843021", ret.get(1).getEmployeeNumber());
        assertEquals("17843022", ret.get(2).getEmployeeNumber());
    }

    @Test
    @DisplayName("Month 로 Employee 검색 확인")
    void searchByBirthdayMonthReturnEmployee() {
        store.add(tom);
        store.add(linda);
        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "06",
                        OperatorId.NONE,
                        OptionId.OPTION_BIRTHDAY_MONTH,
                        OperatorId.NONE
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
    @DisplayName("Birthday Month로 Grater than Employee 검색 확인")
    void searchByBirthdayMonthGraterThanReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(linda);
        store.add(jane);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "06",
                        OperatorId.NONE,
                        OptionId.OPTION_BIRTHDAY_MONTH,
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
        assertEquals("17530482", ret.get(0).getEmployeeNumber());
    }

    @Test
    @DisplayName("Birthday Month로 Grater than equal Employee 검색 확인")
    void searchByBirthdayMonthGraterThanEqualReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(linda);
        store.add(jane);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "06",
                        OperatorId.NONE,
                        OptionId.OPTION_BIRTHDAY_MONTH,
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
        assertEquals("17530481", ret.get(0).getEmployeeNumber());
        assertEquals("17530482", ret.get(1).getEmployeeNumber());
        assertEquals("17843021", ret.get(2).getEmployeeNumber());
    }

    @Test
    @DisplayName("Birthday Month로 smaller than Employee 검색 확인")
    void searchByBirthdayMonthSamllerThanReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(linda);
        store.add(jane);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "06",
                        OperatorId.NONE,
                        OptionId.OPTION_BIRTHDAY_MONTH,
                        OptionId.OPTION_SIGN_SMALLER_THAN
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(1, ret.size());
        assertEquals("17843022", ret.get(0).getEmployeeNumber());
    }

    @Test
    @DisplayName("Birthday Month로 smaller than eqaul Employee 검색 확인")
    void searchByBirthdayMonthSmallerThanEqualReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(linda);
        store.add(jane);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "06",
                        OperatorId.NONE,
                        OptionId.OPTION_BIRTHDAY_MONTH,
                        OptionId.OPTION_SIGN_SMALLER_THAN_OR_EQUAL_TO
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(3, ret.size());
        assertEquals("17530481", ret.get(0).getEmployeeNumber());
        assertEquals("17843021", ret.get(1).getEmployeeNumber());
        assertEquals("17843022", ret.get(2).getEmployeeNumber());
    }

    @Test
    @DisplayName("Day 로 Employee 검색 확인")
    void searchByBirthdayDayReturnEmployee() {
        // prepare
        EmployeeStore store = new EmployeeStoreImpl();
        Employee jack = createEmployeeJack();
        Employee tom = createEmployeeTom();

        store.add(jack);
        store.add(tom);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "02",
                        OperatorId.NONE,
                        OptionId.OPTION_BIRTHDAY_DAY,
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
        assertEquals("17843022", ret.get(0).getEmployeeNumber());
        assertEquals("010-6666-6716", ret.get(0).getPhoneNumber());
    }

    @Test
    @DisplayName("Birthday Day로 Grater than Employee 검색 확인")
    void searchByBirthdayDayGraterThanReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(linda);
        store.add(jane);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "08",
                        OperatorId.NONE,
                        OptionId.OPTION_BIRTHDAY_DAY,
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
        assertEquals("17530482", ret.get(0).getEmployeeNumber());
    }

    @Test
    @DisplayName("Birthday Day로 Grater than equal Employee 검색 확인")
    void searchByBirthdayDayGraterThanEqualReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(linda);
        store.add(jane);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "08",
                        OperatorId.NONE,
                        OptionId.OPTION_BIRTHDAY_DAY,
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
        assertEquals("17530481", ret.get(0).getEmployeeNumber());
        assertEquals("17530482", ret.get(1).getEmployeeNumber());
        assertEquals("17843021", ret.get(2).getEmployeeNumber());
    }

    @Test
    @DisplayName("Birthday Day로 smaller than Employee 검색 확인")
    void searchByBirthdayDaySamllerThanReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(linda);
        store.add(jane);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "08",
                        OperatorId.NONE,
                        OptionId.OPTION_BIRTHDAY_DAY,
                        OptionId.OPTION_SIGN_SMALLER_THAN
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(1, ret.size());
        assertEquals("17843022", ret.get(0).getEmployeeNumber());
    }

    @Test
    @DisplayName("Birthday Day로 smaller than eqaul Employee 검색 확인")
    void searchByBirthdayDaySmallerThanEqualReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(linda);
        store.add(jane);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "08",
                        OperatorId.NONE,
                        OptionId.OPTION_BIRTHDAY_DAY,
                        OptionId.OPTION_SIGN_SMALLER_THAN_OR_EQUAL_TO
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(3, ret.size());
        assertEquals("17530481", ret.get(0).getEmployeeNumber());
        assertEquals("17843021", ret.get(1).getEmployeeNumber());
        assertEquals("17843022", ret.get(2).getEmployeeNumber());
    }

    @Test
    @DisplayName("Birthday 없는 day로 Employee 검색 확인")
    void searchByBirthdayDayNothingReturnEmployee() {
        store.add(jack);
        store.add(tom);
        store.add(linda);
        store.add(jane);

        ConditionParameter param = new ConditionParameter(
                new ConditionValue(
                        FieldId.FIELD_BIRTH_DAY,
                        "06",
                        OperatorId.NONE,
                        OptionId.OPTION_BIRTHDAY_DAY,
                        OptionId.OPTION_SIGN_SMALLER_THAN_OR_EQUAL_TO
                ),
                null,
                null,
                OperatorId.NONE
        );

        // invoke
        List<Employee> ret = store.search(param);

        // validate
        assertEquals(1, ret.size());
        assertEquals("17843022", ret.get(0).getEmployeeNumber());
    }

    private Employee createEmployeeJack() {
        return new Employee.Builder()
                .setEmployeeNumber("17843022")
                .setName("JACK SEO")
                .setCareerLevel("CL3")
                .setPhoneNumber("010-6666-6716")
                .setBirthday("19810302")
                .setCerti("ADV")
                .build();
    }

    private Employee createEmployeeTom () {
        return new Employee.Builder()
                .setEmployeeNumber("17530481")
                .setName("TOM TIM")
                .setCareerLevel("CL2")
                .setPhoneNumber("010-4837-5959")
                .setBirthday("20010608")
                .setCerti("PRO")
                .build();
    }

    private Employee createEmployeeLinda () {
        return new Employee.Builder()
                .setEmployeeNumber("17843021")
                .setName("LINDA SEO")
                .setCareerLevel("CL3")
                .setPhoneNumber("010-6666-6716")
                .setBirthday("20010608")
                .setCerti("ADV")
                .build();
    }

    private Employee createEmployeeJane () {
        return new Employee.Builder()
                .setEmployeeNumber("17530482")
                .setName("JANE TIM")
                .setCareerLevel("CL2")
                .setPhoneNumber("010-4837-5959")
                .setBirthday("20021010")
                .setCerti("PRO")
                .build();
    }
}
