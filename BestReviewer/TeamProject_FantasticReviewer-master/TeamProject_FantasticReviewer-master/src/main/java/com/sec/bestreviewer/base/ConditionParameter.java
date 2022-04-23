package com.sec.bestreviewer.base;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ConditionParameter {

    public static final int TYPE_IDX = 0;
    public static final int FIRST_OPTION_1 = 1;
    public static final int FIRST_OPTION_2 = 2;
    public static final int FIRST_OPTION_3 = 3;
    public static final int FIRST_FIELDNAME = 4;
    public static final int FIRST_VALUE = 5;
    public static final int SECOND_OPTION_1 = 6;
    public static final int SECOND_OPTION_2 = 7;
    public static final int SECOND_OPTION_3 = 8;
    public static final int SECOND_FIELDNAME = 9;
    public static final int SECOND_VALUE = 10;
    public static final int TO_MODIFY_FIELDNAME = 11;
    public static final int TO_MODIFY_VALUE = 12;

    @NotNull
    private final ConditionValue first;

    @Nullable
    private final ConditionValue second;

    @Nullable
    private final Value toModifyValue;

    @NotNull
    private final String operator;

    public ConditionParameter(@NotNull ConditionValue first,
                              @Nullable ConditionValue second,
                              @Nullable Value toModifyValue,
                              @Nullable String operator) {
        this.first = Objects.requireNonNull(first);
        this.second = second;
        this.operator = (operator == null) ? OperatorId.NONE : operator;
        this.toModifyValue = toModifyValue;
    }

    @NotNull
    public ConditionValue getFirst() {
        return first;
    }

    @Nullable
    public ConditionValue getSecond() {
        return second;
    }

    @Nullable
    public Value getModifyValue() {
        return toModifyValue;
    }
    @NotNull
    public String getOperator() {
        return operator;
    }
}
