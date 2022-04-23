package com.samsung.bestreviewer.refactoring.model;

public abstract class CalculateMovie {

    public static CalculateMovie createByType(int priceCode) {
        switch (priceCode) {
            case Movie.REGULAR:
                return new CalculateMovieRegular();
            case Movie.NEW_RELEASE:
                return new CalculateMovieNewRelease();
            case Movie.CHILDREN:
                return new CalculateMovieChildren();
            case Movie.BEST_SELLER:
                return new CalculateMovieBestseller();
            default:
                return new CalculateMovieDefault();
        }
    }

    public abstract double getAmount(int daysRented);
    public abstract int getFrequentRenterPoints(int daysRented);
    public abstract int getFrequentBonusPoints(int daysRented);
}