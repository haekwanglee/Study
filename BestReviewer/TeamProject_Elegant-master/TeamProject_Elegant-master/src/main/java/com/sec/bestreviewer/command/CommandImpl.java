package com.sec.bestreviewer.command;

import com.sec.bestreviewer.store.Condition;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.store.ModelParams;

import java.util.List;

abstract public class CommandImpl implements Command {
    public final static String EMPTY = "";

    public final static int INDEX_OPTION1_1ST = 0;
    public final static int INDEX_OPTION1_2ND = 1;
    public final static int INDEX_PARAM1_COLUMN = 2;
    public final static int INDEX_PARAM1_VALUE = 3;

    public final static int INDEX_AND_OR_OPERATION = 4;

    public final static int INDEX_OPTION2_1ST = 5;
    public final static int INDEX_OPTION2_2ND = 6;
    public final static int INDEX_PARAM2_COLUMN = 7;
    public final static int INDEX_PARAM2_VALUE = 8;

    public final static int INDEX_MOD_COLUMN = 4;
    public final static int INDEX_MOD_VALUE = 5;
    public final static int INDEX_MOD_COLUMN_AND_OR_OPERATOR = 9;
    public final static int INDEX_MOD_VALUE_AND_OR_OPERATOR = 10;

    public final static String OPERATOR_AND = "-a";
    public final static String OPERATOR_OR = "-o";

    final public CommandDTO commandDTO;

    protected ModelParams modelParams;

    public CommandImpl(CommandDTO commandDTO) {
        this.commandDTO = commandDTO;
    }

    protected void setModelParams() {
        List<String> tokenList = commandDTO.getEmployeeDataList();

        Condition condition1 = getFirstCondition(tokenList);
        Condition condition2 = getSecondCondition(tokenList);
        Condition modifyCondition = getModifyCondition(tokenList);
        String optionAndOr = getOptionAndOr(tokenList);

        this.modelParams = new ModelParams(condition1, condition2, modifyCondition, optionAndOr);
    }

    public ModelParams getModelParams() {
        return modelParams;
    }

    protected boolean hasAndOrOperation(List<String> tokenList) {
        return hasAndOperator(tokenList) || hasOrOperator(tokenList);
    }


    boolean hasAndOperator(List<String> tokenList) {
        if (tokenList.size() <= INDEX_AND_OR_OPERATION) {
            return false;
        }
        return tokenList.get(INDEX_AND_OR_OPERATION).equals(OPERATOR_AND);
    }

    boolean hasOrOperator(List<String> tokenList) {
        if (tokenList.size() <= INDEX_AND_OR_OPERATION) {
            return false;
        }
        return tokenList.get(INDEX_AND_OR_OPERATION).equals(OPERATOR_OR);
    }

    String getOptionAndOr(List<String> tokenList) {
        if (hasAndOperator(tokenList)) {
            return OPERATOR_AND;
        }
        if (hasOrOperator(tokenList)) {
            return OPERATOR_OR;
        }
        return EMPTY;
    }

    abstract public List<String> execute(EmployeeStore employeeStore);

    abstract Condition getFirstCondition(List<String> tokenList);

    abstract Condition getSecondCondition(List<String> tokenList);

    abstract Condition getModifyCondition(List<String> tokenList);


}
