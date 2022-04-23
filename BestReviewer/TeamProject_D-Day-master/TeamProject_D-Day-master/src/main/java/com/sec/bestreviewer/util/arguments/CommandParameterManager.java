package com.sec.bestreviewer.util.arguments;

import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CommandParameterManager {

    private static final String EMPLOYEE_NUMBER = "employeeNum";
    private static final String NAME = "name";
    private static final String CAREER_LEVEL = "cl";
    private static final String PHONE_NUMBER = "phoneNum";
    private static final String BIRTHDAY = "birthday";
    private static final String CERTI = "certi";
    private static final Map<String, String> fieldMap = new HashMap<>();
    public static final int A_PAIR_OF_PRAMETERS = 2;

    static {
        fieldMap.put(EMPLOYEE_NUMBER, EmployeeStore.FIELD_EMPLOYEE_NUMBER);
        fieldMap.put(NAME, EmployeeStore.FIELD_NAME);
        fieldMap.put(CAREER_LEVEL, EmployeeStore.FIELD_CAREER_LEVEL);
        fieldMap.put(PHONE_NUMBER, EmployeeStore.FIELD_PHONE_NUMBER);
        fieldMap.put(BIRTHDAY, EmployeeStore.FIELD_BIRTH_DAY);
        fieldMap.put(CERTI, EmployeeStore.FIELD_CERTI);
    }

    protected int mode;
    protected List<String> params;

    protected abstract boolean isConditionParameter(int parseIndex);

    public List<Pair> getConditionParameters() {
        int parserIndex = 0;
        List<Pair> conditionParamList = new ArrayList<>();

        for (int i = 0; i < params.size(); i += A_PAIR_OF_PRAMETERS) {
            Pair<String, String> currentPair = getCurrentParameter(params, i);

            if (isConditionParameter(parserIndex))
                conditionParamList.add(currentPair);
            else
                break;

            parserIndex++;
        }

        return conditionParamList;
    }

    public Pair<String, String> getTargetParameter() {
        Pair<String, String> targetParam = null;
        int parserIndex = 0;

        for (int i = 0; i < params.size(); i += A_PAIR_OF_PRAMETERS) {
            Pair<String, String> currentPair = getCurrentParameter(params, i);

            if (!isConditionParameter(parserIndex))
                targetParam = currentPair;

            parserIndex++;
        }

        return targetParam;
    }

    private Pair<String, String> getCurrentParameter(List<String> params, int i) {
        List<String> currentParams = new ArrayList<>();
        currentParams.add(params.get(i));
        currentParams.add(params.get(i + 1));
        return getConditionMapFromParams(currentParams);
    }

    protected static Pair<String, String> getConditionMapFromParams(List<String> params) {
        final String fieldName = fieldMap.get(params.get(0));
        if (fieldName == null) {
            throw new IllegalArgumentException("Wrong field: " + params.get(0));
        }
        return Pair.create(fieldMap.get(params.get(0)), params.get(1));
    }
}
