package com.sec.bestreviewer.command.queryprocessor;

public interface QueryComparator {
    boolean equals(String a, String b);
    boolean greaterThan(String a, String b);
    boolean greaterThanEquals(String a, String b);
    boolean smallerThan(String a, String b);
    boolean smallerThanEquals(String a, String b);
}
