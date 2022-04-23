package com.sec.bestreviewer.database.field;

import java.util.Arrays;

public enum CareerLevelType {
    CL1("CL1", 1),
    CL2("CL2", 2),
    CL3("CL3",3),
    CL4("CL4",4),
    DEFAULT();

    String name;
    int order;

    CareerLevelType(String name, int order) {
        this.name = name;
        this.order = order;
    }

    CareerLevelType() {
        this("DEFAULT",0);
    }

    public static CareerLevelType forName(String name) {
        return Arrays.stream(CareerLevelType.values())
                .filter(careerLevelType -> careerLevelType.name.equals(name))
                .findFirst()
                .orElse(CareerLevelType.DEFAULT);
    }

    public static int getOrder(String name) {
        return forName(name).order;
    }
}
