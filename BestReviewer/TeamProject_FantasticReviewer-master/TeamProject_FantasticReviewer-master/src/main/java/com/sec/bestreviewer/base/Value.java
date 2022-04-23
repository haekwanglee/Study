package com.sec.bestreviewer.base;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Value {
    @NotNull
    private final String fieldName;

    @NotNull
    private final String value;

    public Value(@NotNull String fieldName, @NotNull String value) {
        this.fieldName = Objects.requireNonNull(fieldName);
        this.value = Objects.requireNonNull(value);
    }

    @NotNull
    public String getFieldName() {
        return fieldName;
    }

    @NotNull
    public String getValue() {
        return value;
    }
}
