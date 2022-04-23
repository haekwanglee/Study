package com.sec.bestreviewer.command;

import java.util.Arrays;

public enum CommandType {
    ADD("ADD"), DEL("DEL"), SCH("SCH"), CNT("CNT"), MOD("MOD");

    public final String label;

    CommandType(String label) {
        this.label = label;
    }

    public static CommandType getTypeByOptionName(String optionName) {
        return Arrays.stream(CommandType.values())
                .filter(e -> e.label.equals(optionName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Wrong option type"));
    }
}
