package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.store.Injection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThirdOptionTest {

    private final EmployeeStore employeeStore = Injection.provideEmployeeStore();
    private CommandExecutor executor;

    @BeforeEach
    void setUp() {
        CommandExecutorTestUtil.addEmployees(employeeStore);
        executor = new CommandExecutor(employeeStore);
    }

    @DisplayName("-g: 조건 값보다 클 경우 (또는 사전 순으로 더 뒤일 경우)")
    @Nested
    class SearchCommandGreaterThanTest {

        final String equalityOption = "-g";

        @DisplayName("PhoneNum이 조건 값보다 더 큰 record를 검색: 010 무시하고 검색")
        @ParameterizedTest
        @CsvSource({"'010-4108-4007', '11'", "'010-7654-7654', '4'"})
        public void testThirdOptionWithFullPhoneNum(String cl, String expected) {
            List<String> options = Arrays.asList(equalityOption);
            List<String> params = Arrays.asList(EmployeeStore.FIELD_PHONE_NUMBER, cl);
            Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
            List<String> resList = executor.execute(command);
            String actual = resList.get(0).split(",")[1];
            assertEquals(expected, actual);
        }

    }

    @DisplayName("-ge: 조건 값보다 크거나 같을 경우 (또는 사전 순으로 더 뒤이거나 같을 경우)")
    @Nested
    class SearchCommandGreaterThanOrEqualToTest {

        final String equalityOption = "-ge";

        @DisplayName("성명의이름(firstname)이 KYUMOK 보다 사전순으로 뒤이거나 같은 record 검색")
        @ParameterizedTest
        @CsvSource({"'KYUMOK', 12", "'BEST', 15"})
        public void testThirdOptionWithFirstName(String name, int expected) {
            List<String> options = Arrays.asList("-f", equalityOption);
            List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, name);
            Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
            List<String> resList = executor.execute(command);
            int actual = Integer.parseInt(resList.get(0).split(",")[1]);
            assertEquals(expected, actual);
        }

        @DisplayName("생년월일 중 연도가 1990보다 크거나 같은 record를 검색")
        @ParameterizedTest
        @CsvSource({"'1990', 'NONE'", "'1989', '1'", "'1950', '16'"})
        public void testThirdOptionWithBirthday(String year, String expected) {
            List<String> options = Arrays.asList("-y", equalityOption);
            List<String> params = Arrays.asList(EmployeeStore.FIELD_BIRTH_DAY, year);
            Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
            List<String> resList = executor.execute(command);
            String actual = resList.get(0).split(",")[1];
            assertEquals(expected, actual);
        }

        @DisplayName("certi가 PRO보다 높거나 같은 record를 검색")
        @ParameterizedTest
        @CsvSource({"'PRO', 8", "'EX', 2"})
        public void testThirdOptionWithCerti(String certi, int expected) {
            List<String> options = Arrays.asList(equalityOption);
            List<String> params = Arrays.asList(EmployeeStore.FIELD_CERTI, certi);
            Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
            List<String> resList = executor.execute(command);
            int actual = Integer.parseInt(resList.get(0).split(",")[1]);
            assertEquals(expected, actual);
        }

        @DisplayName("경력개발 단계가 CL3보다 크거나 같은 record를 검색")
        @ParameterizedTest
        @CsvSource({"'CL3', '16", "'CL4', '7'"})
        public void testThirdOptionWithCareerLevel(String cl, String expected) {
            List<String> options = Arrays.asList(equalityOption);
            List<String> params = Arrays.asList(EmployeeStore.FIELD_CAREER_LEVEL, cl);
            Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
            List<String> resList = executor.execute(command);
            String actual = resList.get(0).split(",")[1];
            assertEquals(expected, actual);
        }

    }

    @DisplayName("-s: 조건 값보다 작을 경우 (또는 사전 순으로 더 앞일 경우)")
    @Nested
    public class SearchCommandSmallerTest {

        final String equalityOption = "-s";

        @DisplayName("경력개발 단계가 CL3보다 작은 record를 검색")
        @ParameterizedTest
        @CsvSource({"'CL3', 'NONE", "'CL2', 'NONE'"})
        public void testThirdOptionWithCareerLevel(String cl, String expected) {
            List<String> options = Arrays.asList(equalityOption);
            List<String> params = Arrays.asList(EmployeeStore.FIELD_CAREER_LEVEL, cl);
            Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
            List<String> resList = executor.execute(command);
            String actual = resList.get(0).split(",")[1];
            assertEquals(expected, actual);
        }
    }

    @DisplayName("-se: 조건 값보다 작거나 같을 경우 (또는 사전 순으로 더 앞이거나 같을 경우)")
    @Nested
    public class SearchCommandSmallerThanOrEqualToTest {

        final String equalityOption = "-se";

        @DisplayName("전화번호의 중간자리가 9777보다 작거나 같은 record를 검색")
        @ParameterizedTest
        @CsvSource({"'9777', '15'", "'1111', 'NONE'"})
        public void testThirdOptionWithCareerLevel(String phoneNum, String expected) {
            List<String> options = Arrays.asList("-m", equalityOption);
            List<String> params = Arrays.asList(EmployeeStore.FIELD_PHONE_NUMBER, phoneNum);
            Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
            List<String> resList = executor.execute(command);
            String actual = resList.get(0).split(",")[1];
            assertEquals(expected, actual);
        }
    }
}
