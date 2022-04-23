package com.sec.bestreviewer.command;


import com.sec.bestreviewer.store.Condition;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;

import java.util.Collections;
import java.util.List;

public class AddCommand extends CommandImpl {

    private static final int INDEX_EMPLOYEE_NUMBER = 2;
    private static final int INDEX_NAME = 3;
    private static final int INDEX_CAREER_LEVEL = 4;
    private static final int INDEX_PHONE_NUMBER = 5;
    private static final int INDEX_BIRTHDAY = 6;
    private static final int INDEX_CERT = 7;

    public AddCommand(CommandDTO commandDTO) {
        super(commandDTO);
    }

    @Override
    public List<String> execute(EmployeeStore employeeStore) {
        List<String> employFields = commandDTO.getEmployeeDataList();
        Employee employee = new Employee(employFields.get(INDEX_EMPLOYEE_NUMBER), employFields.get(INDEX_NAME), employFields.get(INDEX_CAREER_LEVEL),
                employFields.get(INDEX_PHONE_NUMBER), employFields.get(INDEX_BIRTHDAY), employFields.get(INDEX_CERT));

        employeeStore.add(employee);
        return Collections.emptyList();
    }

    @Override
    Condition getFirstCondition(List<String> tokenList) {
        return null;
    }

    @Override
    Condition getSecondCondition(List<String> tokenList) {
        return null;
    }

    @Override
    Condition getModifyCondition(List<String> tokenList) {
        return null;
    }
}
