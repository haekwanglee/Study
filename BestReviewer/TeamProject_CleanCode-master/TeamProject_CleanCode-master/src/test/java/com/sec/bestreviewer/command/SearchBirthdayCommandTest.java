package com.sec.bestreviewer.command;

import com.sec.bestreviewer.data.CommandData;
import com.sec.bestreviewer.data.SearchOption;
import com.sec.bestreviewer.store.Employee;
import org.junit.jupiter.api.Test;

import static com.sec.bestreviewer.data.InqualitySignOption.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchBirthdayCommandTest {

    private final String BIRTHDAY = "20190202";

    private final Employee employee = new Employee(
            "01000000", "DAFLKDJ KIM", "CL1",
            "010-1234-5678", BIRTHDAY, "EX");
    private final SearchCommand command = new SearchCommand(new CommandData());

    @Test
    void testSearchFullBirthday() {
        assertFalse(command.createFilter(SearchOption.FULL_BIRTHDAY, NONE, "20190201").test(employee));
        assertTrue(command.createFilter(SearchOption.FULL_BIRTHDAY, NONE, BIRTHDAY).test(employee));
        assertFalse(command.createFilter(SearchOption.FULL_BIRTHDAY, NONE, "20190203").test(employee));
    }

    @Test
    void testSearchFullBirthdayGreaterThan() {
        assertTrue(command.createFilter(SearchOption.FULL_BIRTHDAY, GREATER_THAN, "20190201").test(employee));
        assertFalse(command.createFilter(SearchOption.FULL_BIRTHDAY, GREATER_THAN, BIRTHDAY).test(employee));
        assertFalse(command.createFilter(SearchOption.FULL_BIRTHDAY, GREATER_THAN, "20190203").test(employee));
    }

    @Test
    void testSearchFullBirthdayGreaterThanOrEqualTo() {
        assertTrue(command.createFilter(SearchOption.FULL_BIRTHDAY, GREATER_THAN_OR_EQUAL_TO, "20190201").test(employee));
        assertTrue(command.createFilter(SearchOption.FULL_BIRTHDAY, GREATER_THAN_OR_EQUAL_TO, BIRTHDAY).test(employee));
        assertFalse(command.createFilter(SearchOption.FULL_BIRTHDAY, GREATER_THAN_OR_EQUAL_TO, "20190203").test(employee));
    }

    @Test
    void testSearchFullBirthdaySmallerThan() {
        assertFalse(command.createFilter(SearchOption.FULL_BIRTHDAY, SMALLER_THAN, "20190201").test(employee));
        assertFalse(command.createFilter(SearchOption.FULL_BIRTHDAY, SMALLER_THAN, BIRTHDAY).test(employee));
        assertTrue(command.createFilter(SearchOption.FULL_BIRTHDAY, SMALLER_THAN, "20190203").test(employee));
    }

    @Test
    void testSearchFullBirthdaySmallerThanOrEqualTo() {
        assertFalse(command.createFilter(SearchOption.FULL_BIRTHDAY, SMALLER_THAN_OR_EQUAL_TO, "20190201").test(employee));
        assertTrue(command.createFilter(SearchOption.FULL_BIRTHDAY, SMALLER_THAN_OR_EQUAL_TO, BIRTHDAY).test(employee));
        assertTrue(command.createFilter(SearchOption.FULL_BIRTHDAY, SMALLER_THAN_OR_EQUAL_TO, "20190203").test(employee));
    }

    @Test
    void testSearchBirthdayYear() {
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_YEAR, NONE, "2018").test(employee));
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_YEAR, NONE, "2019").test(employee));
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_YEAR, NONE, "2020").test(employee));
    }

    @Test
    void testSearchFullBirthdayYearGreaterThan() {
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_YEAR, GREATER_THAN, "2018").test(employee));
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_YEAR, GREATER_THAN, "2019").test(employee));
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_YEAR, GREATER_THAN, "2020").test(employee));
    }

    @Test
    void testSearchFullBirthdayYearGreaterThanOrEqualTo() {
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_YEAR, GREATER_THAN_OR_EQUAL_TO, "2018").test(employee));
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_YEAR, GREATER_THAN_OR_EQUAL_TO, "2019").test(employee));
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_YEAR, GREATER_THAN_OR_EQUAL_TO, "2020").test(employee));
    }

    @Test
    void testSearchFullBirthdayYearSmallerThan() {
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_YEAR, SMALLER_THAN, "2018").test(employee));
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_YEAR, SMALLER_THAN, "2019").test(employee));
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_YEAR, SMALLER_THAN, "2020").test(employee));
    }

    @Test
    void testSearchFullBirthdayYearSmallerThanOrEqualTo() {
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_YEAR, SMALLER_THAN_OR_EQUAL_TO, "2018").test(employee));
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_YEAR, SMALLER_THAN_OR_EQUAL_TO, "2019").test(employee));
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_YEAR, SMALLER_THAN_OR_EQUAL_TO, "2020").test(employee));
    }

    @Test
    void testSearchBirthdayMonth() {
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_MONTH, NONE, "01").test(employee));
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_MONTH, NONE, "02").test(employee));
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_MONTH, NONE, "03").test(employee));
    }

    @Test
    void testSearchFullBirthdayMonthGreaterThan() {
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_MONTH, GREATER_THAN, "01").test(employee));
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_MONTH, GREATER_THAN, "02").test(employee));
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_MONTH, GREATER_THAN, "03").test(employee));
    }

    @Test
    void testSearchFullBirthdayMonthGreaterThanOrEqualTo() {
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_MONTH, GREATER_THAN_OR_EQUAL_TO, "01").test(employee));
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_MONTH, GREATER_THAN_OR_EQUAL_TO, "02").test(employee));
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_MONTH, GREATER_THAN_OR_EQUAL_TO, "03").test(employee));
    }

    @Test
    void testSearchFullBirthdayMonthSmallerThan() {
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_MONTH, SMALLER_THAN, "01").test(employee));
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_MONTH, SMALLER_THAN, "02").test(employee));
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_MONTH, SMALLER_THAN, "03").test(employee));
    }

    @Test
    void testSearchFullBirthdayMonthSmallerThanOrEqualTo() {
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_MONTH, SMALLER_THAN_OR_EQUAL_TO, "01").test(employee));
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_MONTH, SMALLER_THAN_OR_EQUAL_TO, "02").test(employee));
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_MONTH, SMALLER_THAN_OR_EQUAL_TO, "03").test(employee));
    }

    @Test
    void testSearchBirthdayDay() {
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_DAY, NONE, "01").test(employee));
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_DAY, NONE, "02").test(employee));
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_DAY, NONE, "03").test(employee));
    }

    @Test
    void testSearchFullBirthdayDayGreaterThan() {
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_DAY, GREATER_THAN, "01").test(employee));
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_DAY, GREATER_THAN, "02").test(employee));
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_DAY, GREATER_THAN, "03").test(employee));
    }

    @Test
    void testSearchFullBirthdayDayGreaterThanOrEqualTo() {
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_DAY, GREATER_THAN_OR_EQUAL_TO, "01").test(employee));
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_DAY, GREATER_THAN_OR_EQUAL_TO, "02").test(employee));
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_DAY, GREATER_THAN_OR_EQUAL_TO, "03").test(employee));
    }

    @Test
    void testSearchFullBirthdayDaySmallerThan() {
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_DAY, SMALLER_THAN, "01").test(employee));
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_DAY, SMALLER_THAN, "02").test(employee));
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_DAY, SMALLER_THAN, "03").test(employee));
    }

    @Test
    void testSearchFullBirthdayDaySmallerThanOrEqualTo() {
        assertFalse(command.createFilter(SearchOption.BIRTHDAY_DAY, SMALLER_THAN_OR_EQUAL_TO, "01").test(employee));
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_DAY, SMALLER_THAN_OR_EQUAL_TO, "02").test(employee));
        assertTrue(command.createFilter(SearchOption.BIRTHDAY_DAY, SMALLER_THAN_OR_EQUAL_TO, "03").test(employee));
    }
}