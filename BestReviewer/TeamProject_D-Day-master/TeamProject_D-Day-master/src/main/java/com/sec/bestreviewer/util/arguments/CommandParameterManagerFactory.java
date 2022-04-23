package com.sec.bestreviewer.util.arguments;

import java.util.List;

import static com.sec.bestreviewer.util.arguments.CommandOptionSeparator.NORMAL_COMMAND;

public class CommandParameterManagerFactory {

    public static CommandParameterManager create(int mode, List<String> params) {
        if (mode == NORMAL_COMMAND)
            return new NormalCommandParameterManager(mode, params);

        return new WithLogicalOperatorParameterManager(mode, params);
    }
}

