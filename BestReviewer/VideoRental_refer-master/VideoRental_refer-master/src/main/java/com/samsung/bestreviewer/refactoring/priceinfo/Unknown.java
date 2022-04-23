package com.samsung.bestreviewer.refactoring.priceinfo;

public class Unknown extends PriceInfo {
    @Override
    public double getAmount(int daysRented) {
        return 0;
    }

    @Override
    public int getRenterPoints(int daysRented) {
        return 0;
    }
}
