package com.sec.bestreviewer.command;

import com.sec.bestreviewer.CommandFactory;
import com.sec.bestreviewer.store.EmployeeStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CountCommandTest {
    private EmployeeStore employeeStore;

    @BeforeEach
    void createMockEmployeeStore() {
        employeeStore = mock(EmployeeStore.class);
    }

    @Test
    void executeReturnNumberString() {
        when(employeeStore.count()).thenReturn(10);
        CountCommand countCommand = new CountCommand();
        final List<String> resList = countCommand.execute(employeeStore);
        assertEquals(CommandFactory.CMD_CNT + ",10", resList.get(0));
    }
}