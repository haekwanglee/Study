package com.sec.bestreviewer.store;

public class ModelParams {
    public static final String OPT_AND = "-a";
    public static final String OPT_OR = "-o";

    private final String optionAndOr;
    private final Condition conditionForSearch1;
    private final Condition conditionForSearch2;
    private final Condition conditionForModify;

    public ModelParams(Condition conditionForSearch1, Condition conditionForSearch2,
                       Condition conditionForModify, String optionAndOr) {
        this.conditionForSearch1 = conditionForSearch1;
        this.conditionForSearch2 = conditionForSearch2;
        this.conditionForModify = conditionForModify;
        this.optionAndOr = optionAndOr;
    }

    public Condition getConditionForSearch1() {
        return conditionForSearch1;
    }

    public Condition getConditionForSearch2() {
        return conditionForSearch2;
    }

    public Condition getConditionForModify() {
        return conditionForModify;
    }

    public String getOptionAndOr() {
        return optionAndOr;
    }

    public boolean isOptionAndOr(){
        return OPT_AND.equals(optionAndOr) || OPT_OR.equals(optionAndOr);
    }
}
