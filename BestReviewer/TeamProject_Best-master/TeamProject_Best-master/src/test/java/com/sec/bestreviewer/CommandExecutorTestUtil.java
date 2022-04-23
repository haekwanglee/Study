package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommandExecutorTestUtil {


    public static final String NONE = ",NONE";
    public static String[] employees = {"02712462,UAFBOWU HONG,CL3,010-9054-6560,19780825,ADV",
            "09752663,VWCGM OH,CL3,010-6288-1811,19860720,ADV",
            "94526445,VLPDHI LEE,CL4,010-7975-9766,19721111,ADV",
            "99398134,JYFMBMT OH,CL4,011-5269-9657,19730321,ADV",
            "03717863,NSESFBLRK KIM,CL3,010-4108-4007,19770502,ADV",
            "93728322,QSJYRB KANG,CL4,010-3602-4064,19690603,ADV",
            "03249872,TYLNXWQN OH,CL3,010-7910-1669,19820728,ADV",
            "01234567,ASDF LEE,CL3,010-1234-1234,19890123,ADV",
            "02345678,QWER PARK,CL3,010-3456-3456,19850909,PRO",
            "03456789,ZXCV KIM,CL3,010-4567-4567,19860404,PRO",
            "04567890,HJKL CHOI,CL3,010-6789-6789,19840706,PRO",
            "05678901,QWER OH,CL3,010-2345-2345,19831008,PRO",
            "90123456,FDSA KIM,CL4,010-5432-5432,19790101,PRO",
            "92345678,REWQ LEE,CL4,010-9876-9876,19800202,PRO",
            "93456789,VCXZ PARK,CL4,010-7654-7654,19810303,EX",
            "94567890,LKJH CHOI,CL4,010-6543-6543,19820404,EX"};
    public static List<String> emptyList = Collections.emptyList();
    public static List<String> printOptions = Collections.singletonList("-p");

    public static List<Employee> getEmployees(int count) {
        final List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            final String employeeNumber = Integer.toString(90_000000 + (i * 10_000000));
            employeeList.add(
                    new Employee(employeeNumber, "SEO KFI", "CL1", "010-1234-5678", "20190303", "EX"));
        }
        return employeeList;
    }

    public static void addEmployees(EmployeeStore employeeStore) {
        for (String employee : employees) {
            List<String> param = new ArrayList(Arrays.asList(employee.split(",")));

            Command addCommand = CommandFactory.buildCommand(CommandFactory.CMD_ADD, Collections.emptyList(), param);
            List<String> res = (new CommandExecutor(employeeStore)).execute(addCommand);
        }
    }

    public static void addEmployees(EmployeeStore employeeStore, List<String> employeeList) {
        for (String employee : employeeList) {
            List<String> param = new ArrayList(Arrays.asList(employee.split(",")));
            Command addCommand = CommandFactory.buildCommand(CommandFactory.CMD_ADD, Collections.emptyList(), param);
            List<String> res = (new CommandExecutor(employeeStore)).execute(addCommand);
        }
    }

    public static List<String> getResultData(CommandExecutor executor, List<String> params) {
        Command command = CommandFactory.buildCommand(CommandFactory.CMD_SCH, CommandExecutorTestUtil.printOptions, params);
        return executor.execute(command);
    }
}
