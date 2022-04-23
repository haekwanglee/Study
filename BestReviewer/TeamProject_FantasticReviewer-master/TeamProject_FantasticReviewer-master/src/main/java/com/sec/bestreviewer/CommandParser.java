package com.sec.bestreviewer;


import com.sec.bestreviewer.base.*;
import com.sec.bestreviewer.store.Employee;

import java.util.Arrays;
import java.util.List;


public class CommandParser {

    private String[] splitLine;
    private String type;
    private ConditionValue conditionValue1;
    private ConditionValue conditionValue2;
    private Value modifyValue;
    private ConditionParameter conditionParameter;

    public TokenGroup parse(String line) throws IllegalArgumentException {
        splitLine = line.split(TokenId.COMMA, TokenId.TOKEN_LIMIT);
        type = splitLine[ConditionParameter.TYPE_IDX];

        if (splitLine.length < TokenId.MIN_TOKENS_NUM) {
            throw new IllegalArgumentException("wrong command format");
        }
        switch (type){
            case CommandFactory.CMD_ADD:
                return addParse();
            case CommandFactory.CMD_MOD:
                return ModParse();
            default:
                return CmdParse();
        }
    }

    private TokenGroup addParse() {
        List<String> params = Arrays.asList(Arrays.copyOfRange(splitLine, TokenId.PARAM_FROM_IDX, splitLine.length));
        final Employee employee =
                new Employee(params.get(EmployeeId.EMPLOYEE_NUMBER_IDX),
                        params.get(EmployeeId.NAME_IDX),
                        params.get(EmployeeId.CAREER_LEVEL_IDX),
                        params.get(EmployeeId.PHONE_NUMBER_IDX),
                        params.get(EmployeeId.BIRTHDAY_IDX),
                        params.get(EmployeeId.CERTI_IDX));

        return new TokenGroup(type, employee);
    }

    private TokenGroup CmdParse() {
        makeFirstConditionValue();

        if (isAndOr()) {
            makeSecondConditionValue();
        }

        conditionParameter = new ConditionParameter(conditionValue1,
                conditionValue2,
                modifyValue,
                isAndOr()? splitLine[ModifyValue.TO_MODIFY_FIELD_NO_AND_OR] : OptionId.OPTION_NONE);
        return new TokenGroup(type, conditionParameter);
    }


    private TokenGroup ModParse() {
        makeFirstConditionValue();
        if (isAndOr()) {
            makeSecondConditionValue();
        }
        makeModifyValue();
        conditionParameter = new ConditionParameter(conditionValue1,
                conditionValue2,
                modifyValue,
                isAndOr()? splitLine[ModifyValue.TO_MODIFY_FIELD_NO_AND_OR] : OptionId.OPTION_NONE);
        return new TokenGroup(type, conditionParameter);
    }

    private void makeModifyValue() {

        if (splitLine.length < ModifyValue.MODIFY_LEN_NO_AND_OR){
            modifyValue = new Value (splitLine[ModifyValue.TO_MODIFY_FIELD_NO_AND_OR], splitLine[ModifyValue.TO_MODIFY_VALUE_NO_AND_OR]);
        }
        else{
            modifyValue = new Value (splitLine[ConditionParameter.TO_MODIFY_FIELDNAME], splitLine[ConditionParameter.TO_MODIFY_VALUE]);
        }
    }

    private void makeFirstConditionValue() {
        conditionValue1 = new ConditionValue(splitLine[ConditionParameter.FIRST_FIELDNAME],
                splitLine[ConditionParameter.FIRST_VALUE],
                splitLine[ConditionParameter.FIRST_OPTION_1],
                splitLine[ConditionParameter.FIRST_OPTION_2],
                splitLine[ConditionParameter.FIRST_OPTION_3]);

        conditionValue2 = new ConditionValue(OptionId.OPTION_NONE,
                OptionId.OPTION_NONE,
                OptionId.OPTION_NONE,
                OptionId.OPTION_NONE,
                OptionId.OPTION_NONE);
    }

    private void makeSecondConditionValue() {
        conditionValue2 = new ConditionValue(splitLine[ConditionParameter.SECOND_FIELDNAME],
                splitLine[ConditionParameter.SECOND_VALUE],
                splitLine[ConditionParameter.SECOND_OPTION_1],
                splitLine[ConditionParameter.SECOND_OPTION_2],
                splitLine[ConditionParameter.SECOND_OPTION_3]);
    }

    private boolean isAndOr() {
        return (splitLine.length > TokenId.MIN_TOKENS_FOR_CMD) &&
                (splitLine[ModifyValue.TO_MODIFY_FIELD_NO_AND_OR].equals(OperatorId.AND) ||
                        splitLine[ModifyValue.TO_MODIFY_FIELD_NO_AND_OR].equals(OperatorId.OR));
    }
}
