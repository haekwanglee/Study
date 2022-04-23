package com.sec.bestreviewer;

import com.sec.bestreviewer.command.*;
import com.sec.bestreviewer.command.querycondition.QueryCondition;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.OptionParser;
import com.sec.bestreviewer.util.Pair;

import java.util.*;

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
        fieldMap.put(CERTI, EmployeeStore.FIELD_CERTIFICATION);
    }

    static class ParameterParser {

        public static final int QUERY_SIZE = 4;
        public Pair<String, String> newFieldValue;
        List<QueryCondition> queryConditions = new ArrayList<>();
        String joinOperator = "";

        void parse(String cmd, List<String> params) {
            if (CMD_ADD.equals(cmd) || CMD_CNT.equals(cmd))
                return;

            queryConditions.add(makeQuery(params));

            if(params.size() > 4 && ("-a".equals(params.get(4)) || "-o".equals(params.get(4)))) {
                queryConditions.add(makeQuery(params.subList(QUERY_SIZE + 1, QUERY_SIZE + 1 + QUERY_SIZE)));
                joinOperator = getJoinOperatorString(params.get(4));
            }
            if(CMD_MOD.equals(cmd)) {
                parseModValue(params);
            }
        }

        private void parseModValue(List<String> params) {
            String field;
            String value;
            if(params.size() > 4 && ("-a".equals(params.get(4)) || "-o".equals(params.get(4)))) {
                field = convertToStoreFieldName(params.get(QUERY_SIZE + 1 + QUERY_SIZE));
                value = params.get(QUERY_SIZE + 1 + QUERY_SIZE + 1);
            }
            else {
                field = convertToStoreFieldName(params.get(QUERY_SIZE));
                value = params.get(QUERY_SIZE + 1);
            }
            newFieldValue = Pair.create(field, value);
        }

        private String getJoinOperatorString(String operator) {
            switch (operator) {
                case "-o":
                    return "OR";
                case "-a":
                    return "AND";
                default:
                    return "";
            }
        }

        private QueryCondition makeQuery(List<String> params) {
            QueryCondition queryCondition = new QueryCondition();
            queryCondition.setOption2(params.get(0));
            queryCondition.setOption3(params.get(1));
            queryCondition.setColumn(convertToStoreFieldName(params.get(2)));
            queryCondition.setValue(params.get(3));
            return queryCondition;
        }

    }
    static Command buildCommand(String cmd, List<String> options, List<String> params)
            throws IllegalArgumentException {
        final OptionParser optionParser = new OptionParser(options);
        ParameterParser parser = new ParameterParser();

        switch (cmd) {
            case CMD_ADD:
                final Employee employee =
                        new Employee(params.get(2), params.get(3), params.get(4), params.get(5), params.get(6), params.get(7));
                return new AddCommand(optionParser, employee);
            case CMD_DEL:
                parser.parse(cmd, params);
                return new DeleteCommand(optionParser, parser.queryConditions, parser.joinOperator);
            case CMD_SCH:
                parser.parse(cmd, params);
                return new SearchCommand(optionParser, parser.queryConditions, parser.joinOperator);
            case CMD_CNT:
                return new CountCommand();
            case CMD_MOD:
                parser.parse(cmd, params);
                return new ModifyCommand(optionParser, parser.queryConditions, parser.joinOperator, parser.newFieldValue);
        }
        throw new IllegalArgumentException("Wrong command");
    }

    private static String convertToStoreFieldName(String fieldParam) {
        final String fieldName = fieldMap.get(fieldParam);
        if (fieldName == null) {
            throw new IllegalArgumentException("Wrong field: " +  fieldParam);
        }
        return fieldName;
    }
}