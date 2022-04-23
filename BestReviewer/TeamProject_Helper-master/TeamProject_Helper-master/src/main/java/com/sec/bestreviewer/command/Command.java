package com.sec.bestreviewer.command;

import com.sec.bestreviewer.store.ConditionParam;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.OptionParser;
import com.sec.bestreviewer.util.Pair;

import java.util.List;

public abstract class Command {
    protected List<Pair<String, String>> conditionPairs;
    protected OptionParser optionParser;

    public abstract List<String> execute(EmployeeStore employeeStore);

    protected ConditionParam getConditionParam() {
        String fieldOption = optionParser.getSecondOption();
        String compareOption = optionParser.getThirdOption();

        return new ConditionParam.Builder()
                .setKeyValue(conditionPairs.get(0))
                .setCompareOption(compareOption)
                .setFieldOption(fieldOption)
                .build();
    }

    protected ConditionParam getSecondConditionParam() {
        String fieldOption = optionParser.getSecondOptionAfterAndOr();
        String compareOption = optionParser.getThirdOptionAfterAndOr();

        return new ConditionParam.Builder()
                .setKeyValue(conditionPairs.get(1))
                .setCompareOption(compareOption)
                .setFieldOption(fieldOption)
                .build();
    }

    protected boolean isAndOrOperation() {
        return optionParser.isAndOperation() || optionParser.isOrOperation();
    }
}
