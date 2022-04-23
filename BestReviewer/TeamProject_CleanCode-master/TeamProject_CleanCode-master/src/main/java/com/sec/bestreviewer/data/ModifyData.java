package com.sec.bestreviewer.data;

public class ModifyData {
    private String columnName;
    private String value;

    public ModifyData(String columnName, String value) {
        this.columnName = columnName;
        this.value = value;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getValue() {
        return value;
    }
}
