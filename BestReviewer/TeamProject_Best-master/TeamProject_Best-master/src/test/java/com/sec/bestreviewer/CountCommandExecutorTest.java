package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;
import com.sec.bestreviewer.store.EmployeeStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CountCommandExecutorTest {

    private EmployeeStore employeeStore;
    private CommandExecutor executor;

    @BeforeEach
    void createMockEmployeeStore() {
        employeeStore = mock(EmployeeStore.class);
        executor = new CommandExecutor(employeeStore);
    }

    @Test
    public void testCountCommandShouldReturnCountNumberString() {
        when(employeeStore.count()).thenReturn(1);

        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_CNT,
                CommandExecutorTestUtil.emptyList, CommandExecutorTestUtil.emptyList);
        final List<String> resList = executor.execute(command);
        assertEquals(CommandFactory.CMD_CNT + ",1", resList.get(0));
    }

}