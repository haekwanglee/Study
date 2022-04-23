package com.sec.bestreviewer.store;

import com.sec.bestreviewer.base.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeStoreImplTest {
    @Nested
    @DisplayName("When new ")
    class AndOrTest {
        @Test
        @DisplayName("PhoneNumber and Name 으로  Employee 검색 확인")
        void searchByPhoneNumberAndNameReturnEmployee() {
            // prepare
            EmployeeStore store = new EmployeeStoreImpl();
            store.add(createEmployeeJack());
            store.add(createEmployeeTom());
            store.add(createEmployeePig()); // "010-7777-1324"
            store.add(createEmployeeHugo()); // "010-7777-1324"

            ConditionParameter param = new ConditionParameter(
                    new ConditionValue(
                            FieldId.FIELD_PHONE_NUMBER,
                            "010-7777-1324",
                            OptionId.OPTION_NONE,
                            OptionId.OPTION_NONE,
                            OptionId.OPTION_NONE
                    ),
                    new ConditionValue(
                            FieldId.FIELD_NAME,
                            "HUGO TIM",
                            OptionId.OPTION_NONE,
                            OptionId.OPTION_NONE,
                            OptionId.OPTION_NONE
                    ),
                    null,
                    OperatorId.AND
            );

            // invoke
            List<Employee> ret = store.search(param);

            // validate
            assertEquals(1, ret.size());
            assertEquals("010-7777-1324", ret.get(0).getPhoneNumber());
            assertEquals("HUGO TIM", ret.get(0).getName());
        }

        @Test
        @DisplayName("PhoneNumber or Name 으로  Employee 검색 확인")
        void searchByPhoneNumberOrNameReturnEmployee() {
            // prepare
            EmployeeStore store = new EmployeeStoreImpl();
            store.add(createEmployeeJack());
            store.add(createEmployeeTom()); // 010-4837-5959
            store.add(createEmployeePig());
            store.add(createEmployeeHugo());

            ConditionParameter param = new ConditionParameter(
                    new ConditionValue(
                            FieldId.FIELD_PHONE_NUMBER,
                            "010-4837-5959",
                            OptionId.OPTION_NONE,
                            OptionId.OPTION_NONE,
                            OptionId.OPTION_NONE
                    ),
                    new ConditionValue(
                            FieldId.FIELD_NAME,
                            "HUGO TIM",
                            OptionId.OPTION_NONE,
                            OptionId.OPTION_NONE,
                            OptionId.OPTION_NONE
                    ),
                    null,
                    OperatorId.OR
            );

            // invoke
            List<Employee> ret = store.search(param);

            // validate
            assertEquals(2, ret.size());
            assertEquals("17323241", ret.get(0).getEmployeeNumber());
            assertEquals("17530481", ret.get(1).getEmployeeNumber());
        }
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

        private Employee createEmployeeHugo () {
            return new Employee.Builder()
                    .setEmployeeNumber("17323241")
                    .setName("HUGO TIM")
                    .setCareerLevel("CL3")
                    .setPhoneNumber("010-7777-1324")
                    .setBirthday("20040101")
                    .setCerti("EX")
                    .build();
        }
    }