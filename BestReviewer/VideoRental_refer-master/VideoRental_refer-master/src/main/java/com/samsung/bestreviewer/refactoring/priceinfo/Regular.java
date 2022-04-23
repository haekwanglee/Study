package com.samsung.bestreviewer.refactoring.priceinfo;

public class Regular extends PriceInfo {
    @Override
    public double getAmount(int daysRented) {
        double thisAmount = 2;
        if (daysRented > 2)
            thisAmount += (daysRented - 2) * 1.5;
        return thisAmount;
    }

    @Override
    public int getRenterPoints(int daysRented) {
        return 1;
    }
}
