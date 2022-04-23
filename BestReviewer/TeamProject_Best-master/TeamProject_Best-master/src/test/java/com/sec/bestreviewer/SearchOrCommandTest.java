package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.store.Injection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchOrCommandTest {
    private final EmployeeStore employeeStore = Injection.provideEmployeeStore();
    private CommandExecutor executor;
    private int originCount;

    @BeforeEach
    public void setupEmployeeStore() {
        CommandExecutorTestUtil.addEmployees(employeeStore);
        executor = new CommandExecutor(employeeStore);
        originCount = employeeStore.count();
    }

    @Test
    @DisplayName("전화번호의 가운데가 5269이거나, 생년원일의 연도가 1982인 record 탐색")
    public void testWithDifferentOptionsParams() {
        final List<String> options = Arrays.asList("-p", "-m", "-o", "-y");
        final List<String> params = Arrays.asList(EmployeeStore.FIELD_PHONE_NUMBER, "5269", EmployeeStore.FIELD_BIRTH_DAY, "1982");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = executor.execute(command);

        assertEquals(3, resList.size());
        assertEquals(CommandFactory.CMD_SCH + ",94567890,LKJH CHOI,CL4,010-6543-6543,19820404,EX", resList.get(0));
        assertEquals(CommandFactory.CMD_SCH + ",99398134,JYFMBMT OH,CL4,011-5269-9657,19730321,ADV", resList.get(1));
        assertEquals(CommandFactory.CMD_SCH + ",03249872,TYLNXWQN OH,CL3,010-7910-1669,19820728,ADV", resList.get(2));

        assertEquals(originCount, employeeStore.count());
    }

    @Test
    @DisplayName("동일 옵션, 동일 파라미터를 입력으로 받았을 때, 중복제거와 정렬이 잘되는지 확인")
    public void testWithSameOptionsSameParams() {
        final List<String> options = Arrays.asList("-p", "-l", "-o", "-l");
        final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "KIM", EmployeeStore.FIELD_NAME, "KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = executor.execute(command);

        assertEquals(3, resList.size());
        assertEquals(CommandFactory.CMD_SCH + ",90123456,FDSA KIM,CL4,010-5432-5432,19790101,PRO", resList.get(0));
        assertEquals(CommandFactory.CMD_SCH + ",03456789,ZXCV KIM,CL3,010-4567-4567,19860404,PRO", resList.get(1));
        assertEquals(CommandFactory.CMD_SCH + ",03717863,NSESFBLRK KIM,CL3,010-4108-4007,19770502,ADV", resList.get(2));
        assertEquals(originCount, employeeStore.count());
    }

    @Test
    @DisplayName("옵션이 -a만 존재하는 경우(-p가 없는 경우) 검색된 아이템의 숫자를 출력해야함")
    public void testWithoutAnyOptions() {
        final List<String> options = Collections.singletonList("-o");
        final List<String> params = Arrays.asList(
                EmployeeStore.FIELD_NAME, "QWER OH",
                EmployeeStore.FIELD_CAREER_LEVEL, "CL4");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = executor.execute(command);

        assertEquals(CommandFactory.CMD_SCH + ",8", resList.get(0));
        assertEquals(1, resList.size());
        assertEquals(originCount, employeeStore.count());
    }

    @Test
    @DisplayName("검색 대상이 없는 경우, SCH,None을 리턴해야함")
    public void testWithoutAnyRecords() {
        final List<String> options = Arrays.asList("-p", "-l", "-o");
        final List<String> params = Arrays.asList(
                EmployeeStore.FIELD_NAME, "KWON",
                EmployeeStore.FIELD_CAREER_LEVEL, "CL2");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = executor.execute(command);

        assertEquals(CommandFactory.CMD_SCH + CommandExecutorTestUtil.NONE, resList.get(0));
        assertEquals(1, resList.size());
        assertEquals(originCount, employeeStore.count());
    }
}