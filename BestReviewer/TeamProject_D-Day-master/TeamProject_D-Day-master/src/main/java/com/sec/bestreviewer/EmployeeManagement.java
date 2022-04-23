package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;

import java.util.Arrays;
import java.util.List;

public class EmployeeManagement {
    CommandReader commandReader;
    CommandParser commandParser;
    CommandFactory commandFactory;
    CommandExecutor commandExecutor;
    Printer printer;

    public EmployeeManagement() {
    }

    public void run(String[] args) {
        if (!areValidArguments(args)) {
            printUsage();
            throw new IllegalArgumentException("wrong arguments count");
        }

        String inputFileName = args[0];
        String outputFilename = args[1];

        prepareBuildCommand(inputFileName, outputFilename);

        List<String> input = getInputForCommands();

        executeBuildCommandWith(input);

        writeResultToOutputFile();
    }

    private boolean areValidArguments(String[] args) {
        return args.length >= 2;
    }


    private void prepareBuildCommand(String inputFileName, String outputFilename) {
        commandReader = new CommandReader(inputFileName);
        commandParser = new CommandParser();
        commandFactory = new CommandFactory();
        commandExecutor = new CommandExecutor();
        printer = new Printer(outputFilename);
    }

    private List<String> getInputForCommands() {
        return commandReader.readFile();
    }

    private void executeBuildCommandWith(List<String> input) {
        for (String line : input) {
            try {
                TokenGroup tokens = commandParser.parse(line);
                Command command = commandFactory.buildCommand(tokens.getType(), tokens.getOptions(), tokens.getParams());
                List<String> result = commandExecutor.execute(command);
                printer.add(result);
            } catch (IllegalArgumentException exception) {
                printer.add(Arrays.asList("wrong command : " + line));
            }
        }
    }

    private void writeResultToOutputFile() {
        printer.printOutputFile();
    }

    private void printUsage() {
        System.out.println("input / output 형태는 txt 파일이며, 아래 command 로 input file 을 read 하여, output file 을 생성한다.");
        System.out.println("Usage : EmployeeManagement input.txt output.txt");
    }

    public static void main(String[] args) {
        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.run(args);
    }
}
