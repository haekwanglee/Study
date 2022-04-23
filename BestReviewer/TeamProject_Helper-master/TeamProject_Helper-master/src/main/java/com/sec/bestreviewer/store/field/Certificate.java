package com.sec.bestreviewer.store.field;

public class Certificate implements Field {
    enum Level {
        ADV,
        PRO,
        EX
    }

    private Level level;

    public Certificate(String value) {
        setValue(value);
    }

    @Override
    public boolean equals(String value) {
        return equals(value, "");
    }

    @Override
    public boolean equals(String value, String option) {
        return level == Level.valueOf(value);
    }

    @Override
    public int compareTo(String value) {
        return compareTo(value, "");
    }

    @Override
    public int compareTo(String value, String option) {
        return level.ordinal() - Level.valueOf(value).ordinal();
    }

    @Override
    public void setValue(String value) {
        level = Level.valueOf(value);
    }

    @Override
    public String toString() {
        return level.name();
    }
}
