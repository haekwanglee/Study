package com.sec.bestreviewer;

import com.sec.bestreviewer.command.*;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.OptionParser;
import com.sec.bestreviewer.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandFactory {
    static final String CMD_ADD = "ADD";
    public static final String CMD_DEL = "DEL";
    public static final String CMD_SCH = "SCH";
    public static final String CMD_CNT = "CNT";
    public static final String CMD_MOD = "MOD";

    private static final String EMPLOYEE_NUMBER = "employeeNum";
    private static final String NAME = "name";
    private static final String CAREER_LEVEL = "cl";
    private static final String PHONE_NUMBER = "phoneNum";
    private static final String BIRTHDAY = "birthday";
    private static final String CERTI = "certi";

    private static final Map<String, String> fieldMap = new HashMap<>();

    static {
        fieldMap.put(EMPLOYEE_NUMBER, EmployeeStore.FIELD_EMPLOYEE_NUMBER);
        fieldMap.put(NAME, EmployeeStore.FIELD_NAME);
        fieldMap.put(CAREER_LEVEL, EmployeeStore.FIELD_CAREER_LEVEL);
        fieldMap.put(PHONE_NUMBER, EmployeeStore.FIELD_PHONE_NUMBER);
        fieldMap.put(BIRTHDAY, EmployeeStore.FIELD_BIRTH_DAY);
        fieldMap.put(CERTI, EmployeeStore.FIELD_CERTI);
    }

    private static final int PARAM_SIZE_FOR_MOD = 3;
    private static final int PARAM_FILED_FIRST_CMD = 0;
    private static final int PARAM_VALUE_FIRST_CMD = 1;
    private static final int PARAM_FILED_SECOND_CMD = 2;
    private static final int PARAM_VALUE_SECOND_CMD = 3;

    private static final int EMPLOYEE_NUMBER_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int CAREER_LEVEL_INDEX = 2;
    private static final int PHONE_NUMBER_INDEX = 3;
    private static final int BIRTHDAY_INDEX = 4;
    private static final int CERTI_INDEX = 5;

    static Command buildCommand(String cmd, List<String> options, List<String> params)
            throws IllegalArgumentException {
        final OptionParser optionParser = new OptionParser(options);
        final boolean existAndOrOption = optionParser.isAndOperation() || optionParser.isOrOperation();

        switch (cmd) {
            case CMD_ADD:
                final Employee employee =
                        new Employee(params.get(EMPLOYEE_NUMBER_INDEX),
                                params.get(NAME_INDEX),
                                params.get(CAREER_LEVEL_INDEX),
                                params.get(PHONE_NUMBER_INDEX),
                                params.get(BIRTHDAY_INDEX),
                                params.get(CERTI_INDEX));
                return new AddCommand(optionParser, employee);
            case CMD_DEL:
                return new DeleteCommand(optionParser, getConditionMapFromParams(params, existAndOrOption));
            case CMD_SCH:
                return new SearchCommand(optionParser, getConditionMapFromParams(params, existAndOrOption));
            case CMD_CNT:
                return new CountCommand();
            case CMD_MOD:
                return new ModifyCommand(optionParser,getConditionMapFromParams(params, existAndOrOption),
                        getNewValueFromParams(params));
        }
        throw new IllegalArgumentException("Wrong command");
    }

    private static List<Pair<String, String>> getConditionMapFromParams(List<String> params, boolean existAndOrOption) {
        List<Pair<String, String>> retParams = new ArrayList<>();

        checkFieldName(params.get(PARAM_FILED_FIRST_CMD), params.get(PARAM_VALUE_FIRST_CMD));
        retParams.add(Pair.create(
                fieldMap.get(params.get(PARAM_FILED_FIRST_CMD)),
                params.get(PARAM_VALUE_FIRST_CMD)));

        if (existAndOrOption) {
            checkFieldName(params.get(PARAM_FILED_SECOND_CMD), params.get(PARAM_VALUE_SECOND_CMD));
            retParams.add(Pair.create(
                    fieldMap.get(params.get(PARAM_FILED_SECOND_CMD)),
                    params.get(PARAM_VALUE_SECOND_CMD)));
        }
        return retParams;
    }


    private static void checkFieldName(String field, String value) {
        final String fieldName;
        fieldName = fieldMap.get(field);
        if (fieldName == null) {
            throw new IllegalArgumentException("Wrong field: " + field);
        }
    }

    private static Pair<String, String> getNewValueFromParams(List<String> params) {
        if (params.size() < PARAM_SIZE_FOR_MOD) {
            throw new IllegalArgumentException("Wrong Params");
        }
        final int paramLastIndex = params.size() - 1;
        checkFieldName(params.get(paramLastIndex - 1), params.get(paramLastIndex));
        return Pair.create(fieldMap.get(params.get(paramLastIndex - 1)), params.get(paramLastIndex));
    }
}