package com.sec.bestreviewer.store.field;

public interface Field {
    String toString();
    boolean equals(String value);
    boolean equals(String value, String option);

    int compareTo(String value);
    int compareTo(String value, String option);
    public void setValue(String value);
}
