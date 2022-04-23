package com.sec.bestreviewer.util;

import java.util.List;

public class OptionParser {

    private static final int NUMBER_OF_OPTION_SIZE_FOR_FIRST_COMMAND = 3;   //first command consists of '-p', '-f/l/y/m/d/ln/mn', '-g/ge/s/se'
    private static final int NUMBER_OF_OPTION_SIZE_FOR_SECOND_COMMAND = 2;  //second command consists of '-f/l/y/m/d/ln/mn', '-g/ge/s/se'

    private boolean isPrintOn = false;

    private String filterOption = Options.EMPTY_OPTION;
    private String compareOption = Options.EMPTY_OPTION;

    public OptionParser(List<String> options) {
        if (!options.isEmpty()) {
            initializeByNumberOfOptions(options);
        }
    }

    private void initializeByNumberOfOptions(List<String> options) {
        if (options.size() == NUMBER_OF_OPTION_SIZE_FOR_FIRST_COMMAND)
            initFirstCommandOptionParser(options);
        else if (options.size() == NUMBER_OF_OPTION_SIZE_FOR_SECOND_COMMAND)
            initSecondCommandOptionParser(options);
        else
            throw new IllegalArgumentException("Invalid number of options.");
    }

    private void initFirstCommandOptionParser(List<String> options) {
        setPrintOption(options.get(0));
        setFilterOption(options.get(1));
        setCompareOption(options.get(2));
    }

    private void initSecondCommandOptionParser(List<String> options) {
        setFilterOption(options.get(0));
        setCompareOption(options.get(1));
    }

    private void setPrintOption(String commandPrintArgument) {
        if (isNotEmptyOption(commandPrintArgument))
            isPrintOn = true;
    }

    private void setFilterOption(String commandFirstArgument) {
        if (isNotEmptyOption(commandFirstArgument)) {
            filterOption = commandFirstArgument;
        }
    }

    private void setCompareOption(String commandSecondArgument) {
        if (isNotEmptyOption(commandSecondArgument)) {
            compareOption = commandSecondArgument;
        }
    }

    private boolean isNotEmptyOption(String optionValue) {
        return !optionValue.equals(Options.EMPTY_OPTION);
    }

    public boolean isPrintOn() {
        return isPrintOn;
    }

    public String getFilterOption() {
        return filterOption;
    }

    public String getCompareOption() {
        return compareOption;
    }
}
