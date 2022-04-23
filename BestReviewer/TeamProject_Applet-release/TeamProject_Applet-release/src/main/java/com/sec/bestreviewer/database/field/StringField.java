package com.sec.bestreviewer.database.field;

public class StringField extends Field {

    public StringField(String name, int index) {
        super(name, index);
    }

    @Override
    public int compare(String data1, String data2) {
        return data1.compareTo(data2);
    }

    @Override
    public boolean validate(String data) {
        return data != null;
    }
}
