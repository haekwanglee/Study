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

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteOrCommandTest {
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
    @DisplayName("성명의 성이 KIM 이거나, 경력개발단계가 CL4인 record를 삭제")
    public void testWithDifferentOptionsParams() {
        final List<String> options = Arrays.asList("-p", "-l", "-o");
        final List<String> params = Arrays.asList(
                EmployeeStore.FIELD_NAME, "KIM",
                EmployeeStore.FIELD_CAREER_LEVEL, "CL4");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        final List<String> resList = executor.execute(command);

        assertEquals(MAX_RESULT_NUMBER, resList.size());
        assertEquals(CommandFactory.CMD_DEL + ",90123456,FDSA KIM,CL4,010-5432-5432,19790101,PRO", resList.get(0));
        assertEquals(CommandFactory.CMD_DEL + ",92345678,REWQ LEE,CL4,010-9876-9876,19800202,PRO", resList.get(1));
        assertEquals(CommandFactory.CMD_DEL + ",93456789,VCXZ PARK,CL4,010-7654-7654,19810303,EX", resList.get(2));
        assertEquals(CommandFactory.CMD_DEL + ",93728322,QSJYRB KANG,CL4,010-3602-4064,19690603,ADV", resList.get(3));
        assertEquals(CommandFactory.CMD_DEL + ",94526445,VLPDHI LEE,CL4,010-7975-9766,19721111,ADV", resList.get(4));

        assertEquals(originCount - 9, employeeStore.count());
    }

    @Test
    @DisplayName("동일 옵션, 동일 파라미터를 입력으로 받았을 때, 중복제거와 정렬이 잘되는지 확인")
    public void testWithSameOptionsSameParams() {
        final List<String> options = Arrays.asList("-p", "-l", "-o", "-l");
        final List<String> params = Arrays.asList(
                EmployeeStore.FIELD_NAME, "KIM",
                EmployeeStore.FIELD_NAME, "KIM");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        final List<String> resList = executor.execute(command);

        assertEquals(3, resList.size());
        assertEquals(CommandFactory.CMD_DEL + ",90123456,FDSA KIM,CL4,010-5432-5432,19790101,PRO", resList.get(0));
        assertEquals(CommandFactory.CMD_DEL + ",03456789,ZXCV KIM,CL3,010-4567-4567,19860404,PRO", resList.get(1));
        assertEquals(CommandFactory.CMD_DEL + ",03717863,NSESFBLRK KIM,CL3,010-4108-4007,19770502,ADV", resList.get(2));
        assertEquals(originCount - 3, employeeStore.count());
    }

    @Test
    @DisplayName("옵션이 -a만 존재하는 경우(-p가 없는 경우) 제거된 아이템의 숫자를 출력해야함")
    public void testWithoutAnyOptions() {
        final List<String> options = Collections.singletonList("-o");
        final List<String> params = Arrays.asList(
                EmployeeStore.FIELD_NAME, "QWER OH",
                EmployeeStore.FIELD_CAREER_LEVEL, "CL4");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        final List<String> resList = executor.execute(command);

        assertEquals(CommandFactory.CMD_DEL + ",8", resList.get(0));
        assertEquals(1, resList.size());
        assertEquals(originCount - 8, employeeStore.count());
    }

    @Test
    @DisplayName("제거할 대상이 없는 경우, DEL,None을 리턴해야함")
    public void testWithoutAnyRecords() {
        final List<String> options = Arrays.asList("-p", "-l", "-o");
        final List<String> params = Arrays.asList(
                EmployeeStore.FIELD_NAME, "KWON",
                EmployeeStore.FIELD_CAREER_LEVEL, "CL2");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        final List<String> resList = executor.execute(command);

        assertEquals(CommandFactory.CMD_DEL + CommandExecutorTestUtil.NONE, resList.get(0));
        assertEquals(1, resList.size());
        assertEquals(originCount, employeeStore.count());
    }
}