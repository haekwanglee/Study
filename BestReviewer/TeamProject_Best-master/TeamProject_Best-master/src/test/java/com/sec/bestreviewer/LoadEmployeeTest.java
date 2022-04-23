package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.store.Injection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadEmployeeTest {

    public static final int DUMMY_COUNT = 100000;
    private final EmployeeStore employeeStore = Injection.provideEmployeeStore();
    private CommandExecutor executor;
    private int originCount;

    @BeforeEach
    public void setupEmployeeStore() {
        CommandExecutorTestUtil.addEmployees(employeeStore, getDummyEmployees(DUMMY_COUNT));
        CommandExecutorTestUtil.addEmployees(employeeStore);
        executor = new CommandExecutor(employeeStore);
        originCount = employeeStore.count();
    }

    private List<String> getDummyEmployees(int count) {
        final List<String> employeeList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            final String employeeNumber = Integer.toString(90_000000 + i);
            employeeList.add(employeeNumber + ",SEO KFI,CL1,010-1234-5678,20190303,EX");
        }
        return employeeList;
    }

    @Test
    public void testDeleteCommandWithLoadEmployee() {
        final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "SEO KFI");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, CommandExecutorTestUtil.emptyList, params);
        final List<String> resList = executor.execute(command);

        assertEquals(CommandFactory.CMD_DEL + "," + DUMMY_COUNT, resList.get(0));
        assertEquals(originCount - DUMMY_COUNT, employeeStore.count());
    }

    @Test
    public void testDeleteAndCommandWithLoadEmployee() {
        final List<String> options = Arrays.asList("-l", "-a");
        final List<String> params = Arrays.asList(
                EmployeeStore.FIELD_NAME, "KFI",
                EmployeeStore.FIELD_CAREER_LEVEL, "CL1");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        final List<String> resList = executor.execute(command);

        assertEquals(CommandFactory.CMD_DEL + "," + DUMMY_COUNT, resList.get(0));
        assertEquals(originCount - DUMMY_COUNT, employeeStore.count());
    }

    @Test
    public void testDeleteOrCommandWithLoadEmployee() {
        final List<String> options = Arrays.asList("-l", "-o");
        final List<String> params = Arrays.asList(
                EmployeeStore.FIELD_NAME, "KFI",
                EmployeeStore.FIELD_CAREER_LEVEL, "CL2");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_DEL, options, params);
        final List<String> resList = executor.execute(command);

        assertEquals(CommandFactory.CMD_DEL + "," + DUMMY_COUNT, resList.get(0));
        assertEquals(originCount - DUMMY_COUNT, employeeStore.count());
    }

    @Test
    public void testModifyCommandWithLoadEmployee() {
        List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "SEO KFI", EmployeeStore.FIELD_NAME, "KILDONG HONG");
        Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, CommandExecutorTestUtil.emptyList, params);
        List<String> resList = executor.execute(command);
        assertEquals(CommandFactory.CMD_MOD + "," + DUMMY_COUNT, resList.get(0));
    }

    @Test
    public void testModifyAndCommandWithLoadEmployee() {
        final List<String> options = Arrays.asList("-a", "-m");
        final List<String> params = Arrays.asList(
                EmployeeStore.FIELD_CAREER_LEVEL, "CL1",
                EmployeeStore.FIELD_BIRTH_DAY, "03",
                EmployeeStore.FIELD_NAME, "KILDONG HONG");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        List<String> resList = executor.execute(command);

        assertEquals(CommandFactory.CMD_MOD + "," + DUMMY_COUNT, resList.get(0));
    }

    @Test
    public void testModifyOrCommandWithLoadEmployee() {
        final List<String> options = Arrays.asList("-o", "-m");
        final List<String> params = Arrays.asList(EmployeeStore.FIELD_CAREER_LEVEL, "CL1",
                EmployeeStore.FIELD_BIRTH_DAY, "12", EmployeeStore.FIELD_NAME, "KILDONG HONG");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_MOD, options, params);
        List<String> resList = executor.execute(command);

        assertEquals(CommandFactory.CMD_MOD + "," + DUMMY_COUNT, resList.get(0));
    }

    @Test
    public void testSearchCommandWithLoadEmployee() {
        final List<String> params = Arrays.asList(EmployeeStore.FIELD_NAME, "SEO KFI");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, CommandExecutorTestUtil.emptyList, params);
        final List<String> resList = executor.execute(command);

        assertEquals(CommandFactory.CMD_SCH + "," + DUMMY_COUNT, resList.get(0));
    }

    @Test
    public void testSearchAndCommandWithLoadEmployee() {
        final List<String> options = Arrays.asList("-l", "-a");
        final List<String> params = Arrays.asList(
                EmployeeStore.FIELD_NAME, "KFI",
                EmployeeStore.FIELD_CAREER_LEVEL, "CL1");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = (new CommandExecutor(employeeStore)).execute(command);

        assertEquals(CommandFactory.CMD_SCH + "," + DUMMY_COUNT, resList.get(0));
    }

    @Test
    public void testSearchOrCommandWithLoadEmployee() {
        final List<String> options = Arrays.asList("-m", "-o", "-y");
        final List<String> params = Arrays.asList(EmployeeStore.FIELD_PHONE_NUMBER, "9999", EmployeeStore.FIELD_BIRTH_DAY, "2019");
        final Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, options, params);
        final List<String> resList = executor.execute(command);

        assertEquals(CommandFactory.CMD_SCH + "," + DUMMY_COUNT, resList.get(0));
    }

}
