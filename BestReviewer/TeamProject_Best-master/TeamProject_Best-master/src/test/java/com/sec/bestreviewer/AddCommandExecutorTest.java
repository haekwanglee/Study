package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;
import com.sec.bestreviewer.store.EmployeeStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class AddCommandExecutorTest {

    private static final String[] EMPLOYEE_INFORMATION = {"18050301", "KYUMOK KIM", "CL2", "010-9777-6055", "19980906", "ADV"};
    private EmployeeStore employeeStore;
    private CommandExecutor executor;
    private List<String> params;

    @BeforeEach
    void createMockEmployeeStore() {
        employeeStore = mock(EmployeeStore.class);
        executor = new CommandExecutor();
        params = Arrays.asList(EMPLOYEE_INFORMATION);
    }

    @Test
    public void queryExecutorReturnsResultString() {
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_ADD, CommandExecutorTestUtil.printOptions, params);
        final List<String> res = executor.execute(command);
        assertNotNull(res);
    }

    @Test
    public void testAddCommandReturnsEmptyList() {
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_ADD, CommandExecutorTestUtil.emptyList, params);
        final List<String> res = executor.execute(command);
        assertNotNull(res);
        assertEquals(0, res.size());
    }
}
