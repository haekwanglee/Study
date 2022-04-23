package com.samsung.bestreviewer.refactoring;

import java.util.List;

public abstract class Printer {
    List<Rental> rentals;
    String name;

    public Printer(List<Rental> rentals, String name) {
        this.rentals = rentals;
        this.name = name;
    }

    public String print() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = getHeader();

        for (Rental rental: rentals) {
            double thisAmount = rental.getAmount();
            frequentRenterPoints += rental.getFrequentRenterPoints();
            result += getRenterInfoString(rental, thisAmount);
            totalAmount += thisAmount;
        }

        //add footer lines
        result += getTotalAmountString(totalAmount);
        result += getRenterPointString(frequentRenterPoints);
        return result;
    }

    abstract String getHeader();
    abstract String getRenterPointString(int frequentRenterPoints);
    abstract String getTotalAmountString(double totalAmount);
    abstract String getRenterInfoString(Rental rental, double thisAmount);
}
