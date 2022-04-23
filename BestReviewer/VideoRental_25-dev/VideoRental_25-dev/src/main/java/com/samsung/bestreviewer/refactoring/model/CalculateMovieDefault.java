package com.samsung.bestreviewer.refactoring.model;

public class CalculateMovieDefault extends CalculateMovie {
    @Override
    public double getAmount(int daysRented) {
        return 0;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        return 0;
    }

    @Override
    public int getFrequentBonusPoints(int daysRented) {
        return 0;
    }
}
