package com.sec.bestreviewer.util.arguments;

import java.util.List;

public class NormalCommandParameterManager extends CommandParameterManager {
    private static final int NUMBER_OF_COMMAND = 1;

    public NormalCommandParameterManager(int mode, List<String> params) {
        this.mode = mode;
        this.params = params;
    }

    @Override
    protected boolean isConditionParameter(int parserIndex) {
        if (parserIndex < NUMBER_OF_COMMAND)
            return true;

        return false;
    }
}
