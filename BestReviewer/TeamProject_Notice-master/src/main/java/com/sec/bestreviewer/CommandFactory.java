package com.sec.bestreviewer;

import com.sec.bestreviewer.command.*;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.OptionParser;
import com.sec.bestreviewer.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandFactory {
    static final String CMD_ADD = "ADD";
    public static final String CMD_DEL = "DEL";
    public static final String CMD_SCH = "SCH";
    public static final String CMD_CNT = "CNT";

    private static final String EMPLOYEE_NUMBER = "employeeNum";
    private static final String NAME = "name";
    private static final String CAREER_LEVEL = "cl";
    private static final String PHONE_NUMBER = "phoneNum";
    private static final String BIRTHDAY = "birthday";

    private static final Map<String, String> fieldMap = new HashMap<>();

    static {
        fieldMap.put(EMPLOYEE_NUMBER, EmployeeStore.FIELD_EMPLOYEE_NUMBER);
        fieldMap.put(NAME, EmployeeStore.FIELD_NAME);
        fieldMap.put(CAREER_LEVEL, EmployeeStore.FIELD_CAREER_LEVEL);
        fieldMap.put(PHONE_NUMBER, EmployeeStore.FIELD_PHONE_NUMBER);
        fieldMap.put(BIRTHDAY, EmployeeStore.FIELD_BIRTH_DAY);
    }

    static Command buildCommand(String cmd, List<String> options, List<String> params)
            throws IllegalArgumentException {
        final OptionParser optionParser = new OptionParser(options);

        switch (cmd) {
            case CMD_ADD:
                final Employee employee =
                        new Employee(params.get(0), params.get(1), params.get(2), params.get(3), params.get(4));
                return new AddCommand(optionParser, employee);
            case CMD_DEL:
                return new DeleteCommand(optionParser, getConditionMapFromParams(params));
            case CMD_SCH:
                return new SearchCommand(optionParser, getConditionMapFromParams(params));
            case CMD_CNT:
                return new CountCommand();
        }
        throw new IllegalArgumentException("Wrong command");
    }

    private static Pair<String, String> getConditionMapFromParams(List<String> params) {
        final String fieldName = fieldMap.get(params.get(0));
        if (fieldName == null) {
            throw new IllegalArgumentException("Wrong field: " + params.get(0));
        }
        return Pair.create(fieldMap.get(params.get(0)), params.get(1));
    }
}