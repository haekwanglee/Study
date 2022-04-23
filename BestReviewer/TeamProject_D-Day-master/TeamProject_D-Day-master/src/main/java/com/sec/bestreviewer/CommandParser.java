package com.sec.bestreviewer;


import com.sec.bestreviewer.util.Options;

import java.util.ArrayList;
import java.util.List;


public class CommandParser {
    private final static int MIN_TOKENS_NUM = 5; // CMD + 3 of options + at least 1 params
    private final static String OPTION_PREFIX = "-";

    public TokenGroup parse(String line) throws IllegalArgumentException {
        String[] parsedLine = line.split(",", -1);

        if (parsedLine.length < MIN_TOKENS_NUM) {
            throw new IllegalArgumentException("wrong command format");
        }

        String type = parsedLine[0];
        List<String> options = getOptions(parsedLine);
        List<String> params = getParams(parsedLine);

        TokenGroup tokenGroup = new TokenGroup(type, options, params);

        return tokenGroup;
    }

    private List<String> getOptions(String[] parsedLine) {
        List<String> options = new ArrayList<>();

        for(int i=1;i<parsedLine.length;i++){
            if(isOptionValue(parsedLine[i]))
                options.add(parsedLine[i]);
        }

        return options;
    }

    private List<String> getParams(String[] parsedLine) {
        List<String> parameters = new ArrayList<>();

        for(int i=1;i<parsedLine.length;i++){
            if(!isOptionValue(parsedLine[i]))
                parameters.add(parsedLine[i]);
        }

        return parameters;
    }

    private boolean isOptionValue(String argument){
        return (argument.equals(Options.EMPTY_OPTION) || argument.startsWith(OPTION_PREFIX));
    }
}
