package com.sec.bestreviewer;

import com.sec.bestreviewer.command.CommandDTO;
import com.sec.bestreviewer.view.parser.ParserToDto;

import java.util.Arrays;
import java.util.List;

public class CommandParser {
    private final static int MIN_TOKENS_NUM = 5; // CMD + 3 of options + at least 1 params
    private final static String SPLIT_TOKEN = ",";

    public CommandDTO parseCommandDTO(String line) {
        List<String> tokenList = Arrays.asList(line.split(SPLIT_TOKEN, -1));

        if (tokenList.size() < MIN_TOKENS_NUM) {
            throw new IllegalArgumentException("wrong command format");
        }

        return new ParserToDto().parse(tokenList);
    }
}
