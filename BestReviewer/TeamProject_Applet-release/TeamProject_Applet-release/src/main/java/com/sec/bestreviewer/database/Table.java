package com.sec.bestreviewer.database;

import com.sec.bestreviewer.database.field.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Table {

    private final int primaryKeyIndex;
    private final List<Field> fieldList;
    private final Map<String, Row> rowMap;

    public Table(List<Field> fieldList, int primaryKeyIndex) {
        this.fieldList = new ArrayList<>(fieldList);
        this.rowMap = new HashMap<>();
        this.primaryKeyIndex = primaryKeyIndex;
    }

    public boolean add(final Row row) {
        final String primaryKey = row.getValue(primaryKeyIndex);
        rowMap.put(primaryKey, row);
        return true;
    }

    public boolean remove(final Row row) {
        final String primaryKey = row.getValue(primaryKeyIndex);
        rowMap.remove(primaryKey);
        return true;
    }

    public boolean update(final Row row) {
        final String primaryKey = row.getValue(primaryKeyIndex);
        rowMap.put(primaryKey, row);
        return true;
    }

    public int countRows() {
        return rowMap.values().size();
    }

    public int getFieldIndexByName(final String fieldName) {
        return fieldList.stream()
                .filter(field -> field.getName().equals(fieldName))
                .map(Field::getIndex)
                .findFirst()
                .orElse(-1);
    }

    public List<Field> getFieldList() {
        return new ArrayList<>(fieldList);
    }

    public List<Row> getRowList() {
        return new ArrayList<>(rowMap.values());
    }

    public Row querySingleRow(final String primaryKey) {
        return rowMap.get(primaryKey);
    }

    public List<Row> queryFieldMatchedRows(final int fieldIndex, final String findValue) {
        final Field field = fieldList.get(fieldIndex);
        return rowMap.values()
                .stream()
                .parallel()
                .filter(row -> field.compare(row.getValue(fieldIndex), findValue) == 0)
                .collect(Collectors.toList());
    }

    public List<Row> queryLessThanRows(final int fieldIndex, final String standard, final boolean includesStandard) {
        final Field field = fieldList.get(fieldIndex);
        return rowMap.values()
                .stream()
                .parallel()
                .filter(row -> {
                    if (includesStandard) {
                        return field.compare(row.getValue(fieldIndex), standard) <= 0;
                    }
                    return field.compare(row.getValue(fieldIndex), standard) < 0;
                })
                .collect(Collectors.toList());
    }

    public List<Row> queryGreaterThanRows(final int fieldIndex, final String standard, final boolean includesStandard) {
        final Field field = fieldList.get(fieldIndex);
        return rowMap.values()
                .stream()
                .parallel()
                .filter(row -> {
                    if (includesStandard) {
                        return field.compare(row.getValue(fieldIndex), standard) >= 0;
                    }
                    return field.compare(row.getValue(fieldIndex), standard) > 0;
                })
                .collect(Collectors.toList());
    }

    public int getPrimaryKeyIndex() {
        return primaryKeyIndex;
    }
}
