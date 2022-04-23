package com.sec.bestreviewer;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class CommandParser {
    private final static int MIN_TOKENS_NUM = 5; // CMD + 3 of options + at least 1 params
    private final static String SPACE = " ";
    public static final String DELIMITER = ",";

    public TokenGroup parse(String line) throws IllegalArgumentException {
        String[] r = line.split(DELIMITER, -1);

        if (r.length < MIN_TOKENS_NUM) {
            throw new IllegalArgumentException("wrong command format: " + line);
        }

        String commandType = r[0];
        List<String> printOption = Arrays.asList(Arrays.copyOfRange(r, 1, 2));
        List<String> queryParams = Arrays.asList(Arrays.copyOfRange(r, 2, r.length));

        return new TokenGroup(commandType, printOption, queryParams);
    }
}
