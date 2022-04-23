package com.sec.bestreviewer.command;

import com.sec.bestreviewer.store.Condition;
import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.Pair;
import com.sec.bestreviewer.util.ResultStringFormatter;

import java.util.List;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;

public class ModifyCommand extends CommandImpl {

    private static final String MODIFY_OPTION = " ";

    public ModifyCommand(CommandDTO commandDTO) {
        super(commandDTO);
        this.setModelParams();
    }

    @Override
    public List<String> execute(EmployeeStore employeeStore) {
        final List<Employee> employeeList = employeeStore.modify(modelParams);


        if (commandDTO.isPrintOptionOn()) {
            return ResultStringFormatter.getEmployeeListToFormattedString(
                    employeeList, CommandType.MOD, MAX_RESULT_NUMBER);
        }
        return ResultStringFormatter.getEmployeeListToFormattedString(employeeList, CommandType.MOD);
    }

    @Override
    Condition getFirstCondition(List<String> tokenList) {
        return new Condition(Pair.create(tokenList.get(INDEX_PARAM1_COLUMN), tokenList.get(INDEX_PARAM1_VALUE)),
                tokenList.get(INDEX_OPTION1_1ST), tokenList.get(INDEX_OPTION1_2ND));
    }

    @Override
    Condition getSecondCondition(List<String> tokenList) {
        if (hasAndOrOperation(tokenList)) {
            return new Condition(Pair.create(tokenList.get(INDEX_PARAM2_COLUMN), tokenList.get(INDEX_PARAM2_VALUE)),
                    tokenList.get(INDEX_OPTION2_1ST), tokenList.get(INDEX_OPTION2_2ND));
        }
        return null;
    }

    @Override
    Condition getModifyCondition(List<String> tokenList) {
        if (hasAndOrOperation(tokenList)) {
            return new Condition(Pair.create(tokenList.get(INDEX_MOD_COLUMN_AND_OR_OPERATOR), tokenList.get(INDEX_MOD_VALUE_AND_OR_OPERATOR)),
                    MODIFY_OPTION, MODIFY_OPTION);
        }
        return new Condition(Pair.create(tokenList.get(INDEX_MOD_COLUMN), tokenList.get(INDEX_MOD_VALUE)),
                MODIFY_OPTION, MODIFY_OPTION);
    }
}
