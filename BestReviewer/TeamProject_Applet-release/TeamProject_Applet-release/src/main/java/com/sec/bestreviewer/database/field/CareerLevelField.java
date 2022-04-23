package com.sec.bestreviewer.database.field;

public class CareerLevelField extends Field {

    public CareerLevelField(String name, int index) {
        super(name, index);
    }

    @Override
    public int compare(String data1, String data2) {
        if (data1.equals(data2)) {
            return 0;
        }

        int orderOfData1 = CareerLevelType.getOrder(data1);
        int orderOfData2 = CareerLevelType.getOrder(data2);

        return orderOfData1 > orderOfData2 ? 1 : -1;
    }

    @Override
    public boolean validate(String data) {
        return CareerLevelType.forName(data) != CareerLevelType.DEFAULT;
    }
}
