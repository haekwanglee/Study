package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;

import java.util.List;

public class EmployeeManagement {

    private CommandReader commandReader;
    private final CommandParser commandParser;
    private final CommandExecutor commandExecutor;
    private final Printer printer;

    public EmployeeManagement() {
        commandReader = new CommandReader();
        commandParser = new CommandParser();
        commandExecutor = new CommandExecutor();
        printer = new Printer();
    }

    @Deprecated
    public void run(String[] args) {
        executeCommand(args[0]);
        printOutFile(args[1]);
    }

    public void printOutFile(String outputFileName) {
        printer.printOutputFile(outputFileName);
    }

    public void executeCommand(final String inputFileName) {

        final List<String> lineList = commandReader.readFile(inputFileName);
        lineList.forEach(this::executeEach);
    }

    public void executeEach(String line) {
        try {
            final TokenGroup tokenGroup = commandParser.parse(line);
            final Command command = CommandFactory.buildCommand(tokenGroup);
            final List<String> rowResult = commandExecutor.execute(command);

            printer.addCommandResult(tokenGroup, rowResult);
        } catch (IllegalArgumentException exception) {
            printer.onWrongMessage(line);
        }
    }

    public String peekCommandOutput() {
        return printer.peekStringOutput();
    }
}
