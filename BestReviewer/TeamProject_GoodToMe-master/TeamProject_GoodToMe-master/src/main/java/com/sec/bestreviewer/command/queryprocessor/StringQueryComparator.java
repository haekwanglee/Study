package com.sec.bestreviewer.command.queryprocessor;

public class StringQueryComparator implements QueryComparator {
    public boolean equals(String a, String b) {
        return a.equals(b);
    }
    public boolean greaterThan(String a, String b) {
        return a.compareTo(b) > 0;
    }
    public boolean greaterThanEquals(String a, String b) {
        return a.compareTo(b) >= 0;
    }
    public boolean smallerThan(String a, String b) {
        return a.compareTo(b) < 0;
    }
    public boolean smallerThanEquals(String a, String b) {
        return a.compareTo(b) <= 0;
    }
}
