package com.sec.bestreviewer.store;

public class Field {
    String value;

    public Field(String value) {
        setField(value);
    }

    public void setField(String value) {
        this.value = value;
    }

    public int toCompareTo(String value, String filterOption) {
        return this.value.compareTo(value);
    }

    public String getValue() {
        return value;
    }
}
