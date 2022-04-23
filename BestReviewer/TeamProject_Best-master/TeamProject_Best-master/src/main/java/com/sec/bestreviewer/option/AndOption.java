package com.sec.bestreviewer.option;

import java.util.List;
import java.util.stream.Collectors;

public class AndOption {
    public static final String AND_OPTION = "-a";

    private final boolean isAndOn;
    private final List<String> firstOptions;
    private final List<String> secondOptions;

    public AndOption(List<String> options) {
        this.isAndOn = options.contains(AND_OPTION);
        firstOptions = sliceBefore(options);
        secondOptions = sliceAfter(options);
    }

    public boolean isAndOn() {
        return isAndOn;
    }

    public List<String> getFirstOptions() {
        return firstOptions;
    }

    public List<String> getSecondOptions() {
        return secondOptions;
    }

    private List<String> sliceBefore(List<String> array) {
        if (!isAndOn) {
            return null;
        }
        final int indexOfAndOption = array.indexOf(AND_OPTION);
        return array.stream().limit(indexOfAndOption).collect(Collectors.toList());
    }

    private List<String> sliceAfter(List<String> array) {
        if (!isAndOn) {
            return null;
        }
        final int indexOfAndOption = array.indexOf(AND_OPTION);
        return array.stream().skip(indexOfAndOption + 1).collect(Collectors.toList());
    }
}
