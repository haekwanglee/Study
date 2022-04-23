package com.sec.bestreviewer;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CommandParser {

    private final static int MIN_TOKENS_NUM = 5; // CMD + 3 of options + at least 1 params
    private final static String SPACE = " ";
    private final static String EMPTY = "";
    private final static String OPERATOR_AND = "-a";
    private final static String OPERATOR_OR = "-o";

    private List<String> tokenList;
    private List<SearchConditionPair> searchConditionPairs = new ArrayList<>();
    private List<String> conditionOptions, conditionParams, targetParams;
    private String type, optionMain, optionOperator;

    public TokenGroup parse(String line) throws IllegalArgumentException {
        getTokenListFromLine(line);
        initTokenize();
        tokenizeMain(tokenList);
        tokenizeParameter(tokenList);
        tokenizeMoreWhenHasOperator(tokenList);
        return new TokenGroup(type, optionMain, optionOperator, searchConditionPairs, targetParams);
    }

    private void initTokenize() {
        searchConditionPairs.clear();
    }

    private void getTokenListFromLine(String line) {
        tokenList = Arrays.asList(line.split(",", -1));
        if (tokenList.size() < MIN_TOKENS_NUM) {
            throw new IllegalArgumentException("wrong command format");
        }
    }

    private void tokenizeMain(List<String> tokenList) {
        type = getType(tokenList);
        optionMain = getOptionMain(tokenList);
        optionOperator = getOptionOperator(tokenList);
    }

    private void tokenizeParameter(List<String> tokenList) {
        int optionOperatorIndex = getOptionOperatorIndex(tokenList);
        conditionParams = getConditionParams(tokenList, type, optionOperatorIndex);
        conditionOptions = getConditionOptions(tokenList);
        targetParams = getTargetParams(tokenList, type, conditionParams);
        if (!type.equals(CommandFactory.CMD_ADD)) {
            searchConditionPairs.add(new SearchConditionPair(getValidList(conditionOptions), getValidList(conditionParams)));
        }
    }

    private void tokenizeMoreWhenHasOperator(List<String> tokenList) {
        int optionOperatorIndex = getOptionOperatorIndex(tokenList);
        if (optionOperatorIndex == tokenList.size()) {
            return;
        }
        conditionOptions = tokenList.subList(optionOperatorIndex + 1, optionOperatorIndex + 3);
        conditionParams = tokenList.subList(optionOperatorIndex + 3, optionOperatorIndex + 5);
        searchConditionPairs.add(new SearchConditionPair(getValidList(conditionOptions), getValidList(conditionParams)));
        if (optionOperatorIndex + 5 < tokenList.size()) {
            targetParams = tokenList.subList(optionOperatorIndex + 5, tokenList.size());
        }
    }

    private String getType(List<String> tokenList) {
        if (!isValidToken(tokenList.get(0))) {
            return "";
        }
        return tokenList.get(0);
    }

    private String getOptionMain(List<String> tokenList) {
        if (!isValidToken(tokenList.get(1))) {
            return "";
        }
        return tokenList.get(1);
    }

    private boolean isValidToken(String token) {
        if (EMPTY.equals(token) || SPACE.equals(token)) {
            return false;
        }
        return true;
    }

    private List<String> getValidList(List<String> list) {
        List<String> validList = new ArrayList<String>();
        list.forEach(i -> {
            if (isValidToken(i)) {
                validList.add(i);
            }
        });
        return validList;
    }

    private int getOptionOperatorIndex(List<String> tokenList) {
        int optionOperatorIndex = tokenList.size();
        for (String token : tokenList) {
            if (token.equals(OPERATOR_AND) || token.equals(OPERATOR_OR)) {
                optionOperatorIndex = tokenList.indexOf(token);
            }
        }
        return optionOperatorIndex;
    }

    private String getOptionOperator(List<String> tokenList) {
        String optionOperator = "";
        for (String token : tokenList) {
            if (token.equals(OPERATOR_AND) || token.equals(OPERATOR_OR)) {
                optionOperator = token;
            }
        }
        return optionOperator;
    }

    private List<String> getConditionOptions(List<String> tokenList) {
        return tokenList.subList(2, 4);
    }

    private List<String> getConditionParams(List<String> tokenList, String type, int endPosition) {
        List<String> params = tokenList.subList(4, endPosition);
        if (type.equals(CommandFactory.CMD_MOD)) {
            params = tokenList.subList(4, 6);
        }
        return params;
    }

    private List<String> getTargetParams(List<String> tokenList, String type, List<String> defaultParams) {
        List<String> targetParams = defaultParams;
        if (type.equals(CommandFactory.CMD_MOD)) {
            targetParams = tokenList.subList(6, 8);
        }
        return targetParams;
    }

}
