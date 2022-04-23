package com.sec.bestreviewer.store;

import java.util.Arrays;

public enum OptionValueType {
    VALUE_GREATER("-g"),
    VALUE_GREATER_EQUAL("-ge"),
    VALUE_SMALLER("-s"),
    VALUE_SMALLER_EQUAL("-se"),
    VALUE_EQUAL(" ");

    public final String optionName;

    OptionValueType(String optionName) {
        this.optionName = optionName;
    }

    public static OptionValueType getTypeByOptionName(String optionName) {
        final String option = optionName.isEmpty() ? " " : optionName;

        return Arrays.stream(OptionValueType.values())
                .filter(e -> e.optionName.equals(option))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Wrong option type"));
    }
}
