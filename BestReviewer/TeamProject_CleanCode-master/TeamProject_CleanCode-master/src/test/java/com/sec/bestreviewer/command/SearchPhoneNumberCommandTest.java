package com.sec.bestreviewer.command;

import com.sec.bestreviewer.data.CommandData;
import com.sec.bestreviewer.data.SearchOption;
import com.sec.bestreviewer.store.Employee;
import org.junit.jupiter.api.Test;

import static com.sec.bestreviewer.data.InqualitySignOption.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchPhoneNumberCommandTest {

    private final String PHONE_NUMBER = "010-1234-5678";
    private final String PHONE_NUMBER_SMALLER = "010-0234-5678";
    private final String PHONE_NUMBER_GREATER = "010-2234-5678";

    private Employee employee = new Employee(
            "01000000", "DAFLKDJ KIM", "CL1",
            PHONE_NUMBER, "20190101", "EX");
    private SearchCommand command = new SearchCommand(new CommandData());

    @Test
    void testSearchFullNumber() {
        assertFalse(command.createFilter(SearchOption.FULL_NUMBER, NONE, PHONE_NUMBER_SMALLER).test(employee));
        assertTrue(command.createFilter(SearchOption.FULL_NUMBER, NONE, PHONE_NUMBER).test(employee));
        assertFalse(command.createFilter(SearchOption.FULL_NUMBER, NONE, PHONE_NUMBER_GREATER).test(employee));
    }

    @Test
    void testSearchFullNumberGreaterThan() {
        assertTrue(command.createFilter(SearchOption.FULL_NUMBER, GREATER_THAN, PHONE_NUMBER_SMALLER).test(employee));
        assertFalse(command.createFilter(SearchOption.FULL_NUMBER, GREATER_THAN, PHONE_NUMBER).test(employee));
        assertFalse(command.createFilter(SearchOption.FULL_NUMBER, GREATER_THAN, PHONE_NUMBER_GREATER).test(employee));
    }

    @Test
    void testSearchFullNumberGreaterThanOrEqualTo() {
        assertTrue(command.createFilter(SearchOption.FULL_NUMBER, GREATER_THAN_OR_EQUAL_TO, PHONE_NUMBER_SMALLER).test(employee));
        assertTrue(command.createFilter(SearchOption.FULL_NUMBER, GREATER_THAN_OR_EQUAL_TO, PHONE_NUMBER).test(employee));
        assertFalse(command.createFilter(SearchOption.FULL_NUMBER, GREATER_THAN_OR_EQUAL_TO, PHONE_NUMBER_GREATER).test(employee));
    }

    @Test
    void testSearchFullNumberSmallerThan() {
        assertFalse(command.createFilter(SearchOption.FULL_NUMBER, SMALLER_THAN, PHONE_NUMBER_SMALLER).test(employee));
        assertFalse(command.createFilter(SearchOption.FULL_NUMBER, SMALLER_THAN, PHONE_NUMBER).test(employee));
        assertTrue(command.createFilter(SearchOption.FULL_NUMBER, SMALLER_THAN, PHONE_NUMBER_GREATER).test(employee));
    }

    @Test
    void testSearchFullNumberSmallerThanOrEqualTo() {
        assertFalse(command.createFilter(SearchOption.FULL_NUMBER, SMALLER_THAN_OR_EQUAL_TO, PHONE_NUMBER_SMALLER).test(employee));
        assertTrue(command.createFilter(SearchOption.FULL_NUMBER, SMALLER_THAN_OR_EQUAL_TO, PHONE_NUMBER).test(employee));
        assertTrue(command.createFilter(SearchOption.FULL_NUMBER, SMALLER_THAN_OR_EQUAL_TO, PHONE_NUMBER_GREATER).test(employee));
    }

    @Test
    void testSearchMiddleNumber() {
        assertFalse(command.createFilter(SearchOption.MIDDLE_NUMBER, NONE, "0123").test(employee));
        assertTrue(command.createFilter(SearchOption.MIDDLE_NUMBER, NONE, "1234").test(employee));
        assertFalse(command.createFilter(SearchOption.MIDDLE_NUMBER, NONE, "2234").test(employee));
    }

    @Test
    void testSearchMiddleNumberGreaterThan() {
        assertTrue(command.createFilter(SearchOption.MIDDLE_NUMBER, GREATER_THAN, "0123").test(employee));
        assertFalse(command.createFilter(SearchOption.MIDDLE_NUMBER, GREATER_THAN, "1234").test(employee));
        assertFalse(command.createFilter(SearchOption.MIDDLE_NUMBER, GREATER_THAN, "2234").test(employee));
    }

    @Test
    void testSearchMiddleNumberGreaterThanOrEqualTo() {
        assertTrue(command.createFilter(SearchOption.MIDDLE_NUMBER, GREATER_THAN_OR_EQUAL_TO, "0123").test(employee));
        assertTrue(command.createFilter(SearchOption.MIDDLE_NUMBER, GREATER_THAN_OR_EQUAL_TO, "1234").test(employee));
        assertFalse(command.createFilter(SearchOption.MIDDLE_NUMBER, GREATER_THAN_OR_EQUAL_TO, "2234").test(employee));
    }

    @Test
    void testSearchMiddleNumberSmallerThan() {
        assertFalse(command.createFilter(SearchOption.MIDDLE_NUMBER, SMALLER_THAN, "0123").test(employee));
        assertFalse(command.createFilter(SearchOption.MIDDLE_NUMBER, SMALLER_THAN, "1234").test(employee));
        assertTrue(command.createFilter(SearchOption.MIDDLE_NUMBER, SMALLER_THAN, "2234").test(employee));
    }

    @Test
    void testSearchMiddleNumberSmallerThanOrEqualTo() {
        assertFalse(command.createFilter(SearchOption.MIDDLE_NUMBER, SMALLER_THAN_OR_EQUAL_TO, "0123").test(employee));
        assertTrue(command.createFilter(SearchOption.MIDDLE_NUMBER, SMALLER_THAN_OR_EQUAL_TO, "1234").test(employee));
        assertTrue(command.createFilter(SearchOption.MIDDLE_NUMBER, SMALLER_THAN_OR_EQUAL_TO, "2234").test(employee));
    }

    @Test
    void testSearchLastNumber() {
        assertFalse(command.createFilter(SearchOption.LAST_NUMBER, NONE, "0678").test(employee));
        assertTrue(command.createFilter(SearchOption.LAST_NUMBER, NONE, "5678").test(employee));
        assertFalse(command.createFilter(SearchOption.LAST_NUMBER, NONE, "6678").test(employee));
    }

    @Test
    void testSearchLastNumberGreaterThan() {
        assertTrue(command.createFilter(SearchOption.LAST_NUMBER, GREATER_THAN, "0678").test(employee));
        assertFalse(command.createFilter(SearchOption.LAST_NUMBER, GREATER_THAN, "5678").test(employee));
        assertFalse(command.createFilter(SearchOption.LAST_NUMBER, GREATER_THAN, "6678").test(employee));
    }

    @Test
    void testSearchLastNumberGreaterThanOrEqualTo() {
        assertTrue(command.createFilter(SearchOption.LAST_NUMBER, GREATER_THAN_OR_EQUAL_TO, "0678").test(employee));
        assertTrue(command.createFilter(SearchOption.LAST_NUMBER, GREATER_THAN_OR_EQUAL_TO, "5678").test(employee));
        assertFalse(command.createFilter(SearchOption.LAST_NUMBER, GREATER_THAN_OR_EQUAL_TO, "6678").test(employee));
    }

    @Test
    void testSearchLastNumberSmallerThan() {
        assertFalse(command.createFilter(SearchOption.LAST_NUMBER, SMALLER_THAN, "0678").test(employee));
        assertFalse(command.createFilter(SearchOption.LAST_NUMBER, SMALLER_THAN, "5678").test(employee));
        assertTrue(command.createFilter(SearchOption.LAST_NUMBER, SMALLER_THAN, "6678").test(employee));
    }

    @Test
    void testSearchLastNumberSmallerThanOrEqualTo() {
        assertFalse(command.createFilter(SearchOption.LAST_NUMBER, SMALLER_THAN_OR_EQUAL_TO, "0678").test(employee));
        assertTrue(command.createFilter(SearchOption.LAST_NUMBER, SMALLER_THAN_OR_EQUAL_TO, "5678").test(employee));
        assertTrue(command.createFilter(SearchOption.LAST_NUMBER, SMALLER_THAN_OR_EQUAL_TO, "6678").test(employee));
    }
}