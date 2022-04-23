package com.sec.bestreviewer.command;

import java.util.List;

public class CommandDTO {

    private final CommandType type;
    private final String printOption;
    private final List<String> employeeDataList;

    public CommandDTO(CommandType type, String printOption, List<String> employeeDataList) {
        this.type = type;
        this.printOption = printOption;
        this.employeeDataList = employeeDataList;
    }

    public CommandType getType() {
        return type;
    }

    public String getPrintOption() {
        return printOption;
    }

    public List<String> getEmployeeDataList() {
        return employeeDataList;
    }

    public Boolean isPrintOptionOn() {
        return "-p".equals(printOption);
    }
}
