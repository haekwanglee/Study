package com.sec.bestreviewer;

import com.sec.bestreviewer.command.querycondition.QueryCondition;
import com.sec.bestreviewer.command.queryprocessor.ComparisonQueryProcessor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class ComparisonQueryProcessorTest {

    ComparisonQueryProcessor comparisonQueryProcessor = new ComparisonQueryProcessor();

    @Nested
    @DisplayName("사원 성명으로 비교")
    class NameCompare {
        QueryCondition queryCondition;
        String compareValue;

        @BeforeEach
        void setUp() {
            compareValue = "KYUMOK KIM";
            queryCondition = new QueryCondition();
            queryCondition.setColumn("name");
            queryCondition.setOption2("");
        }

        @ParameterizedTest
        @CsvSource({"KYUMOK KIM,true", "KYUMOK KIN,false"})
        void testCompareEqual(String queryValue, boolean expected) {
            queryCondition.setOption3("");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"KYUMOK KIN,false", "KYUMOK KIM,false", "KYUMOK KIL,true"})
        void testCompareGreaterThan(String queryValue, boolean expected) {
            queryCondition.setOption3("-g");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"KYUMOK KIN,false", "KYUMOK KIM,true", "KYUMOK KIL,true"})
        void testCompareByNameGreaterThanEqual(String queryValue, boolean expected) {
            queryCondition.setOption2("");
            queryCondition.setOption3("-ge");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"KYUMOK KIN,true", "KYUMOK KIM,false", "KYUMOK KIL,false"})
        void testCompareSmallerThan(String queryValue, boolean expected) {
            queryCondition.setOption3("-s");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"KYUMOK KIN,true", "KYUMOK KIM,true", "KYUMOK KIL,false"})
        void testCompareSmallerThanEqual(String queryValue, boolean expected) {
            queryCondition.setOption3("-se");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }
    }

    @Nested
    @DisplayName("사원번호로 비교")
    class EmployeeNumberCompare {
        QueryCondition queryCondition;
        String compareValue;

        @BeforeEach
        void setUp() {
            compareValue = "18050301";
            queryCondition = new QueryCondition();
            queryCondition.setColumn("employeeNum");
            queryCondition.setOption2("");
        }

        @ParameterizedTest
        @CsvSource({"18050301,true", "18050302,false"})
        void testCompareEqual(String queryValue, boolean expected) {
            queryCondition.setOption3("");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"19050301,false", "18050301,false", "17050301,true", "91050301,true"})
        void testCompareGreaterThan(String queryValue, boolean expected) {
            queryCondition.setOption3("-g");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"19050301,false", "18050301,true", "17050301,true", "91050301,true"})
        void testCompareByNameGreaterThanEqual(String queryValue, boolean expected) {
            queryCondition.setOption3("-ge");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"19050301,true", "18050301,false", "17050301,false", "91050301,false"})
        void testCompareSmallerThan(String queryValue, boolean expected) {
            queryCondition.setOption3("-s");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"19050301,true", "18050301,true", "17050301,false", "91050301,false"})
        void testCompareSmallerThanEqual(String queryValue, boolean expected) {
            queryCondition.setOption3("-se");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }
    }

    @Nested
    @DisplayName("경력개발 단계 비교")
    class CareerLevelCompare {
        QueryCondition queryCondition;
        String compareValue;

        @BeforeEach
        void setUp() {
            compareValue = "CL2";
            queryCondition = new QueryCondition();
            queryCondition.setColumn("cl");
            queryCondition.setOption2("");
        }

        @ParameterizedTest
        @CsvSource({"CL2,true", "CL3,false"})
        void testCompareEqual(String queryValue, boolean expected) {
            queryCondition.setOption3("");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"CL3,false", "CL2,false", "CL1,true"})
        void testCompareGreaterThan(String queryValue, boolean expected) {
            queryCondition.setOption3("-g");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"CL3,false", "CL2,true", "CL1,true"})
        void testCompareByNameGreaterThanEqual(String queryValue, boolean expected) {
            queryCondition.setOption2("");
            queryCondition.setOption3("-ge");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"CL3,true", "CL2,false", "CL1,false"})
        void testCompareSmallerThan(String queryValue, boolean expected) {
            queryCondition.setOption3("-s");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"CL3,true", "CL2,true", "CL1,false"})
        void testCompareSmallerThanEqual(String queryValue, boolean expected) {
            queryCondition.setOption3("-se");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }
    }

    @Nested
    @DisplayName("전화번호 비교")
    class PhoneNumberCompare {
        QueryCondition queryCondition;
        String compareValue;

        @BeforeEach
        void setUp() {
            compareValue = "010-9777-6055";
            queryCondition = new QueryCondition();
            queryCondition.setColumn("phoneNum");
            queryCondition.setOption2("");
        }

        @ParameterizedTest
        @CsvSource({"010-9777-6055,true", "010-9777-6056,false"})
        void testCompareEqual(String queryValue, boolean expected) {
            queryCondition.setOption3("");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"010-9777-6045,true", "010-9777-6065,false"})
        void testCompareGreaterThan(String queryValue, boolean expected) {
            queryCondition.setOption3("-g");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"010-9777-6045,true", "010-9777-6055,true", "010-9777-6065,false"})
        void testCompareByNameGreaterThanEqual(String queryValue, boolean expected) {
            queryCondition.setOption3("-ge");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"010-9777-6045,false", "010-9777-6055,false", "010-9777-6065,true"})
        void testCompareSmallerThan(String queryValue, boolean expected) {
            queryCondition.setOption3("-s");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"010-9777-6045,false", "010-9777-6055,true", "010-9777-6065,true"})
        void testCompareSmallerThanEqual(String queryValue, boolean expected) {
            queryCondition.setOption3("-se");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }
    }

    @Nested
    @DisplayName("전화번호 중간자리 비교")
    class PhoneNumberMiddleCompare {
        QueryCondition queryCondition;
        String compareValue;

        @BeforeEach
        void setUp() {
            compareValue = "9777";
            queryCondition = new QueryCondition();
            queryCondition.setColumn("phoneNum");
            queryCondition.setOption2("-m");
        }

        @ParameterizedTest
        @CsvSource({"9777,true", "9778,false"})
        void testMiddleCompareEqual(String queryValue, boolean expected) {
            queryCondition.setOption3("");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"9776,true", "9777,false", "9778,false"})
        void testMiddleCompareGreaterThan(String queryValue, boolean expected) {
            queryCondition.setOption3("-g");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"9776,true", "9777,true", "9778,false"})
        void testMiddleCompareByNameGreaterThanEqual(String queryValue, boolean expected) {
            queryCondition.setOption3("-ge");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"9776,false", "9777,false", "9778,true"})
        void testMiddleCompareSmallerThan(String queryValue, boolean expected) {
            queryCondition.setOption3("-s");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"9776,false", "9777,true", "9778,true"})
        void testMiddleCompareSmallerThanEqual(String queryValue, boolean expected) {
            queryCondition.setOption3("-se");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }
    }

    @Nested
    @DisplayName("CERTI 비교")
    class CertificationCompare {
        QueryCondition queryCondition;
        String compareValue;

        @BeforeEach
        void setUp() {
            compareValue = "PRO";
            queryCondition = new QueryCondition();
            queryCondition.setColumn("certi");
            queryCondition.setOption2("");
        }

        @Test
        void testWrongArgument() {
            queryCondition.setOption3("");
            queryCondition.setValue("VG");
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                comparisonQueryProcessor.process(queryCondition, compareValue);
            });
        }

        @ParameterizedTest
        @CsvSource({"PRO,true", "ADV,false"})
        void testCompareEqual(String queryValue, boolean expected) {
            queryCondition.setOption3("");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"ADV,true", "PRO,false", "EX,false"})
        void testCompareGreaterThan(String queryValue, boolean expected) {
            queryCondition.setOption3("-g");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"ADV,true", "PRO,true", "EX,false"})
        void testCompareByNameGreaterThanEqual(String queryValue, boolean expected) {
            queryCondition.setOption3("-ge");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"ADV,false", "PRO,false", "EX,true"})
        void testCompareSmallerThan(String queryValue, boolean expected) {
            queryCondition.setOption3("-s");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"ADV,false", "PRO,true", "EX,true"})
        void testCompareSmallerThanEqual(String queryValue, boolean expected) {
            queryCondition.setOption3("-se");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }
    }

    @Nested
    @DisplayName("생년월일 비교")
    class BirthdayCompare {
        QueryCondition queryCondition;
        String compareValue;

        @BeforeEach
        void setUp() {
            compareValue = "19980906";
            queryCondition = new QueryCondition();
            queryCondition.setColumn("birthday");
            queryCondition.setOption2("");
        }

        @ParameterizedTest
        @CsvSource({"19980906,true", "19980907,false"})
        void testCompareEqual(String queryValue, boolean expected) {
            queryCondition.setOption3("");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"19980905,true", "19980906,false", "19980907,false"})
        void testCompareGreaterThan(String queryValue, boolean expected) {
            queryCondition.setOption3("-g");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"19980905,true", "19980906,true", "19980907,false"})
        void testCompareByNameGreaterThanEqual(String queryValue, boolean expected) {
            queryCondition.setOption3("-ge");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"19980905,false", "19980906,false", "19980907,true"})
        void testCompareSmallerThan(String queryValue, boolean expected) {
            queryCondition.setOption3("-s");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }

        @ParameterizedTest
        @CsvSource({"19980905,false", "19980906,true", "19980907,true"})
        void testCompareSmallerThanEqual(String queryValue, boolean expected) {
            queryCondition.setOption3("-se");
            queryCondition.setValue(queryValue);

            assertEquals(expected, comparisonQueryProcessor.process(queryCondition, compareValue));
        }
    }
}
