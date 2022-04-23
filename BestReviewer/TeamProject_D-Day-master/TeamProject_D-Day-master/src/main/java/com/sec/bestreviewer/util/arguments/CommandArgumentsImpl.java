package com.sec.bestreviewer.util.arguments;

import com.sec.bestreviewer.util.OptionParser;
import com.sec.bestreviewer.util.Pair;

import java.util.ArrayList;
import java.util.List;

import static com.sec.bestreviewer.util.arguments.CommandOptionSeparator.NORMAL_COMMAND;


public class CommandArgumentsImpl implements CommandArguments {


    private static final String CMD_ADD = "ADD";
    private List<String> rawParams;
    private List<OptionParser> optionList;
    private List<Pair> conditionParamList;
    private Pair<String, String> targetParam;
    private int mode;

    public CommandArgumentsImpl() {
        optionList = new ArrayList<>();
        conditionParamList = new ArrayList<>();
        targetParam = null;
        mode = NORMAL_COMMAND;
    }

    @Override
    public void generateCommandArguments(String cmd, List<String> options, List<String> params) {
        initCommandOptions(options);
        initCommandParameters(cmd, params);
    }

    private void initCommandOptions(List<String> options) {
        CommandOptionSeparator commandOptionSeparator = new CommandOptionSeparator(options);
        optionList = commandOptionSeparator.getOptionParserList();
        mode = commandOptionSeparator.getCommandMode();
    }

    private void initCommandParameters(String commandType, List<String> params) {

        if (isADDCommand(commandType)) {
            initNoConditionalParameters(params);
            return;
        }

        initConditionAndTargetParameters(params);
    }

    private void initConditionAndTargetParameters(List<String> params) {
        CommandParameterManager parameterManager = CommandParameterManagerFactory.create(mode, params);

        conditionParamList = parameterManager.getConditionParameters();
        targetParam = parameterManager.getTargetParameter();
    }

    private boolean isADDCommand(String commandType) {
        return commandType.equals(CMD_ADD);
    }

    private void initNoConditionalParameters(List<String> params) {
        rawParams = params;
    }

    @Override
    public List<OptionParser> getOptionList() {
        return optionList;
    }

    @Override
    public List<String> getRawParams() {
        return rawParams;
    }

    @Override
    public List<Pair> getConditionParamList() {
        return conditionParamList;
    }

    @Override
    public Pair<String, String> getTargetParam() {
        return targetParam;
    }

    @Override
    public int getCommandMode() {
        return mode;
    }
}