package com.sec.bestreviewer.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandDataTest {

    @Test
    @DisplayName("초기값 확인")
    public void testCommandData() {
        CommandData commandData = new CommandData();
        assertEquals(CommandType.NONE, commandData.getType());
        assertEquals(PrintOption.NONE, commandData.getPrintOption());
        assertEquals(AndOrOption.NONE, commandData.getAndOrOption());
        assertNull(commandData.getModifyData());
        assertTrue(commandData.getAddDataList().isEmpty());
    }
}
