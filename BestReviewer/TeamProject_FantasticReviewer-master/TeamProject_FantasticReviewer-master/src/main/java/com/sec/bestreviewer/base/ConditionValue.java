package com.sec.bestreviewer.base;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConditionValue extends Value {

    @NotNull
    private final String option1;

    @NotNull
    private final String option2;

    @NotNull
    private final String option3;

    public ConditionValue(@NotNull String fieldName,
                          @NotNull String value,
                          @Nullable String option1,
                          @Nullable String option2,
                          @Nullable String option3) {
        super(fieldName, value);
        this.option1 = (option1 == null) ? OptionId.OPTION_NONE : option1;
        this.option2 = (option2 == null) ? OptionId.OPTION_NONE : option2;
        this.option3 = (option3 == null) ? OptionId.OPTION_NONE : option3;
    }


    @NotNull
    public String getOption1() {
        return option1;
    }

    @NotNull
    public String getOption2() {
        return option2;
    }

    @NotNull
    public String getOption3() {
        return option3;
    }
}
