package com.sec.bestreviewer;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CommandParser {
    private final static int MIN_TOKENS_NUM = 5; // CMD + 3 of options + at least 1 params
    private final static int INDEX_CMD_TYPE = 0;
    private final static String SPACE = " ";
    private final static String EMPTY = "";
    private final static String START_OPTION = "-";

    public TokenGroup parse(String line) throws IllegalArgumentException {
        ArrayList<String> r = new ArrayList<>(Arrays.asList(line.split(",", -1)));

        if (r.size() < MIN_TOKENS_NUM) {
            throw new IllegalArgumentException("wrong command format");
        }

        String type = r.get(INDEX_CMD_TYPE);
        r.remove(INDEX_CMD_TYPE);

        List<String> options = new ArrayList<String>();
        List<String> params = new ArrayList<String>();
        r.forEach(i -> {
            if (i.startsWith(START_OPTION) || SPACE.equals(i)) {
                options.add(i);
            } else if (EMPTY.equals(i)) {
                options.add(SPACE);
            } else {
                params.add(i);
            }
        });

        TokenGroup tokenGroup = new TokenGroup(type, options, getValidList(params));

        return tokenGroup;
    }

    private List<String> getValidList(List<String> list) {
        List<String> validList = new ArrayList<String>();

        list.forEach(i -> {
            if (!EMPTY.equals(i) && !SPACE.equals(i)) validList.add(i);
        });

        return validList;
    }
}
