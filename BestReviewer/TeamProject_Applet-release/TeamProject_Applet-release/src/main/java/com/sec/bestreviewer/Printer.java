package com.sec.bestreviewer;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class Printer {

    private static final String COMMA = ",";
    private static final String NEW_LINE = "\n";
    private static final String NONE = "NONE";

    public static final String ERR_MSG_WRONG_COMMAND = "wrong command : ";
    public static final String FILE_READ_ERROR = "failed to read file : ";

    private final StringBuilder stdStringBuilder;

    public Printer() {
        this.stdStringBuilder = new StringBuilder();
    }

    public void onWrongMessage(final String line) {
        stdStringBuilder.append(ERR_MSG_WRONG_COMMAND);
        stdStringBuilder.append(line);
        stdStringBuilder.append(NEW_LINE);
    }

    public void addCommandResult(final TokenGroup tokenGroup, final List<String> dataList) {
        if (tokenGroup.getType().equals(CommandFactory.CMD_ADD)) {
            return;
        }

        if (dataList.isEmpty()) {
            appendNoneResult(tokenGroup.getType());
            return;
        }

        if (tokenGroup.hasOption(TokenGroup.Option.PRINT)) {
            final String result = printResultDetail(tokenGroup.getType(), dataList);
            stdStringBuilder.append(result);
        } else {
            final String result = printResult(tokenGroup.getType(), dataList);
            stdStringBuilder.append(result);
        }
    }

    void appendNoneResult(final String commandString) {
        stdStringBuilder.append(commandString).append(COMMA).append(NONE).append(NEW_LINE);
    }

    String printResult(final String commandString, final List<String> resultList) {
        return commandString + COMMA + resultList.size() + NEW_LINE;
    }

    String printResultDetail(final String commandString, final List<String> resultList) {
        final StringBuilder stringBuilder = new StringBuilder();

        resultList.stream()
                .limit(5)
                .forEachOrdered(resultLine -> {
                    stringBuilder.append(commandString);
                    stringBuilder.append(COMMA);
                    stringBuilder.append(resultLine);
                    stringBuilder.append(NEW_LINE);
                });

        return stringBuilder.toString();
    }

    public String peekStringOutput() {
        return stdStringBuilder.toString();
    }

    public void printOutputFile(final String outputFilename) {
        try (OutputStream output = new FileOutputStream(outputFilename)) {
            output.write(stdStringBuilder.toString().getBytes());
        } catch (Exception e) {
            appendFileReadFailure(e);
        }
    }

    private void appendFileReadFailure(Exception e) {
        stdStringBuilder.append(FILE_READ_ERROR)
                .append(e.getMessage())
                .append(NEW_LINE);
    }
}
