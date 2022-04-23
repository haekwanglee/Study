package com.sec.bestreviewer.database.field;

import java.util.Arrays;

enum CertiType {
    ADV("ADV", 1),
    PRO("PRO", 2),
    EX("EX",3),
    DEFAULT();

    String name;
    int order;

    CertiType(String name, int order) {
        this.name = name;
        this.order = order;
    }

    CertiType() {
        this("DEFAULT",0);
    }

    public static CertiType forName(String name) {
        return Arrays.stream(CertiType.values())
                .filter(certiType -> certiType.name.equals(name))
                .findFirst()
                .orElse(CertiType.DEFAULT);
    }

    public static int getOrder(String name) {
        return forName(name).order;
    }
}
