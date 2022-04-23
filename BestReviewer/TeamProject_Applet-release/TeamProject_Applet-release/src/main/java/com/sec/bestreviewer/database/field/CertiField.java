package com.sec.bestreviewer.database.field;

public class CertiField extends Field {
    public CertiField(String name, int index) {
        super(name, index);
    }

    @Override
    public int compare(String data1, String data2) {
        if (data1.equals(data2)) {
            return 0;
        }

        int orderOfData1 = CertiType.getOrder(data1);
        int orderOfData2 = CertiType.getOrder(data2);

        return orderOfData1 > orderOfData2 ? 1 : -1;
    }

    @Override
    public boolean validate(String data) {
        return CertiType.forName(data) != CertiType.DEFAULT;
    }
}
