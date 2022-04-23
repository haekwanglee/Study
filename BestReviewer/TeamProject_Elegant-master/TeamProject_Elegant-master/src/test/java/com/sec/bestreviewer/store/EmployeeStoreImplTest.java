package com.sec.bestreviewer.store;

import com.sec.bestreviewer.CommandParser;
import com.sec.bestreviewer.command.CommandDTO;
import com.sec.bestreviewer.command.DeleteCommand;
import com.sec.bestreviewer.command.ModifyCommand;
import com.sec.bestreviewer.command.SearchCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmployeeStoreImplTest {

    private static final String EMPLOYEE_NUMBER = "18050301";

    CommandDTO createCommandDTO(String commandLine) {
        CommandParser commandParser = new CommandParser();
        return commandParser.parseCommandDTO(commandLine);
    }

    private ModelParams getModifyCommandModelParams(String commandLine) {
        CommandDTO commandDTO = createCommandDTO(commandLine);
        ModifyCommand modifyCommand = new ModifyCommand(commandDTO);
        return modifyCommand.getModelParams();
    }

    private ModelParams getSearchCommandModelParams(String commandLine) {
        CommandDTO commandDTO = createCommandDTO(commandLine);
        SearchCommand searchCommand = new SearchCommand(commandDTO);
        return searchCommand.getModelParams();
    }

    private ModelParams getDeleteCommandModelParams(String commandLine) {
        CommandDTO commandDTO = createCommandDTO(commandLine);
        DeleteCommand deleteCommand = new DeleteCommand(commandDTO);
        return deleteCommand.getModelParams();
    }

    EmployeeStoreImpl store;

    void createEmployeePool() {
        store = new EmployeeStoreImpl();
        store.add(new Employee("17905165", "Angelina Lee", "CL4", "010-1234-4998", "20090618", "ADV"));
        store.add(new Employee("19172754", "Anybody Park", "CL2", "010-8211-3991", "20111113", "ADV"));
        store.add(new Employee("90456837", "Daniel Yoon", "CL3", "010-1234-3991", "20090618", "PRO"));
        store.add(new Employee("93201880", "Smith Yoon", "CL2", "010-1211-3991", "20160505", "ADV"));
        store.add(new Employee("10258522", "Leo Kim", "CL2", "010-2347-5678", "20190101", "EX"));
        store.add(new Employee("98599136", "Justin Kim", "CL1", "010-1234-4639", "19970423", "ADV"));
        store.add(new Employee("11067095", "Sunny Moon", "CL3", "010-7491-9246", "19970423", "PRO"));
        store.add(new Employee("90023138", "Anybody Lim", "CL1", "010-2347-8546", "19911214", "PRO"));
    }


    @Nested
    @DisplayName("SCH 명령에 대한 Model Logic Test")
    class SearchTester {
        @BeforeEach
        void setupSearchTester() {
            createEmployeePool();
        }

        @DisplayName("search() test")
        @ParameterizedTest
        @CsvSource({
                "' ',' ', employeeNum, 19172754, 1",
                "' ',' ', name, 'Anybody Park', 1",
                "' ',' ', cl, CL1, 2",
                "' ',' ', cl, CL2, 3",
                "' ',' ', cl, CL3, 2",
                "' ',' ', cl, CL4, 1",
                "' ',' ', phoneNum, 010-8211-3991, 1",
                "' ',' ', birthday, 19970423, 2",
                "' ',' ', certi, EX, 1",
                "' ',' ', certi, PRO, 3",
                "' ',' ', certi, ADV, 4",
        })
        void testSearchEmployee(String optionAttribute, String optionValue, String searchAttribute, String searchValue, int searchedEmployeesCount) {
            ModelParams modelParamsBefore = getSearchCommandModelParams(String.format("SCH, ,%s,%s,%s,%s", optionAttribute, optionValue, searchAttribute, searchValue));
            List<Employee> searchResult = store.delete(modelParamsBefore);
            assertEquals(searchedEmployeesCount, searchResult.size());
        }

        @DisplayName("search() with options test")
        @ParameterizedTest
        @CsvSource({
                "-f,, name, Anybody, 2",
                "-l,, name, Kim, 2",
                "-f,, name, Yoon, 0",
                "-l,-ge, name, Yoon, 2",
                "-l,-se, name, Yoon, 8",
                "-l,-s, name, Yoon, 6",
                "-l,-se, name, Yoon, 8",
                ",-g, cl, CL2, 3",
                ",-ge, cl, CL2, 6",
                ",-se, cl, CL2, 5",
                ",-s, cl, CL2, 2",
                "-m,, phoneNum, 8211, 1",
                "-l,, phoneNum, 3991, 3",
                "-l,-g, phoneNum, 3991, 5",
                "-l,-ge, phoneNum, 3991, 8",
                "-l,-se, phoneNum, 3991, 3",
                "-l,-s, phoneNum, 3991, 0",
                "-y,, birthday, 1997, 2",
                "-m,, birthday, 04, 2",
                "-m,, birthday, 07, 0",
                "-d,, birthday, 23, 2",
                "-d,, birthday, 31, 0",
                ",-g, certi, PRO, 1",
                ",-ge, certi, PRO, 4",
                ",-se, certi, PRO, 7",
                ",-s, certi, PRO, 4",
        })
        void testSearchEmployeeWithOptions(String optionAttribute, String optionValue, String searchAttribute, String searchValue, int searchedEmployeesCount) {
            if (optionAttribute == null)
                optionAttribute = " ";
            if (optionValue == null)
                optionValue = " ";
            ModelParams modelParamsBefore = getSearchCommandModelParams(String.format("SCH,,%s,%s,%s,%s", optionAttribute, optionValue, searchAttribute, searchValue));
            List<Employee> searchResult = store.search(modelParamsBefore);
            assertEquals(searchedEmployeesCount, searchResult.size());
        }
    }

    @Nested
    @DisplayName("DEL 명령에 대한 Model Logic Test")
    class DeleteTester {
        @BeforeEach
        void setupDeleteTester() {
            createEmployeePool();
        }

        @DisplayName("delete() test")
        @ParameterizedTest
        @CsvSource({
                "' ',' ', employeeNum, 19172754, 1, 7",
                "' ',' ', name, 'Anybody Park', 1, 7",
                "' ',' ', name, 'Nobody You', 0, 8",  // Not exist
                "' ',' ', cl, CL1, 2, 6",
                "' ',' ', cl, CL2, 3, 5",
                "' ',' ', cl, CL3, 2, 6",
                "' ',' ', cl, CL4, 1, 7",
                "' ',' ', phoneNum, 010-8211-3991, 1, 7",
                "' ',' ', phoneNum, 010-0000-0000, 0, 8",
                "' ',' ', birthday, 19970423, 2, 6",
                "' ',' ', birthday, 20001231, 0, 8",
                "' ',' ', certi, EX, 1, 7",
                "' ',' ', certi, PRO, 3, 5",
                "' ',' ', certi, ADV, 4, 4",
        })
        void testDeleteEmployee(String optionAttribute, String optionValue, String searchAttribute, String searchValue, int searchedEmployeesCount, int expectedEmplyeesCount) {
            ModelParams modelParamsBefore = getDeleteCommandModelParams(String.format("DEL,,%s,%s,%s,%s", optionAttribute, optionValue, searchAttribute, searchValue));
            List<Employee> deleteResult = store.delete(modelParamsBefore);
            assertEquals(searchedEmployeesCount, deleteResult.size());
            assertEquals(expectedEmplyeesCount, store.count());
        }

        @DisplayName("delete() with options test")
        @ParameterizedTest
        @CsvSource({
                "-f,, name, Anybody, 2, 6",
                "-l,, name, Kim, 2, 6",
                "-l,-g, name, Yoon, 0, 8",
                "-l,-ge, name, Yoon, 2, 6",
                "-l,-se, name, Yoon, 8, 0",
                "-l,-s, name, Yoon, 6, 2",
                "-l,-se, name, Yoon, 8, 0",
                ",-g, cl, CL2, 3, 5",
                ",-ge, cl, CL2, 6, 2",
                ",-se, cl, CL2, 5, 3",
                ",-s, cl, CL2, 2, 6",
                "-m,, phoneNum, 8211, 1, 7",
                "-l,, phoneNum, 3991, 3, 5",
                "-l,-g, phoneNum, 3991, 5, 3",
                "-l,-ge, phoneNum, 3991, 8, 0",
                "-l,-se, phoneNum, 3991, 3, 5",
                "-l,-s, phoneNum, 3991, 0, 8",
                "-y,, birthday, 1997, 2, 6",
                "-m,, birthday, 04, 2, 6",
                "-m,, birthday, 07, 0, 8",
                "-d,, birthday, 23, 2, 6",
                "-d,, birthday, 31, 0, 8",
                ",-g, certi, PRO, 1, 7",
                ",-ge, certi, PRO, 4, 4",
                ",-se, certi, PRO, 7, 1",
                ",-s, certi, PRO, 4, 4",
        })
        void testDeleteEmployeeWithOptions(String optionAttribute, String optionValue, String searchAttribute, String searchValue, int searchedEmployeesCount, int expectedEmplyeesCount) {
            if (optionAttribute == null)
                optionAttribute = " ";
            if (optionValue == null)
                optionValue = " ";
            ModelParams modelParamsBefore = getDeleteCommandModelParams(String.format("DEL,' ',%s,%s,%s,%s", optionAttribute, optionValue, searchAttribute, searchValue));
            List<Employee> deleteResult = store.delete(modelParamsBefore);
            assertEquals(searchedEmployeesCount, deleteResult.size());
            assertEquals(expectedEmplyeesCount, store.count());
        }
    }

    @Nested
    @DisplayName("MOD 명령에 대한 Model Logic Test")
    class ModifyTester {
        Employee employeeToModify;

        @BeforeEach
        void setupModifyTester() {
            createEmployeePool();
            employeeToModify = new Employee(EMPLOYEE_NUMBER, "Tom Boy", "CL1", "010-2234-5658", "20010101", "ADV");
        }

        @DisplayName("modify() test for 'name'")
        @ParameterizedTest
        @CsvSource({
                "'dh1022 kim', 'Donny Kim'",
                "'little prince', 'Little Prince'",
        })
        void testModifyNameValue(String beforeValue, String afterValue) {
            store.add(employeeToModify);
            employeeToModify.setName(beforeValue);
            // before modify() => before value
            ModelParams modelParamsBefore = getModifyCommandModelParams(String.format("MOD,,,,employeeNum,%s,%s,%s", EMPLOYEE_NUMBER, "name", afterValue));

            List<Employee> modifyResult = store.modify(modelParamsBefore);
            assertNotNull(modifyResult);
            for (Employee employee : modifyResult) {
                assertEquals(beforeValue, employee.getName());
            }
            // after modify() => after value : check by search()
            ModelParams modelParamsAfter = getSearchCommandModelParams(String.format("SCH,,,,employeeNum,%s", EMPLOYEE_NUMBER));
            List<Employee> resultSearch = store.search(modelParamsAfter);
            assertNotNull(resultSearch);
            for (Employee employee : resultSearch) {
                assertEquals(afterValue, employee.getName());
            }
        }

        @DisplayName("modify() test for 'careerLevel'")
        @ParameterizedTest
        @CsvSource({
                "CL1, CL2",
                "CL2, CL3",
                "CL3, CL4",
                "CL4, CL1",
        })
        void testModifyCareerLevelValue(String beforeValue, String afterValue) {
            store.add(employeeToModify);
            employeeToModify.setCareerLevel(beforeValue);

            // before modify() => before value
            ModelParams modelParamsBefore = getModifyCommandModelParams(String.format("MOD,,,,employeeNum,%s,%s,%s", EMPLOYEE_NUMBER, "cl", afterValue));
            List<Employee> modifyResult = store.modify(modelParamsBefore);
            assertNotNull(modifyResult);
            for (Employee employee : modifyResult) {
                assertEquals(beforeValue, employee.getCareerLevel());
            }
            // after modify() => after value : check by search()
            ModelParams modelParamsAfter = getSearchCommandModelParams(String.format("SCH,,,,employeeNum,%s", EMPLOYEE_NUMBER));
            List<Employee> resultSearch = store.search(modelParamsAfter);
            assertNotNull(resultSearch);
            for (Employee employee : resultSearch) {
                assertEquals(afterValue, employee.getCareerLevel());
            }
        }


        @DisplayName("modify() test for 'phoneNumber'")
        @ParameterizedTest
        @CsvSource({
                "010-1234-5678, 010-4567-1234",
                "010-1234-5678, 010-1234-5678",
        })
        void testModifyPhoneNumberValue(String beforeValue, String afterValue) {
            store.add(employeeToModify);
            employeeToModify.setPhoneNumber(beforeValue);

            // before modify() => before value
            ModelParams modelParamsBefore = getModifyCommandModelParams(String.format("MOD,,,,employeeNum,%s,%s,%s", EMPLOYEE_NUMBER, "phoneNum", afterValue));
            List<Employee> modifyResult = store.modify(modelParamsBefore);
            assertNotNull(modifyResult);
            for (Employee employee : modifyResult) {
                assertEquals(beforeValue, employee.getPhoneNumber());
            }
            // after modify() => after value : check by search()
            ModelParams modelParamsAfter = getSearchCommandModelParams(String.format("SCH,,,,employeeNum,%s", EMPLOYEE_NUMBER));
            List<Employee> resultSearch = store.search(modelParamsAfter);
            assertNotNull(resultSearch);
            for (Employee employee : resultSearch) {
                assertEquals(afterValue, employee.getPhoneNumber());
            }
        }


        @DisplayName("modify() test for 'birthday'")
        @ParameterizedTest
        @CsvSource({
                "19980901, 19980916",
                "20091106, 20101103",
                "19771231, 19771231",
        })
        void testModifyBirthdayValue(String beforeValue, String afterValue) {
            store.add(employeeToModify);
            employeeToModify.setBirthday(beforeValue);

            // before modify() => before value
            ModelParams modelParamsBefore = getModifyCommandModelParams(String.format("MOD,,,,employeeNum,%s,%s,%s", EMPLOYEE_NUMBER, "birthday", afterValue));
            List<Employee> modifyResult = store.modify(modelParamsBefore);
            assertNotNull(modifyResult);
            for (Employee employee : modifyResult) {
                assertEquals(beforeValue, employee.getBirthday());
            }
            // after modify() => after value : check by search()
            ModelParams modelParamsAfter = getSearchCommandModelParams(String.format("SCH,,,,employeeNum,%s", EMPLOYEE_NUMBER));
            List<Employee> resultSearch = store.search(modelParamsAfter);
            assertNotNull(resultSearch);
            for (Employee employee : resultSearch) {
                assertEquals(afterValue, employee.getBirthday());
            }
        }


        @DisplayName("modify() test for 'certi'")
        @ParameterizedTest
        @CsvSource({
                "ADV, PRO",
                "ADV, EX",
                "PRO, EX",
                "EX, ADV",
        })
        void testModifyCertiValue(String beforeValue, String afterValue) {
            store.add(employeeToModify);
            employeeToModify.setCerti(beforeValue);

            // before modify() => before value
            ModelParams modelParamsBefore = getModifyCommandModelParams(String.format("MOD,,,,employeeNum,%s,%s,%s", EMPLOYEE_NUMBER, "certi", afterValue));
            List<Employee> modifyResult = store.modify(modelParamsBefore);
            assertNotNull(modifyResult);
            for (Employee employee : modifyResult) {
                assertEquals(beforeValue, employee.getCerti());
            }
            // after modify() => after value : check by search()
            ModelParams modelParamsAfter = getSearchCommandModelParams(String.format("SCH,,,,employeeNum,%s", EMPLOYEE_NUMBER));
            List<Employee> resultSearch = store.search(modelParamsAfter);
            assertNotNull(resultSearch);
            for (Employee employee : resultSearch) {
                assertEquals(afterValue, employee.getCerti());
            }
        }

        @DisplayName("modify() test by model params")
        @ParameterizedTest
        @CsvSource({
                "' ',' ', name, 'Sunny Moon',certi,EX,1,2",
                "' ',' ', certi, ADV,cl,CL1,4,5",
        })
        void testModifyCondition(String optionAttribute, String optionValue, String searchAttribute, String searchValue, String updateAttribute, String updateValue,
                                 int beforeSearchCount, int afterSearchCount) {
            ModelParams modelParamsBefore = getModifyCommandModelParams(String.format("MOD,,%s,%s,%s,%s,%s,%s", optionAttribute, optionValue, searchAttribute, searchValue, updateAttribute, updateValue));
            List<Employee> updateResult = store.modify(modelParamsBefore);
            assertEquals(beforeSearchCount, updateResult.size());

            ModelParams modelParamsAfter = getSearchCommandModelParams(String.format("SCH,,,,%s,%s", updateAttribute, updateValue));
            List<Employee> searchResult = store.search(modelParamsAfter);
            assertEquals(afterSearchCount, searchResult.size());
        }
    }
}
