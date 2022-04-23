package com.sec.bestreviewer.view.parser;

import com.sec.bestreviewer.command.CommandDTO;
import com.sec.bestreviewer.command.CommandType;

import java.util.List;

public class ParserToDto {

    private final static int INDEX_TYPE = 0;
    private final static int INDEX_OPTION_PRINT = 1;
    private final static int INDEX_CONDITIONS_START = 2;

    final public CommandDTO parse(List<String> tokenList) {
        String type = tokenList.get(INDEX_TYPE);
        String printOption = tokenList.get(INDEX_OPTION_PRINT);

        List<String> employeeDataList = getEmployeeDataList(tokenList);

        return new CommandDTO(CommandType.getTypeByOptionName(type), printOption, employeeDataList);
    }

    List<String> getEmployeeDataList(List<String> tokenList) {
        return tokenList.subList(INDEX_CONDITIONS_START, tokenList.size());
    }

}
