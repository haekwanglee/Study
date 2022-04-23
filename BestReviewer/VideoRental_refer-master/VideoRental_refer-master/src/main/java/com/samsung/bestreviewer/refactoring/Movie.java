package com.samsung.bestreviewer.refactoring;

import com.samsung.bestreviewer.refactoring.priceinfo.PriceInfo;

public class Movie {

    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDREN = 2;
    public static final int BESTSELLER = 3;

    private String title;
    private int priceCode;
    private PriceInfo priceInfo;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
        this.priceInfo = PriceInfo.createPriceInfo(priceCode);
    }

    public int getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(int arg) {
        priceCode = arg;
        priceInfo = PriceInfo.createPriceInfo(priceCode);
    }

    public PriceInfo getPriceInfo() {
        return priceInfo;
    }

    public String getTitle() {
        return title;
    }
}
