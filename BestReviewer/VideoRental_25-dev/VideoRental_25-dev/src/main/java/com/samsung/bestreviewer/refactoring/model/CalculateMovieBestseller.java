package com.samsung.bestreviewer.refactoring.model;

public class CalculateMovieBestseller extends CalculateMovie {
    @Override
    public double getAmount(int daysRented) {
        double amount = 0;
        if (daysRented <= 2) {
            amount += daysRented * 5;
        } else {
            amount += 2 * 5 + (daysRented - 2) * 7.5;
        }
        return amount;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        return daysRented * 5;
    }

    @Override
    public int getFrequentBonusPoints(int daysRented) {
        return 0;
    }

}
