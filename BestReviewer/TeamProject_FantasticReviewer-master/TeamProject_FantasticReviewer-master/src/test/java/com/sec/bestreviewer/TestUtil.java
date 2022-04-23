package com.sec.bestreviewer;

import com.sec.bestreviewer.base.ConditionParameter;
import com.sec.bestreviewer.base.ConditionValue;
import com.sec.bestreviewer.base.FieldId;
import com.sec.bestreviewer.base.OptionId;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TestUtil {

    public static final String EMPLOYEE_NUMBER_GETTER = "getEmployeeNumber";
    public static final String NAME_GETTER = "getName";
    public static final String CAREER_LEVEL_GETTER = "getCareerLevel";
    public static final String PHONE_NUMBER_GETTER = "getPhoneNumber";
    public static final String BIRTHDAY_GETTER = "getBirthday";
    public static final String UNDEFINED_GETTER = "UndefinedField";

    public static final String NONE_RESULT = ",NONE";
    public static final String ONE_RESULT = ",1";
    public static final String COMMA_RESULT = ",";

    public static final String[] EMPLOYEE_LIST = {
            "18050301,KYUMOK KIM,CL1,010-9777-6051,19980901,ADV",
            "18050302,KYUMOK LIM,CL2,010-9777-6052,19980902,PRO",
            "18050303,KYUMOK SIM,CL3,010-9777-6053,19980903,EX",
            "18050304,KYUMOK LEE,CL4,010-9777-6054,19980904,ADV"};

    public static final String[] EMPLOY_LIST_SAME_VALUE = {
            "25050301,KYUMOK KIM,CL2,010-9777-6051,19980901,ADV",
            "24050301,KYUMOK KEM,CL2,010-9777-6052,19980902,ADV",
            "22050301,KYUMOK KAM,CL2,010-9777-6053,19980905,EX",
            "11050302,KYUMOK LIM,CL2,010-9777-6054,19980905,EX",
            "99050303,KYUMOK SIM,CL2,010-9777-6054,19980905,PRO",
            "90050304,KYUMOK SIM,CL2,010-9777-6054,19980905,PRO"};

    public List<Employee> createTestEmployeeList(String[] testEmployList) {
        final List<Employee> employeeList = new ArrayList<>();
        for (String testEmploy : testEmployList)
            appendEmployeeToTestList(employeeList, testEmploy);
        return employeeList;
    }

    public void addTestEmployee(EmployeeStore employeeStore, List<Employee> employeeList) {
        for (Employee employee : employeeList)
            employeeStore.add(employee);
    }

    private void appendEmployeeToTestList(List<Employee> employeeList, String testEmploy) {
        StringTokenizer stringTokenizer = new StringTokenizer(testEmploy, ",");
        if (stringTokenizer.countTokens() == 6)
            employeeList.add(new Employee(stringTokenizer.nextToken(), stringTokenizer.nextToken(),
                    stringTokenizer.nextToken(), stringTokenizer.nextToken(),
                    stringTokenizer.nextToken(), stringTokenizer.nextToken()));
    }

    public String findTestValue(Employee employee, String fieldName) {
        try {
            Class<?> employClass = Class.forName(employee.getClass().getName());
            Method targetMethod = employClass.getDeclaredMethod(findGetMethodName(fieldName));
            return (String) targetMethod.invoke(employee);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException from Class.forName()");
        } catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException from getDeclaredMethod()");
        } catch (InvocationTargetException e) {
            System.out.println("InvocationTargetException from invoke()");
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException from invoke()");
        }
        return "";
    }

    private String findGetMethodName(String fieldName) {
        switch (fieldName) {
            case FieldId.FIELD_EMPLOYEE_NUMBER:
                return EMPLOYEE_NUMBER_GETTER;
            case FieldId.FIELD_NAME:
                return NAME_GETTER;
            case FieldId.FIELD_CAREER_LEVEL:
                return CAREER_LEVEL_GETTER;
            case FieldId.FIELD_PHONE_NUMBER:
                return PHONE_NUMBER_GETTER;
            case FieldId.FIELD_BIRTH_DAY:
                return BIRTHDAY_GETTER;
            default:
                return UNDEFINED_GETTER;
        }
    }

    public TokenGroup createTokenGroupWithPrintOption(String command, Employee employee, String fieldName) {
        String value = findTestValue(employee, fieldName);
        ConditionValue conditionValue = new ConditionValue(fieldName, value, OptionId.OPTION_PRINT, OptionId.OPTION_NONE, OptionId.OPTION_NONE);
        return createConditionParameter(command, conditionValue);
    }

    public TokenGroup createTokenGroupWithPrintOption(String command, String fieldName, String value) {
        ConditionValue conditionValue = new ConditionValue(fieldName, value, OptionId.OPTION_PRINT, OptionId.OPTION_NONE, OptionId.OPTION_NONE);
        return createConditionParameter(command, conditionValue);
    }

    public TokenGroup createTokenGroupWithOutPrint(String command, Employee employee, String fieldName) {
        String value = findTestValue(employee, fieldName);
        ConditionValue conditionValue = new ConditionValue(fieldName, value, OptionId.OPTION_NONE, OptionId.OPTION_NONE, OptionId.OPTION_NONE);
        return createConditionParameter(command, conditionValue);
    }

    @NotNull
    private TokenGroup createConditionParameter(String command, ConditionValue conditionValue) {
        ConditionParameter conditionParameter = new ConditionParameter(conditionValue, null, null, null);
        return new TokenGroup(command, conditionParameter);
    }
}
