package com.sec.bestreviewer.approvalV2;

import com.sec.bestreviewer.EmployeeManagement;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * Test For added single options for phoneNumber, name, and birth
 */
public class EmployeeManagementTest {

    private static final String PATH = "./src/test/java/com/sec/bestreviewer/approvalV2/";

    @Test
    @DisplayName("성명의 이름(first name)이 E 보다 사전 순으로 뒤이거나 같은 record 검색")
    public void searchTest1() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.searchTest1.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.searchTest1.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("성명의 이름(first name)이 E 보다 사전 순으로 큰 record 검색")
    public void testGreat() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.testGreat.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.testGreat.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("경력개발단계가 CL3 보다 작은 record를 검색")
    public void searchTest2() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.searchTest2.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.searchTest2.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("전화번호의 중간자리가 5555 보다 작거나 같은 record를 검색")
    public void searchTest3() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.searchTest3.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.searchTest3.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("생년월일 중 연도가 2000보다 크거나 같은 record를 검색")
    public void searchTest4() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.searchTest4.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.searchTest4.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("certi 가 PRO 보다 높거나 같은 record를 검색")
    public void searchTest5() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.searchTest5.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.searchTest5.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("이름(First Name)이 F인 record를 삭제")
    public void nameOptionTest1() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.nameOptionTest1.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.nameOptionTest1.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("성(Last Name)이 A인 전화번호를 010-0000-0000 으로 변경")
    public void nameOptionTest2() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.nameOptionTest2.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.nameOptionTest2.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("전화 번호의 중간자리가 2222인 record를 삭제")
    public void phoneNumberTest1() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.phoneNumberTest1.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.phoneNumberTest1.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("전화 번호의 뒷자리가 6666인 record의 certi를 PRO로 변경")
    public void phoneNumberTest2() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.phoneNumberTest2.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.phoneNumberTest2.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("생년월일의 연도가 2001인 record의 성명을 Z Z로 변경")
    public void birthTest1() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.birthTest1.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.birthTest1.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("생년월일의 월이 11인 record 의 경력개발단계를 CL4로 변경")
    public void birthTest2() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.birthTest2.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.birthTest2.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("생년월일의 일이 11인 record 의 certi를 EX로 변경")
    public void birthTest3() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.birthTest3.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.birthTest3.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("성명의 이름이 F이고, 경력개발단계가 CL3인 record를 삭제")
    public void multipleOptionDeleteTest1() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.multipleOptionDeleteTest1.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.multipleOptionDeleteTest1.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName(" 전화번호의 가운데가 3333이거나, 생년월일의 연도가 1900인 record를 삭제")
    public void multipleOptionDeleteTest2() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.multipleOptionDeleteTest2.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.multipleOptionDeleteTest2.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("경력개발단계가 CL3, 생년월일의 월이 01인 record의 성명을 Z Z로 변경")
    public void multipleOptionModTest1() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.multipleOptionModTest1.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.multipleOptionModTest1.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("생년월일의 일이 31이거나, certi가 PRO인 record의 생년월일을 20200202로 변경")
    public void multipleOptionModTest2() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.multipleOptionModTest2.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.multipleOptionModTest2.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("성명의 이름이 F이고, 경력개발단계가 CL4인 record를 검색")
    public void multipleOptionSearchTest1() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.multipleOptionSearchTest1.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.multipleOptionSearchTest1.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("전화번호의 가운데가 4444이거나, 생년월일의 연도가 2001인 record를 검색")
    public void multipleOptionSearchTest2() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.multipleOptionSearchTest2.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.multipleOptionSearchTest2.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("전화번호의 가운데가 4444이거나, 생년월일의 연도가 2001보다 작은 record를 검색")
    public void multipleOptionSearchTest3() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.multipleOptionSearchTest3.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.multipleOptionSearchTest3.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("And 조건에서 하나를 못 찾을 경우 정상처리 되는지 확인")
    public void multipleOptionSearchTest4() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.multipleOptionSearchTest4.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.multipleOptionSearchTest4.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("And 조건에서 둘다 못 찾을 경우 정상처리 되는지 확인")
    public void multipleOptionSearchTest5() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.multipleOptionSearchTest5.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.multipleOptionSearchTest5.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("OR 조건에서 하나를 못 찾을 경우 정상처리 되는지 확인")
    public void multipleOptionSearchTest6() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.multipleOptionSearchTest6.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.multipleOptionSearchTest6.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }

    @Test
    @DisplayName("OR 조건에서 둘다 못 찾을 경우 정상처리 되는지 확인")
    public void multipleOptionSearchTest7() throws Exception {
        final String inputFileName = PATH + "EmployeeManagementTest.multipleOptionSearchTest7.input.txt";
        final String outputFileName = PATH + "EmployeeManagementTest.multipleOptionSearchTest7.output.txt";

        EmployeeManagement employeeManagement = new EmployeeManagement();

        File outputFile = new File(outputFileName);
        String[] args = {inputFileName, outputFileName};

        employeeManagement.run(args);

        Approvals.verify(outputFile);
    }
}
