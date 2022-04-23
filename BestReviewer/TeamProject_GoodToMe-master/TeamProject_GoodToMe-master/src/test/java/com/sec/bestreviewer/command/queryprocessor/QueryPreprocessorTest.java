package com.sec.bestreviewer.command.queryprocessor;

import com.sec.bestreviewer.command.querycondition.QueryCondition;
import com.sec.bestreviewer.store.Employee;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueryPreprocessorTest {

    QueryPreprocessor queryPreprocessor = new QueryPreprocessor();
    QueryCondition queryCondition;
    Employee employee = new Employee("15301767", "Kildong Hong",
            "cl2", "010-3334-3333", "19900507", "PRO");

    @BeforeEach
    void setup() {
        queryCondition = new QueryCondition();
        queryCondition.setOption3(" ");
    }

    @DisplayName("Name field test")
    @ParameterizedTest
    @CsvSource({
            "Hong, name, -l, Hong",
            "Kildong,  name, -f, Kildong",
            "Kildong Hong,  name, ' ', Kildong Hong",
    })
    void testNameProcess(String value, String field, String option) {
        queryCondition.setColumn(field);
        queryCondition.setValue(value);
        queryCondition.setOption2(option);

        String processedValue = queryPreprocessor.process(queryCondition, employee);
        Assertions.assertEquals(value, processedValue);
    }

    @DisplayName("Birthday field test")
    @ParameterizedTest
    @CsvSource({
            "1990,  birthDay, -y, 1990",
            "05,  birthDay, -m, 05",
            "07,  birthDay, -d, 07",
            "19900507,  birthDay, ' ', 19900507",
    })
    void testBirthdayProcess(String value, String field, String option) {
        queryCondition.setColumn(field);
        queryCondition.setValue(value);
        queryCondition.setOption2(option);

        String processedValue = queryPreprocessor.process(queryCondition, employee);
        Assertions.assertEquals(value, processedValue);
    }

    @DisplayName("PhoneNumber field test")
    @ParameterizedTest
    @CsvSource({
            "3334,  phoneNumber, -m",
            "3333,  phoneNumber, -l",
            "010-3334-3333,  phoneNumber, ' '",
    })
    void testPhoneNumberProcess(String value, String field, String option) {
        queryCondition.setColumn(field);
        queryCondition.setValue(value);
        queryCondition.setOption2(option);

        String processedValue = queryPreprocessor.process(queryCondition, employee);
        Assertions.assertEquals(value, processedValue);
    }

    @DisplayName("careerLevel field test")
    @ParameterizedTest
    @CsvSource({
            "cl2,  careerLevel, ' '",
    })
    void testCareerLevelProcess(String value, String field, String option) {
        queryCondition.setColumn(field);
        queryCondition.setValue(value);
        queryCondition.setOption2(option);

        String processedValue = queryPreprocessor.process(queryCondition, employee);
        Assertions.assertEquals(value, processedValue);
    }

    @DisplayName("employeeNumber field test")
    @ParameterizedTest
    @CsvSource({
            "15301767,  employeeNumber, ' '",
    })
    void testEmployeeNumberProcess(String value, String field, String option) {
        queryCondition.setColumn(field);
        queryCondition.setValue(value);
        queryCondition.setOption2(option);

        String processedValue = queryPreprocessor.process(queryCondition, employee);
        Assertions.assertEquals(value, processedValue);
    }

    @DisplayName("Certification field test")
    @ParameterizedTest
    @CsvSource({
            "PRO,  certi, ' '",
    })
    void testCertificationProcess(String value, String field, String option) {
        queryCondition.setColumn(field);
        queryCondition.setValue(value);
        queryCondition.setOption2(option);

        String processedValue = queryPreprocessor.process(queryCondition, employee);
        Assertions.assertEquals(value, processedValue);
    }
}
