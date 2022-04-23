package com.sec.bestreviewer.option;

import java.util.List;
import java.util.stream.Collectors;

public class OrOption {
    public static final String OR_OPTION = "-o";

    private final boolean isOrOn;
    private final List<String> firstOptions;
    private final List<String> secondOptions;

    public OrOption(List<String> options) {
        this.isOrOn = options.contains(OR_OPTION);
        firstOptions = sliceBefore(options);
        secondOptions = sliceAfter(options);
    }

    public boolean isOrOn() {
        return isOrOn;
    }

    public List<String> getFirstOptions() {
        return firstOptions;
    }

    public List<String> getSecondOptions() {
        return secondOptions;
    }

    private List<String> sliceBefore(List<String> array) {
        if (!isOrOn) {
            return null;
        }
        final int indexOfAndOption = array.indexOf(OR_OPTION);
        return array.stream().limit(indexOfAndOption).collect(Collectors.toList());
    }

    private List<String> sliceAfter(List<String> array) {
        if (!isOrOn) {
            return null;
        }
        final int indexOfAndOption = array.indexOf(OR_OPTION);
        return array.stream().skip(indexOfAndOption + 1).collect(Collectors.toList());
    }
}
