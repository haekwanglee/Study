package com.sec.bestreviewer.util.arguments;

import com.sec.bestreviewer.util.OptionParser;

import java.util.ArrayList;
import java.util.List;

public class CommandOptionSeparator {
    private static final String AND_OPTION_TOKEN = "-a";
    private static final String OR_OPTION_TOKEN = "-o";

    public static final int NORMAL_COMMAND = 0;
    public static final int AND_COMMAND = 1;
    public static final int OR_COMMAND = 2;

    private List<String> subOptions;
    private List<String> options;
    private List<OptionParser> optionParserList;
    private int commandMode;

    public CommandOptionSeparator(List<String> options) {
        this.options = options;
        subOptions = new ArrayList<>();
        optionParserList = new ArrayList<>();

        initOptionParserList();
    }

    public List<OptionParser> getOptionParserList() {
        return optionParserList;
    }

    public int getCommandMode() {
        return commandMode;
    }

    private void initOptionParserList() {
        for (int i = 0; i < options.size(); i++) {
            if (isLogicalOperatorToken(options.get(i))) {
                setCommandMode(options.get(i));
                addToOptionList();
            } else {
                subOptions.add(options.get(i));
            }
        }

        addToOptionList();
    }

    private boolean isLogicalOperatorToken(String option) {
        return option.equals(AND_OPTION_TOKEN) || option.equals(OR_OPTION_TOKEN);
    }

    private void setCommandMode(String option) {
        if (option.equals(AND_OPTION_TOKEN)) {
            commandMode = AND_COMMAND;
            return;
        }

        commandMode = OR_COMMAND;
    }

    private void addToOptionList() {
        optionParserList.add(new OptionParser(subOptions));
        subOptions = new ArrayList<>();
    }
}
