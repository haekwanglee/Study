package com.samsung.bestreviewer.refactoring.priceinfo;

import com.samsung.bestreviewer.refactoring.Movie;

public abstract class PriceInfo {
    PriceInfo() {
    }

    public abstract double getAmount(int daysRented);
    public abstract int getRenterPoints(int daysRented);

    public static PriceInfo createPriceInfo(int priceCode) {
        switch (priceCode) {
            case Movie.REGULAR:
                return new Regular();
            case Movie.NEW_RELEASE:
                return new NewRelease();
            case Movie.CHILDREN:
                return new Children();
            case Movie.BESTSELLER:
                return new Bestseller();
            default:
                return new Unknown();
        }
    }
}
