package com.sec.bestreviewer.command;

import com.sec.bestreviewer.store.Condition;
import com.sec.bestreviewer.store.EmployeeStore;

import java.util.Collections;
import java.util.List;

public class CountCommand extends CommandImpl {
    public CountCommand(CommandDTO commandDTO) {
        super(commandDTO);
    }

    @Override
    public List<String> execute(EmployeeStore employeeStore) {
        return Collections.singletonList(CommandType.CNT + "," + employeeStore.count());
    }

    @Override
    Condition getFirstCondition(List<String> tokenList) {
        return null;
    }

    @Override
    Condition getSecondCondition(List<String> tokenList) {
        return null;
    }

    @Override
    Condition getModifyCondition(List<String> tokenList) {
        return null;
    }
}
