package com.samsung.bestreviewer.refactoring.priceinfo;

public class Children extends PriceInfo {
    @Override
    public double getAmount(int daysRented) {
        double thisAmount = 1.5;
        if (daysRented > 3)
            thisAmount += (daysRented - 3) * 1.5;
        return thisAmount;
    }

    @Override
    public int getRenterPoints(int daysRented) {
        return 1;
    }
}
