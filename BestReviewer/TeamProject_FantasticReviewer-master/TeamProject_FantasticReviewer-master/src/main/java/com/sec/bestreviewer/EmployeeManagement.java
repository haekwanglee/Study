package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;

import java.util.List;

public class EmployeeManagement  {

    public static final int MIN_ARGS_LENGTH = 2;
    public static final int INPUT_FILE_IDX = 0;
    public static final int OUTPUT_FILE_IDX = 1;

    public EmployeeManagement() {
    }

    public void run(String[] args) {
        if (args.length < MIN_ARGS_LENGTH) {
            printUsage();
            throw new IllegalArgumentException("wrong arguments count");
        }

        String inputFileName = args[INPUT_FILE_IDX];
        String outputFilename = args[OUTPUT_FILE_IDX];

        CommandReader commandReader = new CommandReader(inputFileName);
        CommandParser commandParser = new CommandParser();
        CommandExecutor commandExecutor = new CommandExecutor();
        Printer printer = new Printer(outputFilename);

        List<String> input = commandReader.readFile();

        for (String line : input) {
            try {
                TokenGroup tokens = commandParser.parse(line);
                Command command = CommandFactory.buildCommand(tokens);
                List<String> result = commandExecutor.execute(command);
                printer.add(result);
            } catch (IllegalArgumentException exception) {
                printer.add(List.of("wrong command : " + line));
            }
        }

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
