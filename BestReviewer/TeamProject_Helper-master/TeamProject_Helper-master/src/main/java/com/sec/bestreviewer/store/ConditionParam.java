package com.sec.bestreviewer.store;

import com.sec.bestreviewer.util.Pair;

import java.util.HashMap;

public class ConditionParam {
    public enum CompareOption {
        EQUAL,
        GREATER_EQUAL,
        GREATER,
        SMALL,
        SMALL_EQUAL
    }

    private static final String OP_GREATER = "-g";
    private static final String OP_GREATER_EQUAL = "-ge";
    private static final String OP_SMALL = "-s";
    private static final String OP_SMALL_EQUAL = "-se";

    private static final HashMap<String, ConditionParam.CompareOption> compareOptionMap = new HashMap<>();
    static {
        compareOptionMap.put(OP_GREATER, CompareOption.GREATER);
        compareOptionMap.put(OP_GREATER_EQUAL, CompareOption.GREATER_EQUAL);
        compareOptionMap.put(OP_SMALL, CompareOption.SMALL);
        compareOptionMap.put(OP_SMALL_EQUAL, CompareOption.SMALL_EQUAL);
    }

    static final String SPACE = " ";

    String field;
    String value;
    String fieldOption = SPACE;
    CompareOption compareOption = CompareOption.EQUAL;

    public ConditionParam(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public ConditionParam(String field, String value, String fieldOption) {
        this.field = field;
        this.value = value;
        this.fieldOption = fieldOption;
    }

    public ConditionParam(String field, String value, String fieldOption, CompareOption compareOption) {
        this.field = field;
        this.value = value;
        this.fieldOption = fieldOption;
        this.compareOption = compareOption;
    }

    public static class Builder {
        String field;
        String value;
        String fieldOption = SPACE;
        CompareOption compareOption = CompareOption.EQUAL;

        public Builder setKeyValue(Pair<String, String> pair) {
            field = pair.first;
            value = pair.second;
            return this;
        }

        public Builder setCompareOption(String compareOption) {
            if (!SPACE.equals(compareOption)) {
                this.compareOption = getCompareOption(compareOption);
            }
            return this;
        }

        public Builder setFieldOption(String fieldOption) {
            if (!SPACE.equals(fieldOption)) {
                this.fieldOption = fieldOption;
            }
            return this;
        }

        public ConditionParam build() {
            return new ConditionParam(field, value, fieldOption, compareOption);
        }

        private ConditionParam.CompareOption getCompareOption(String compareOption) {
            return compareOptionMap.get(compareOption);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof ConditionParam))
            return false;

        ConditionParam param = (ConditionParam) obj;
        return param.field.equals(field)
                && param.value.equals(value)
                && param.fieldOption.equals(fieldOption)
                && param.compareOption == compareOption;
    }
}
