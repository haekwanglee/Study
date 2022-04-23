package com.samsung.bestreviewer.refactoring.model;

public class CalculateMovieNewRelease extends CalculateMovie {
    @Override
    public double getAmount(int daysRented) {
        double amount = 0;
        amount += daysRented * 3;
        return amount;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }

    @Override
    public int getFrequentBonusPoints(int daysRented) {
        if (daysRented > 1) {
            return 1;
        }
        return 0;
    }
}
