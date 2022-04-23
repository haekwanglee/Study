package com.sec.bestreviewer;

import com.sec.bestreviewer.command.ModifyCommand;
import com.sec.bestreviewer.data.*;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrinterTest {
    Printer printer;
    StringBuilder stringBuilder;

    @Test
    void testAddStringNothing() {
        stringBuilder = new StringBuilder();
        printer = new Printer("hello.txt", stringBuilder);
        assertThrows(NullPointerException.class, () -> {
            printer.add(null);
        });
    }

    @Test
    void testWriteOutputFileWhenAlreadyFileExist() throws IOException {
        stringBuilder = new StringBuilder();
        stringBuilder.append("FirstMessage");
        printer = new Printer("output.txt", stringBuilder);
        printer.printOutputFile();

        stringBuilder = new StringBuilder();
        stringBuilder.append("SecondMessage");
        printer = new Printer("output.txt", stringBuilder);
        printer.printOutputFile();

        InputStream input = new FileInputStream("output.txt");
        byte[] result = new byte[13];
        input.read(result);
        assertEquals("SecondMessage", new String(result));
    }

    @Test
    @DisplayName("MOD -p 옵션 테스트. column 값이 변경되기 전의 record 를 출력한다.")
    void testPrintMODCommandWithPrintOption() throws IOException {
        EmployeeStore employeeStore = mock(EmployeeStore.class);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("18050301", "TEST KIM", "CL2", "010-9777-6055", "19980906", "PRO"));

        when(employeeStore.modify(any(), eq("name"), eq("KIHYUN CHUNG"))).thenReturn(employeeList);

        CommandData commandData = new CommandData();
        commandData.setSearchDataList(Collections.singletonList(
                new SearchData(SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "TEST KIM")));
        commandData.setModifyData(new ModifyData("name", "KIHYUN CHUNG"));
        commandData.setPrintOption(PrintOption.PRINT);
        ModifyCommand modifyCommand = new ModifyCommand(commandData);

        printer = new Printer("output.txt");
        printer.add(new CommandExecutor(employeeStore).execute(modifyCommand));
        printer.printOutputFile();

        InputStream input = new FileInputStream("output.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(input);
        Stream<String> streamOfString= new BufferedReader(inputStreamReader).lines();
        String streamToString = streamOfString.collect(Collectors.joining());
        assertEquals("MOD,18050301,TEST KIM,CL2,010-9777-6055,19980906,PRO", streamToString);
    }

    @Test
    @DisplayName("MOD 테스트. 출력 옵션이 적용되지 않은 경우에는 조건에 부합하는 record 수를 출력한다.")
    void testPrintMODCommandWithoutPrintOption() throws IOException {
        EmployeeStore employeeStore = mock(EmployeeStore.class);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("18050301", "TEST KIM", "CL2", "010-9777-6055", "19980906", "PRO"));
        employeeList.add(new Employee("18050302", "TEST KIM", "CL2", "010-9777-6055", "19980906", "PRO"));
        employeeList.add(new Employee("18050303", "TEST KIM", "CL2", "010-9777-6055", "19980906", "PRO"));

        when(employeeStore.modify(any(), eq("name"), eq("KIHYUN CHUNG"))).thenReturn(employeeList);

        CommandData commandData = new CommandData();
        commandData.setSearchDataList(Collections.singletonList(
                new SearchData(SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "TEST KIM")));
        commandData.setModifyData(new ModifyData("name", "KIHYUN CHUNG"));
        ModifyCommand modifyCommand = new ModifyCommand(commandData);

        printer = new Printer("output.txt");
        printer.add(new CommandExecutor(employeeStore).execute(modifyCommand));
        printer.printOutputFile();

        InputStream input = new FileInputStream("output.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(input);
        Stream<String> streamOfString= new BufferedReader(inputStreamReader).lines();
        String streamToString = streamOfString.collect(Collectors.joining());
        assertEquals("MOD,3", streamToString);
    }

    @Test
    @DisplayName("MOD 테스트. 출력하는 record 개수는 최대 5개이다.")
    void testPrintMODCommandWithoutPrintOptionAndTestMaxRecord() throws IOException {
        EmployeeStore employeeStore = mock(EmployeeStore.class);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("18050301", "TEST KIM", "CL2", "010-9777-6055", "19980906", "PRO"));
        employeeList.add(new Employee("18050302", "TEST KIM", "CL2", "010-9777-6055", "19980906", "PRO"));
        employeeList.add(new Employee("18050303", "TEST KIM", "CL2", "010-9777-6055", "19980906", "PRO"));
        employeeList.add(new Employee("18050304", "TEST KIM", "CL2", "010-9777-6055", "19980906", "PRO"));
        employeeList.add(new Employee("18050305", "TEST KIM", "CL2", "010-9777-6055", "19980906", "PRO"));
        employeeList.add(new Employee("18050306", "TEST KIM", "CL2", "010-9777-6055", "19980906", "PRO"));

        when(employeeStore.modify(any(), eq("name"), eq("KIHYUN CHUNG"))).thenReturn(employeeList);

        CommandData commandData = new CommandData();
        commandData.setSearchDataList(Collections.singletonList(
                new SearchData(SearchOption.FULL_NAME, InqualitySignOption.NONE, "name", "TEST KIM")));
        commandData.setModifyData(new ModifyData("name", "KIHYUN CHUNG"));
        commandData.setPrintOption(PrintOption.PRINT);
        ModifyCommand modifyCommand = new ModifyCommand(commandData);

        printer = new Printer("output.txt");
        printer.add(new CommandExecutor(employeeStore).execute(modifyCommand));
        printer.printOutputFile();

        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("output.txt"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        assertEquals("MOD,18050301,TEST KIM,CL2,010-9777-6055,19980906,PRO\n" +
                "MOD,18050302,TEST KIM,CL2,010-9777-6055,19980906,PRO\n" +
                "MOD,18050303,TEST KIM,CL2,010-9777-6055,19980906,PRO\n" +
                "MOD,18050304,TEST KIM,CL2,010-9777-6055,19980906,PRO\n" +
                "MOD,18050305,TEST KIM,CL2,010-9777-6055,19980906,PRO\n",  stringBuilder.toString());
    }
}
