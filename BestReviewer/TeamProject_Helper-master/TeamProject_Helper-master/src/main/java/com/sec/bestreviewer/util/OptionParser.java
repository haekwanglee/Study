package com.sec.bestreviewer.util;

import com.sec.bestreviewer.store.ConditionParam;

import java.util.List;

public class OptionParser {
    private final int INDEX_PRINT_OP = 0;
    private final int INDEX_MATCH_OP = 1;
    private final int INDEX_COMP_OP = 2;
    private final int INDEX_ANDOR_OP = 3;
    private final int INDEX_MATCH_OP_AFTER_ANDOR = 4;
    private final int INDEX_COMP_OP_AFTER_ANDOR = 5;

    private final int OPTION_LIST_MIN = 1;
    private final int OPTION_LIST_NORMAL = 3;

    private static final String OP_PRINT = "-p";
    private static final String OP_AND = "-a";
    private static final String OP_OR = "-o";

    private boolean isPrintOn;
    private boolean isAndOperationOn = false;
    private boolean isOrOperationOn = false;
    private Pair<ConditionOptionParser, ConditionOptionParser> conditionOptions;


    public OptionParser(List<String> options) {
        ConditionOptionParser conditionFirst = new ConditionOptionParser();
        ConditionOptionParser conditionSecond = new ConditionOptionParser();

        isPrintOn = OP_PRINT.equals(options.get(INDEX_PRINT_OP));

        if (options.size() > OPTION_LIST_MIN) {
            conditionFirst.setSecondOption(options.get(INDEX_MATCH_OP));
            conditionFirst.setThirdOption(options.get(INDEX_COMP_OP));
        }

        if (options.size() > OPTION_LIST_NORMAL) {
            isAndOperationOn = OP_AND.equals(options.get(INDEX_ANDOR_OP));
            isOrOperationOn = OP_OR.equals(options.get(INDEX_ANDOR_OP));
            conditionSecond.setSecondOption(options.get(INDEX_MATCH_OP_AFTER_ANDOR));
            conditionSecond.setThirdOption(options.get(INDEX_COMP_OP_AFTER_ANDOR));
        }

        conditionOptions = Pair.create(conditionFirst, conditionSecond);
    }

    public boolean isPrintOn() {
        return isPrintOn;
    }

    public boolean isAndOperation() { return isAndOperationOn; }

    public boolean isOrOperation() {
        return isOrOperationOn;
    }

    public String getSecondOption() {
        return conditionOptions.first.getSecondOption();
    }

    public String getThirdOption() {
        return conditionOptions.first.getThirdOption();
    }

    public String getSecondOptionAfterAndOr() {
        return conditionOptions.second.getSecondOption();
    }

    public String getThirdOptionAfterAndOr() {
        return conditionOptions.second.getThirdOption();
    }
}