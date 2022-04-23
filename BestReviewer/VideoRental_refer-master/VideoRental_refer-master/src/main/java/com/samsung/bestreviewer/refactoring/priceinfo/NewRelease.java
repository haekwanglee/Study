package com.samsung.bestreviewer.refactoring.priceinfo;

public class NewRelease extends PriceInfo {
    @Override
    public double getAmount(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int getRenterPoints(int daysRented) {
        return 1 + ((daysRented > 1) ? 1 : 0);
    }
}
