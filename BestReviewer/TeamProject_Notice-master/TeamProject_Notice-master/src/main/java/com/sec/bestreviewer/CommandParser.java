package com.sec.bestreviewer;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CommandParser {
    private final static int MIN_TOKENS_NUM = 5; // CMD + 3 of options + at least 1 params
    private final static String SPACE = " ";
    private final static String EMPTY = "";

    public TokenGroup parse(String line) throws IllegalArgumentException {
        String[] r = line.split(",", -1);

        if (r.length < MIN_TOKENS_NUM) {
            throw new IllegalArgumentException("wrong command format");
        }

        String type = r[0];
        List<String> options = Arrays.asList(Arrays.copyOfRange(r, 1, 4));
        List<String> params = Arrays.asList(Arrays.copyOfRange(r, 4, r.length));

        TokenGroup tokenGroup = new TokenGroup(type, getValidList(options), getValidList(params));

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
