package com.sec.bestreviewer.store;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum OptionAttributeType {
    DEFAULT("default", ""),
    ATTRIBUTE_FIRST_NAME("name", "-f"),
    ATTRIBUTE_LAST_NAME("name", "-l"),
    ATTRIBUTE_MIDDLE_NUMBER("phoneNum", "-m"),
    ATTRIBUTE_LAST_NUMBER("phoneNum", "-l"),
    ATTRIBUTE_YEAR("birthday", "-y"),
    ATTRIBUTE_MONTH("birthday", "-m"),
    ATTRIBUTE_DAY("birthday", "-d");


    private static final Map<String, OptionAttributeType> BY_KEY = new HashMap<>();
    private static final Map<String, OptionAttributeType> BY_OPTION = new HashMap<>();

    static {
        for (OptionAttributeType e : values()) {
            BY_KEY.put(e.key, e);
            BY_OPTION.put(e.option, e);
        }
    }

    public final String key;
    public final String option;

    OptionAttributeType(String key, String option) {
        this.key = key;
        this.option = option;
    }

    public static OptionAttributeType getTypeByKeyAndOption(String key, String optionName) {
        final String option = optionName.isEmpty() ? " " : optionName;

        return Arrays.stream(OptionAttributeType.values())
                .filter(e -> e.key.equals(key) && e.option.equals(option))
                .findFirst()
                .orElse(OptionAttributeType.DEFAULT);
    }

}
