package com.sec.bestreviewer;

public class Main {

    public static void main(String[] args) {

        if (!isValidArgument(args)) {
            printUsage();
            throw new IllegalArgumentException("wrong arguments count");
        }

        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.executeCommand(args[0]);
        employeeManagement.printOutFile(args[1]);
    }

    private static boolean isValidArgument(String[] args) {
        return args.length == 2;
    }

    private static void printUsage() {
        System.out.println("input / output 형태는 txt 파일이며, 아래 command 로 input file 을 read 하여, output file 을 생성한다.");
        System.out.println("Usage : EmployeeManagement input.txt output.txt");
    }
}
