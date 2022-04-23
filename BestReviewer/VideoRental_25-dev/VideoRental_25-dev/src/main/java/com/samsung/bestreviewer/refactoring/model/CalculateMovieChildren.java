package com.samsung.bestreviewer.refactoring.model;

public class CalculateMovieChildren extends CalculateMovie {
    @Override
    public double getAmount(int daysRented) {
        double amount = 0;
        amount += 1.5;
        if (daysRented > 3)
            amount += (daysRented - 3) * 1.5;
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
