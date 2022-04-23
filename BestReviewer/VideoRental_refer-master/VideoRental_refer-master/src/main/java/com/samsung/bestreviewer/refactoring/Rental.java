package com.samsung.bestreviewer.refactoring;

public class Rental {
    Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public double getAmount() {
        return movie.getPriceInfo().getAmount(daysRented);
    }

    public int getFrequentRenterPoints() {
        return movie.getPriceInfo().getRenterPoints(daysRented);
    }
}
