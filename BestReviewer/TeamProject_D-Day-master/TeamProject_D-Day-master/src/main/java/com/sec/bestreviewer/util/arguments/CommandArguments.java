package com.sec.bestreviewer.util.arguments;

import com.sec.bestreviewer.util.OptionParser;
import com.sec.bestreviewer.util.Pair;

import java.util.List;

public interface CommandArguments {

    void generateCommandArguments(String cmd, List<String> options, List<String> params);

    List<OptionParser> getOptionList();

    List<String> getRawParams();

    List<Pair> getConditionParamList();

    Pair<String, String> getTargetParam();

    int getCommandMode();
}
