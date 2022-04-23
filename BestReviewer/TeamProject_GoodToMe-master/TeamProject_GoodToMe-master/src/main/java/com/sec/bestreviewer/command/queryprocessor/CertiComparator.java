package com.sec.bestreviewer.command.queryprocessor;

public class CertiComparator implements QueryComparator {

    public static final int ADV_GRADE = 1;
    public static final int PRO_GRADE = 2;
    public static final int EX_GRADE = 3;
    public static final String ADV = "ADV";
    public static final String PRO = "PRO";
    public static final String EX = "EX";

    public boolean equals(String a, String b) {
        return getConvertedCertiGrade(a) == getConvertedCertiGrade(b);
    }
    public boolean greaterThan(String a, String b) {
        return getConvertedCertiGrade(a) > getConvertedCertiGrade(b);
    }
    public boolean greaterThanEquals(String a, String b) {
        return getConvertedCertiGrade(a) >= getConvertedCertiGrade(b);
    }
    public boolean smallerThan(String a, String b) {
        return getConvertedCertiGrade(a) < getConvertedCertiGrade(b);
    }
    public boolean smallerThanEquals(String a, String b) {
        return getConvertedCertiGrade(a) <= getConvertedCertiGrade(b);
    }

    private int getConvertedCertiGrade(String certi) throws IllegalArgumentException  {
        switch (certi) {
            case ADV:
                return ADV_GRADE;
            case PRO:
                return PRO_GRADE;
            case EX:
                return EX_GRADE;
            default:
                throw new IllegalArgumentException("Wrong certification");
        }
    }
}
