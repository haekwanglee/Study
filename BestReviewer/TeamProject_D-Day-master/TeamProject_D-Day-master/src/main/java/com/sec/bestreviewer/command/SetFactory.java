package com.sec.bestreviewer.command;

import com.sec.bestreviewer.util.arguments.CommandOptionSeparator;

public class SetFactory {

    public static Set createSet(int mode) {
        switch(mode) {
            case CommandOptionSeparator.AND_COMMAND:
                return new IntersectionSet();
            default:
                return new UnionSet();
        }
    }
}
