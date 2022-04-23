package com.sec.bestreviewer.parser;


import com.sec.bestreviewer.data.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CommandParser {
    private final static int MIN_TOKENS_NUM = 5; // CMD + 3 of options + at least 1 params

    static List<String> options;
    static List<String> params;
    static String[] r;

    public CommandData parse(String line) throws IllegalArgumentException {
        initVariables(line);
        checkOverMinimumTokenNum(r, MIN_TOKENS_NUM);

        String type = r[0];

        CommandData commandData = new CommandData();

        commandData.setType(parseCommandType(type));
        splitToOptionsAndParams(r, commandData);

        commandData.setPrintOption(parsePrintOption(options.get(0)));
        commandData.setAndOrOption(parseAndOrOption(options));
        commandData.setSearchDataList(parseSearchDataList(options, params));
        commandData.setModifyData(parseUpdateData(commandData.getType(), params));
        commandData.setAddDataList(params);
        return commandData;
    }

    private void initVariables(String line) {
        options = new ArrayList<>();
        params = new ArrayList<>();
        r = line.split(",", -1);
    }

    private void checkOverMinimumTokenNum(String[] r, int minTokensNum) {
        if (r.length < minTokensNum) {
            throw new IllegalArgumentException("wrong command format");
        }
    }

    private void splitToOptionsAndParams(String[] r, CommandData commandData) {
        if (commandData.getType() == CommandType.ADD) {
            splitOneConditionedCommand(r);
        } else if (commandData.getType() == CommandType.MOD) {
            if (r.length > 8) {
                splitTwoConditionedCommand(r);
            } else {
                splitOneConditionedCommand(r);
            }
        } else {
            if (r.length > 6) {
                splitTwoConditionedCommand(r);
            } else {
                splitOneConditionedCommand(r);
            }
        }

        trimOptionsAndParams();
    }

    private void trimOptionsAndParams() {
        options = trim(options);
        params = trim(params);
    }

    private void splitOneConditionedCommand(String[] r) {
        addOptionFromTo(r, 1, 4);
        addParamFromTo(r, 4, r.length);
    }

    private void splitTwoConditionedCommand(String[] r) {
        addOptionFromTo(r, 1, 4);
        addParamFromTo(r, 4, 6);
        addOptionFromTo(r, 6, 9);
        addParamFromTo(r, 9, r.length);
    }

    private void addParamFromTo(String[] r, int from, int to) {
        params.addAll(Arrays.asList(Arrays.copyOfRange(r, from, to)));
    }

    private void addOptionFromTo(String[] r, int from, int to) {
        options.addAll(Arrays.asList(Arrays.copyOfRange(r, from, to)));
    }

    private List<String> trim(List<String> contents) {
        List<String> trimmedList = new ArrayList<String>();
        for (String content : contents) {
            trimmedList.add(content.trim());
        }
        return trimmedList;
    }

    private ModifyData parseUpdateData(CommandType commandType, List<String> params) {
        if (!hasUpdateData(commandType, params)) {
            return null;
        }
        return new ModifyData(params.get(params.size() - 2), params.get(params.size() - 1));
    }

    private boolean hasUpdateData(CommandType commandType, List<String> params) {
        return commandType == CommandType.MOD && params.size() >= 4;
    }

    private AndOrOption parseAndOrOption(List<String> options) {
        if (options.size() < 4) {
            return AndOrOption.NONE;
        }

        String andOrOption = options.get(3);
        switch (andOrOption) {
            case "-a":
                return AndOrOption.AND;
            case "-o":
                return AndOrOption.OR;
            default:
                return AndOrOption.NONE;
        }
    }

    private List<SearchData> parseSearchDataList(List<String> options, List<String> params) {
        return SearchDataConvertor.commandToSearchData(options, params);
    }

    private PrintOption parsePrintOption(String printOption) {
        switch (printOption) {
            case "-p":
                return PrintOption.PRINT;
            default:
                return PrintOption.NONE;
        }
    }

    public CommandType parseCommandType(String commandType) {
        switch (commandType) {
            case "ADD":
                return CommandType.ADD;
            case "DEL":
                return CommandType.DEL;
            case "SCH":
                return CommandType.SCH;
            case "MOD":
                return CommandType.MOD;
            default:
                throw new IllegalArgumentException("not supported Command : " + commandType);
        }
    }

}
