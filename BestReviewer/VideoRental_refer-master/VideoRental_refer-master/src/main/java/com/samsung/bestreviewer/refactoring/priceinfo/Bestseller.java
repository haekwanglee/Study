package com.samsung.bestreviewer.refactoring.priceinfo;

public class Bestseller extends PriceInfo {
    @Override
    public double getAmount(int daysRented) {
        if (daysRented <= 2) {
            return daysRented * 5;
        } else {
            return 2 * 5 + (daysRented - 2) * 7.5;
        }
    }

    @Override
    public int getRenterPoints(int daysRented) {
        return daysRented * 5;
    }
}
