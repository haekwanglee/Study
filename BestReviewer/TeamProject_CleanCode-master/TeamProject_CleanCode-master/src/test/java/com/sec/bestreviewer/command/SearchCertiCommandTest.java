package com.sec.bestreviewer.command;

import com.sec.bestreviewer.data.CommandData;
import com.sec.bestreviewer.data.SearchOption;
import com.sec.bestreviewer.store.Employee;
import org.junit.jupiter.api.Test;

import static com.sec.bestreviewer.data.InqualitySignOption.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchCertiCommandTest {

    private final Employee employee = new Employee(
            "01000000", "DAFLKDJ KIM", "CL1",
            "010-1234-5678", "20190202", "PRO");
    private final SearchCommand command = new SearchCommand(new CommandData());

    @Test
    void testSearchCerti() {
        assertFalse(command.createFilter(SearchOption.CERTI, NONE, "ADV").test(employee));
        assertTrue(command.createFilter(SearchOption.CERTI, NONE, "PRO").test(employee));
        assertFalse(command.createFilter(SearchOption.CERTI, NONE, "EX").test(employee));
    }

    @Test
    void testSearchCertiGreaterThan() {
        assertTrue(command.createFilter(SearchOption.CERTI, GREATER_THAN, "ADV").test(employee));
        assertFalse(command.createFilter(SearchOption.CERTI, GREATER_THAN, "PRO").test(employee));
        assertFalse(command.createFilter(SearchOption.CERTI, GREATER_THAN, "EX").test(employee));
    }

    @Test
    void testSearchCertiGreaterThanOrEqualTo() {
        assertTrue(command.createFilter(SearchOption.CERTI, GREATER_THAN_OR_EQUAL_TO, "ADV").test(employee));
        assertTrue(command.createFilter(SearchOption.CERTI, GREATER_THAN_OR_EQUAL_TO, "PRO").test(employee));
        assertFalse(command.createFilter(SearchOption.CERTI, GREATER_THAN_OR_EQUAL_TO, "EX").test(employee));
    }

    @Test
    void testSearchCertiSmallerThan() {
        assertFalse(command.createFilter(SearchOption.CERTI, SMALLER_THAN, "ADV").test(employee));
        assertFalse(command.createFilter(SearchOption.CERTI, SMALLER_THAN, "PRO").test(employee));
        assertTrue(command.createFilter(SearchOption.CERTI, SMALLER_THAN, "EX").test(employee));
    }

    @Test
    void testSearchCertiSmallerThanOrEqualTo() {
        assertFalse(command.createFilter(SearchOption.CERTI, SMALLER_THAN_OR_EQUAL_TO, "ADV").test(employee));
        assertTrue(command.createFilter(SearchOption.CERTI, SMALLER_THAN_OR_EQUAL_TO, "PRO").test(employee));
        assertTrue(command.createFilter(SearchOption.CERTI, SMALLER_THAN_OR_EQUAL_TO, "EX").test(employee));
    }
}
