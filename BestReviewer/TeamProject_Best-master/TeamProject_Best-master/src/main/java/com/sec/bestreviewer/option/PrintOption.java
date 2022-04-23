package com.sec.bestreviewer.option;

import java.util.List;

public class PrintOption {
    public static final String PRINT_OPTION = "-p";

    private final boolean isPrintOn;

    public PrintOption(List<String> options) {
        isPrintOn = options.contains(PRINT_OPTION);
//        System.out.println("PrintOption: " + "isPrintOn=" + isPrintOn);
    }

    public boolean isOptionOn() {
        return isPrintOn;
    }
}