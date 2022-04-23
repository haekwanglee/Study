package com.sec.bestreviewer.command;

import com.sec.bestreviewer.data.CommandData;
import com.sec.bestreviewer.data.CommandType;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AddCommandTest {

    private EmployeeStore employeeStore;

    @BeforeEach
    void createMockEmployeeStore() {
        employeeStore = mock(EmployeeStore.class);
    }

    @Test
    void executeReturnEmptyList() {
        final List<String> params = Arrays.asList("18050301", "KYUMOK KIM", "CL2", "010-9777-6055", "19980906", "PRO");
        final Employee employee =
                new Employee(params.get(0), params.get(1), params.get(2), params.get(3), params.get(4), params.get(5));

        CommandData commandData = new CommandData();
        commandData.setType(CommandType.ADD);
        commandData.setAddDataList(params);
        AddCommand addCommand = new AddCommand(commandData);

        final List<String> res = addCommand.execute(employeeStore);

        assertNotNull(res);
        assertEquals(0, res.size());
        verify(employeeStore).add(employee);
    }
}