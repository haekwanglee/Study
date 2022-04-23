package com.sec.bestreviewer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TokenGroup {

    private final String type;
    private final String optionMain;
    private final String optionOperator;

    private final List<SearchConditionPair> searchConditionPairs;
    private final List<String> targetParams;

    TokenGroup(String type,
               String optionMain,
               String optionOperator,
               List<SearchConditionPair> searchConditionPairs,
               List<String> targetParams) {

        this.type = type;
        this.optionMain = optionMain;
        this.optionOperator = optionOperator;
        this.searchConditionPairs = new ArrayList<>(searchConditionPairs);
        this.targetParams = targetParams;
    }

    String getType() {
        return type;
    }

    String getOptionMain() {
        return optionMain;
    }

    String getOptionOperator() {
        return optionOperator;
    }

    public String getTargetFieldName() {
        if (!type.equals(CommandFactory.CMD_MOD)) {
            throw new IllegalArgumentException("invalid request with type");
        }
        return targetParams.get(0);
    }

    public String getTargetFieldValue() {
        if (!type.equals(CommandFactory.CMD_MOD)) {
            throw new IllegalArgumentException("invalid request with type");
        }
        return targetParams.get(1);
    }

    public List<String> getTargetValuesForAdd() {
        if (!type.equals(CommandFactory.CMD_ADD)) {
            throw new IllegalArgumentException("invalid request with type");
        }
        return targetParams;
    }

    public List<SearchConditionPair> getSearchConditionPair() {
        return searchConditionPairs;
    }

    public boolean hasOption(Option option) {
        return option.hasOption(this);
    }

    public enum Option {

        PRINT((tokenGroup) -> tokenGroup.getOptionMain().equals("-p")),
        AND((tokenGroup) -> tokenGroup.getOptionOperator().equals("-a")),
        OR((tokenGroup) -> tokenGroup.getOptionOperator().equals("-o"));

        private final Function<TokenGroup, Boolean> checkOperator;

        Option(Function<TokenGroup, Boolean> checkOperator) {
            this.checkOperator = checkOperator;
        }

        public boolean hasOption(final TokenGroup tokenGroup) {
            return checkOperator.apply(tokenGroup);
        }
    }
}

