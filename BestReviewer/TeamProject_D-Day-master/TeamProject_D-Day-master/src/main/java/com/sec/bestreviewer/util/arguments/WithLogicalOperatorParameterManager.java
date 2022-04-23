package com.sec.bestreviewer.util.arguments;

import java.util.List;

public class WithLogicalOperatorParameterManager extends CommandParameterManager {
    private static final int NUMBER_OF_COMMAND_IN_LOGICAL_OPERATOR = 2;

    public WithLogicalOperatorParameterManager(int mode, List<String> params) {
        this.mode = mode;
        this.params = params;
    }

    @Override
    protected boolean isConditionParameter(int parserIndex) {
        if (parserIndex < NUMBER_OF_COMMAND_IN_LOGICAL_OPERATOR)
            return true;

        return false;
    }
}
