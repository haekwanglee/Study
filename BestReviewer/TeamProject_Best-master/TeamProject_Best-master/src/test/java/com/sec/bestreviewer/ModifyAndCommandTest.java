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
import static org.junit.jupiter.api.Assertions.assertThrows;

class ModifyAndCommandTest {

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
    @DisplayName("사번을 변경하는 경우 exception이 발생하는지 확인")
    public void testWithIllegalParams() {
        final List<String> options = Arrays.asList("-p", "-a", "-m");
        final List<String> params = Arrays.asList(
                EmployeeStore.FIELD_CAREER_LEVEL, "CL3",
                EmployeeStore.FIELD_BIRTH_DAY, "07",
                EmployeeStore.FIELD_EMPLOYEE_NUMBER, "01234567");
        assertThrows(IllegalArgumentException.class, () -> {
            Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
            List<String> resList = executor.execute(command);
        });
    }

    @Test
    @DisplayName("경력개발단계가 CL3이고, 생년월일의 월이 07인 record의 성명을 KYUMOK LEE으로 변경")
    public void testWithDifferentOptionsParams() {
        final List<String> options = Arrays.asList("-p", "-a", "-m");
        final List<String> params = Arrays.asList(
                EmployeeStore.FIELD_CAREER_LEVEL, "CL3",
                EmployeeStore.FIELD_BIRTH_DAY, "07",
                EmployeeStore.FIELD_NAME, "KYUMOK LEE");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        List<String> resList = executor.execute(command);

        assertEquals(3, resList.size());
        assertEquals(CommandFactory.CMD_MOD + ",03249872,TYLNXWQN OH,CL3,010-7910-1669,19820728,ADV", resList.get(0));
        assertEquals(CommandFactory.CMD_MOD + ",04567890,HJKL CHOI,CL3,010-6789-6789,19840706,PRO", resList.get(1));
        assertEquals(CommandFactory.CMD_MOD + ",09752663,VWCGM OH,CL3,010-6288-1811,19860720,ADV", resList.get(2));

        resList = CommandExecutorTestUtil.getResultData(executor, Arrays.asList("name", "KYUMOK LEE"));
        assertEquals(CommandFactory.CMD_SCH + ",03249872,KYUMOK LEE,CL3,010-7910-1669,19820728,ADV", resList.get(0));
        assertEquals(CommandFactory.CMD_SCH + ",04567890,KYUMOK LEE,CL3,010-6789-6789,19840706,PRO", resList.get(1));
        assertEquals(CommandFactory.CMD_SCH + ",09752663,KYUMOK LEE,CL3,010-6288-1811,19860720,ADV", resList.get(2));
    }

    @Test
    @DisplayName("동일 옵션, 동일 파라미터를 입력으로 받았을 때, 중복제거와 정렬이 잘되는지 확인")
    public void testWithSameOptionsSameParams() {
        final List<String> options = Arrays.asList("-p", "-l", "-a", "-l");
        final List<String> params = Arrays.asList(
                EmployeeStore.FIELD_NAME, "KIM",
                EmployeeStore.FIELD_NAME, "KIM",
                EmployeeStore.FIELD_CAREER_LEVEL, "CL3");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        final List<String> resList = executor.execute(command);

        assertEquals(3, resList.size());
        assertEquals(CommandFactory.CMD_MOD + ",90123456,FDSA KIM,CL4,010-5432-5432,19790101,PRO", resList.get(0));
        assertEquals(CommandFactory.CMD_MOD + ",03456789,ZXCV KIM,CL3,010-4567-4567,19860404,PRO", resList.get(1));
        assertEquals(CommandFactory.CMD_MOD + ",03717863,NSESFBLRK KIM,CL3,010-4108-4007,19770502,ADV", resList.get(2));
        assertEquals(originCount, employeeStore.count());
    }

    @Test
    @DisplayName("옵션이 -a만 존재하는 경우(-p가 없는 경우) 수정된 아이템의 숫자를 출력해야함")
    public void testWithoutAnyOptions() {
        final List<String> options = Collections.singletonList("-a");
        final List<String> params = Arrays.asList(
                EmployeeStore.FIELD_NAME, "FDSA KIM",
                EmployeeStore.FIELD_CAREER_LEVEL, "CL4",
                EmployeeStore.FIELD_CERTI, "PRO");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        final List<String> resList = executor.execute(command);

        assertEquals(CommandFactory.CMD_MOD + ",1", resList.get(0));
        assertEquals(1, resList.size());
        assertEquals(originCount, employeeStore.count());
    }

    @Test
    @DisplayName("수정할 대상이 없는 경우, MOD,None을 리턴해야함")
    public void testWithoutAnyRecords() {
        final List<String> options = Arrays.asList("-p", "-l", "-a");
        final List<String> params = Arrays.asList(
                EmployeeStore.FIELD_NAME, "KIM",
                EmployeeStore.FIELD_CAREER_LEVEL, "CL2",
                EmployeeStore.FIELD_CERTI, "EX");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        final List<String> resList = executor.execute(command);

        assertEquals(CommandFactory.CMD_MOD + CommandExecutorTestUtil.NONE, resList.get(0));
        assertEquals(1, resList.size());
        assertEquals(originCount, employeeStore.count());
    }
}