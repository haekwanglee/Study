package com.sec.bestreviewer.store.field;

public class CareerLevel implements Field {
    public static final String CL1 = "CL1";
    public static final String CL2 = "CL2";
    public static final String CL3 = "CL3";
    public static final String CL4 = "CL4";

    enum Level {
        CL1,
        CL2,
        CL3,
        CL4,
    }

    Level level;

    public CareerLevel(String level) {
        setValue(level);
    }

    @Override
    public void setValue(String level) {
        this.level = Level.valueOf(level);
    }

    public String toString() {
        return level.toString();
    }

    @Override
    public boolean equals(String value) {
        return this.level == Level.valueOf(value);
    }

    @Override
    public boolean equals(String value, String option) {
        return equals(value);
    }

    @Override
    public int compareTo(String value) {
        return level.compareTo(Level.valueOf(value));
    }

    @Override
    public int compareTo(String value, String option) {
        return compareTo(value);
    }
}
