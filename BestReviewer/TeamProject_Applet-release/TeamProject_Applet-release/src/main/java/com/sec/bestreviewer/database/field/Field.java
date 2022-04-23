package com.sec.bestreviewer.database.field;

import java.util.Comparator;
import java.util.Optional;

public abstract class Field implements Comparator<String> {

    private final String name;
    private final int index;

    public Field(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public abstract int compare(String data1, String data2);

    public abstract boolean validate(String data);
}
