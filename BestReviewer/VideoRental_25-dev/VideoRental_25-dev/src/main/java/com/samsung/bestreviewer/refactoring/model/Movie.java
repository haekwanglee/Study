package com.samsung.bestreviewer.refactoring.model;

public class Movie {

    public static final int CHILDREN = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int BEST_SELLER = 3;

    private String title;
    private int priceCode;
    private CalculateMovie calculateMovie;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
        this.calculateMovie = CalculateMovie.createByType(priceCode);
    }

    public int getPriceCode() {
        return priceCode;
    }

    public String getTitle() {
        return title;
    }

    public CalculateMovie getCalculateMovie() {
        return calculateMovie;
    }
}
