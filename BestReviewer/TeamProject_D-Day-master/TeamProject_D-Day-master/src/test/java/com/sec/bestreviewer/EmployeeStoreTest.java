package com.sec.bestreviewer;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.store.Injection;
import com.sec.bestreviewer.util.Options;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeStoreTest {

    EmployeeStore employeeStore = Injection.provideEmployeeStore();

    @Nested
    class AddTest {
        @ParameterizedTest
        @ValueSource(ints ={1, 100, 10000, 100000})
        void testAddSimple(int numOfEmployee){
            for (int i = 0; i < numOfEmployee; i++) {
                final String employeeNumber = Integer.toString(90_000000 + i);
                employeeStore.add(
                        new Employee(employeeNumber, "SEO KFI", "CL1", "010-1234-5678", "20190101", "ADV"));
            }
            assertEquals(numOfEmployee, employeeStore.count());
        }

        @Test
        void testAddWithDuplicatedEmployeeNumber(){
            employeeStore.add(new Employee("90001234", "SEO KFI", "CL1", "010-1234-5678", "20190101", "ADV"));
            assertEquals(1, employeeStore.count());

            employeeStore.add(new Employee("90001234", "SEO KFI", "CL1", "010-1234-5678", "20190101", "ADV"));
            assertEquals(1, employeeStore.count());
        }

        @ParameterizedTest
        @ValueSource(ints ={100000, 1000000})
        void testAddWithManyEmployees(int numOfEmployee){
            for (int i = 0; i < numOfEmployee; i++) {
                final String employeeNumber = Integer.toString(90_000000 + i);
                employeeStore.add(
                        new Employee(employeeNumber, "SEO KFI", "CL1", "010-1234-5678", "20190101", "ADV"));
            }
            assertEquals(numOfEmployee, employeeStore.count());
        }
    }

    @Nested
    class DeleteTest {
        public static final int NUM_OF_EMPLOYEE = 10000;
        public static final String BASE_EMPLOYEE_NUMBER = "90000000";
        @BeforeEach
        void beforeEach() {
            for (int i = 0; i < NUM_OF_EMPLOYEE; i++) {
                final String employeeNumber = Integer.toString(Integer.valueOf(BASE_EMPLOYEE_NUMBER) + i);
                employeeStore.add(
                        new Employee(employeeNumber, "SAMSUNG KIM", "CL1", "010-1234-5678", "20190101", "ADV"));
            }
        }

        @Test
        void testDeleteWithList() {
            List<Employee> deleteList = new ArrayList<>();
            int deleteEmployee = 5;
            for(int i = 0; i < deleteEmployee; i++)
                deleteList.add(new Employee(Integer.toString(Integer.parseInt(BASE_EMPLOYEE_NUMBER) + i), "SAMSUNG KIM", "CL1", "010-1234-5678", "20190101", "ADV"));
            employeeStore.delete(deleteList);
            assertEquals(NUM_OF_EMPLOYEE-deleteEmployee, employeeStore.count());
        }

        @Test
        void testDeleteWithEmployeeNumber(){
            employeeStore.delete(EmployeeStore.FIELD_EMPLOYEE_NUMBER, BASE_EMPLOYEE_NUMBER);
            assertEquals(NUM_OF_EMPLOYEE-1, employeeStore.count());
        }

        @Test
        void testDeleteWithEmployeeNumber_NoneDeletedEmployee(){
            employeeStore.delete(EmployeeStore.FIELD_EMPLOYEE_NUMBER, "7777");
            assertEquals(NUM_OF_EMPLOYEE, employeeStore.count());
        }

        @Test
        void testDeleteWithName(){
            employeeStore.delete(EmployeeStore.FIELD_NAME, "SAMSUNG KIM");
            assertEquals(0, employeeStore.count());
        }

        @Test
        void testDeleteWithName_NoneDeletedEmployee(){
            employeeStore.delete(EmployeeStore.FIELD_NAME, "SAMSUNG NA");
            assertEquals(NUM_OF_EMPLOYEE, employeeStore.count());
        }

        @Test
        void testDeleteWithCareerLevel(){
            employeeStore.delete(EmployeeStore.FIELD_CAREER_LEVEL, "CL1");
            assertEquals(0, employeeStore.count());
        }

        @Test
        void testDeleteWithCareerLevel_NoneDeletedEmployee(){
            employeeStore.delete(EmployeeStore.FIELD_CAREER_LEVEL, "CL5");
            assertEquals(NUM_OF_EMPLOYEE, employeeStore.count());
        }

        @Test
        void testDeleteWithPhone(){
            employeeStore.delete(EmployeeStore.FIELD_PHONE_NUMBER, "010-1234-5678");
            assertEquals(0, employeeStore.count());
        }

        @Test
        void testDeleteWithPhone_NoneDeletedEmployee(){
            employeeStore.delete(EmployeeStore.FIELD_PHONE_NUMBER, "011-1234-5678");
            assertEquals(NUM_OF_EMPLOYEE, employeeStore.count());
        }

        @Test
        void testDeleteWithBirthday(){
            employeeStore.delete(EmployeeStore.FIELD_BIRTH_DAY, "20190101");
            assertEquals(0, employeeStore.count());
        }

        @Test
        void testDeleteWithBirthday_NoneDeletedEmployee(){
            employeeStore.delete(EmployeeStore.FIELD_BIRTH_DAY, "30210101");
            assertEquals(NUM_OF_EMPLOYEE, employeeStore.count());
        }

        @Test
        void testDeleteWithCerti(){
            employeeStore.delete(EmployeeStore.FIELD_CERTI, "ADV");
            assertEquals(0, employeeStore.count());
        }

        @Test
        void testDeleteWithCerti_NoneDeletedEmployee(){
            employeeStore.delete(EmployeeStore.FIELD_CERTI, "EX");
            assertEquals(NUM_OF_EMPLOYEE, employeeStore.count());
        }
    }

    @Nested
    class ModifyTest {
        Employee employee = new Employee("90001234", "Reviewer KIM", "CL1", "010-1234-5678", "20000101", "PRO");

        @BeforeEach
        void beforeEach() {
            employeeStore.add(employee);
        }

        @Test
        void testModifyName() {
            employeeStore.modify(EmployeeStore.FIELD_NAME, "BestReviewer", Arrays.asList(employee));
            assertEquals("BestReviewer", employee.getName());
        }

        @Test
        void testModifyBirthday() {
            employeeStore.modify(EmployeeStore.FIELD_BIRTH_DAY, "19900101", Arrays.asList(employee));
            assertEquals("19900101", employee.getBirthday());
        }

        @Test
        void testModifyCareerLevel() {
            employeeStore.modify(EmployeeStore.FIELD_CAREER_LEVEL, "CL2", Arrays.asList(employee));
            assertEquals("CL2", employee.getCareerLevel());
        }

        @Test
        void testModifyPhoneNumber() {
            List<Employee> list = employeeStore.modify(EmployeeStore.FIELD_PHONE_NUMBER, "010-1234-9999", Arrays.asList(employee));
            assertEquals("010-1234-9999", employee.getPhoneNumber());
            assertEquals(1, list.size());
            assertEquals(1, employeeStore.count());
        }

        @Test
        void testModifyCerti() {
            employeeStore.modify(EmployeeStore.FIELD_CERTI, "EX", Arrays.asList(employee));
            assertEquals("EX", employee.getCerti());
        }
    }

    @Nested
    class SearchTest {
        Employee employee = new Employee("90001234", "Reviewer KIM", "CL1", "010-1234-5678", "20000101", "PRO");

        @BeforeEach
        void beforeEach() {
            employeeStore.add(employee);
        }

        @Test
        void testSearchEmployeeNumberWithSe() {
            List<Employee> list = employeeStore.search(EmployeeStore.FIELD_EMPLOYEE_NUMBER, "20001234", "-se");
            assertEquals("90001234", list.get(0).getEmployeeNumber());
        }

        @Test
        void testSearchEmployeeNumberWithG() {
            List<Employee> list = employeeStore.search(EmployeeStore.FIELD_EMPLOYEE_NUMBER, "90001111", "-g");
            assertEquals("90001234", list.get(0).getEmployeeNumber());
        }


        @Test
        void testSearchNameWithGe() {
            List<Employee> list = employeeStore.search(EmployeeStore.FIELD_NAME, "Reviewee", "-ge");
            assertEquals("Reviewer KIM", list.get(0).getName());
        }

        @Test
        void testSearchClWithS() {
            List<Employee> list = employeeStore.search(EmployeeStore.FIELD_CAREER_LEVEL, "CL3", "-s");
            assertEquals("CL1", list.get(0).getCareerLevel());
        }

        @Test
        void testSearchPhoneNumberWithSer() {
            List<Employee> list = employeeStore.search(EmployeeStore.FIELD_PHONE_NUMBER, "010-1234-5679", "-se");
            assertEquals("010-1234-5678", list.get(0).getPhoneNumber());
        }

        @Test
        void testSearchBirthdayWithGe() {
            List<Employee> list = employeeStore.search(EmployeeStore.FIELD_BIRTH_DAY, "19990101", "-ge");
            assertEquals("20000101", list.get(0).getBirthday());
        }

        @Test
        void testSearchCertiWithGe() {
            List<Employee> list = employeeStore.search(EmployeeStore.FIELD_CERTI, "PRO", "-ge");
            assertEquals("PRO", list.get(0).getCerti());
        }

        @Test
        void testSearchBirthdayWithYear() {
            List<Employee> list = employeeStore.search(EmployeeStore.FIELD_BIRTH_DAY, "2000", Options.EMPTY_OPTION, "-y");
            assertEquals("20000101", list.get(0).getBirthday());
        }

        @Test
        void testSearchBirthdayWithMonth() {
            List<Employee> list = employeeStore.search(EmployeeStore.FIELD_BIRTH_DAY, "01", Options.EMPTY_OPTION, "-m");
            assertEquals("20000101", list.get(0).getBirthday());
        }


        @Test
        void testSearchBirthdayWithDay() {
            List<Employee> list = employeeStore.search(EmployeeStore.FIELD_BIRTH_DAY, "01", Options.EMPTY_OPTION, "-d");
            assertEquals("20000101", list.get(0).getBirthday());
        }

        @Test
        void testSearchPhoneNumberWithMiddleNumber() {
            List<Employee> list = employeeStore.search(EmployeeStore.FIELD_PHONE_NUMBER, "1234", " ", "-m");
            assertEquals("010-1234-5678", list.get(0).getPhoneNumber());
        }

        @Test
        void testSearchPhoneNumberWithMiddleNumber_NoResult() {
            List<Employee> list = employeeStore.search(EmployeeStore.FIELD_PHONE_NUMBER, "5678", " ", "-m");
            assertEquals(0, list.size());
        }

        @Test
        void testSearchPhoneNumberWithLastNumber() {
            List<Employee> list = employeeStore.search(EmployeeStore.FIELD_PHONE_NUMBER, "5678", " ", "-l");
            assertEquals("010-1234-5678", list.get(0).getPhoneNumber());
        }

        @Test
        void testSearchPhoneNumberWithLastNumber_NoResult() {
            List<Employee> list = employeeStore.search(EmployeeStore.FIELD_PHONE_NUMBER, "1234", " ", "-l");
            assertEquals(0, list.size());
        }
    }
}