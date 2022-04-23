package com.sec.bestreviewer.util;

import com.sec.bestreviewer.option.*;

import java.util.Arrays;
import java.util.List;

public class OptionParser {

    public final static List<String> SUPPORTED_OPTIONS = Arrays.asList("-p", "-f", "-l", "-m", "-y", "-d", "-g", "-ge", "-s", "-se", "-a", "-o");
    private final PrintOption printOption;
    private final NameOption nameOption;
    private final BirthDayOption birthDayOption;
    private final PhoneOption phoneOption;
    private final EqualityOption equalityOption;
    private final AndOption andOption;
    private final OrOption orOption;

    public OptionParser(List<String> options, String fieldName) {
        printOption = new PrintOption(options);
        nameOption = new NameOption(options, fieldName);
        birthDayOption = new BirthDayOption(options, fieldName);
        phoneOption = new PhoneOption(options, fieldName);
        equalityOption = new EqualityOption(options);
        andOption = new AndOption(options);
        orOption = new OrOption(options);
    }

    public PrintOption getPrintOption() {
        return printOption;
    }

    public NameOption getNameOption() {
        return nameOption;
    }

    public BirthDayOption getBirthDayOption() {
        return birthDayOption;
    }

    public PhoneOption getPhoneOption() {
        return phoneOption;
    }

    public EqualityOption getEqualityOption() {
        return equalityOption;
    }

    public AndOption getAndOption() {
        return andOption;
    }

    public OrOption getOrOption() {
        return orOption;
    }
}
