package com.sec.bestreviewer.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Row {

    private final List<String> row;

    public Row(String... stringArray) {
        row = new ArrayList<>(Arrays.asList(stringArray));
    }

    public Row(List<String> stringList) {
        row = new ArrayList<>(stringList);
    }

    public String getValue(int index) {
        return row.get(index);
    }

    public List<String> getRow() {
        return new ArrayList<>(row);
    }

    public void clear() {
        row.clear();
    }

    public void addAll(final List<String> valueList) {
        row.addAll(valueList);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        row.forEach(fieldEntry -> builder.append(fieldEntry).append(","));
        builder.deleteCharAt(builder.lastIndexOf(","));
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Row row1 = (Row) o;
        return Objects.equals(row, row1.row);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row);
    }
}
