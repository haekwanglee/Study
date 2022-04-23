package com.samsung.bestreviewer.refactoring.model;

public class CalculateMovieRegular extends CalculateMovie {
    @Override
    public double getAmount(int daysRented) {
        double amount = 0;
        amount += 2;
        if (daysRented > 2)
            amount += (daysRented- 2) * 1.5;
        return amount;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }

    @Override
    public int getFrequentBonusPoints(int daysRented) {
        return 0;
    }
}
