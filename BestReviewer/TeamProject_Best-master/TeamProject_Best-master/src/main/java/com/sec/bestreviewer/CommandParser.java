package com.sec.bestreviewer;

import com.sec.bestreviewer.util.OptionParser;

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

        ArrayList<String> cmd = new ArrayList<>(Arrays.asList(r));
        String type = getType(cmd);
        cmd.remove(0);

        List<String> options = getOptions(cmd);
        cmd.removeAll(options);
        return new TokenGroup(type, getValidList(options), getValidList(cmd));
    }

    private String getType(ArrayList<String> cmd) {
        return cmd.get(0);
    }

    private List<String> getOptions(List<String> cmd) {
        List<String> options = new ArrayList<>();
        for (String ch : cmd) {
            if (OptionParser.SUPPORTED_OPTIONS.contains(ch)) {
                options.add(ch);
            }
        }
        return options;
    }

    private List<String> getValidList(List<String> list) {
        List<String> validList = new ArrayList<String>();

        list.forEach(i -> {
            if (!EMPTY.equals(i) && !SPACE.equals(i)) validList.add(i);
        });

        return validList;
    }
}
