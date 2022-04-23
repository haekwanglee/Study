package com.samsung.bestreviewer.refactoring;

import java.util.List;

public class StatementPrinter extends Printer {

    public StatementPrinter(List<Rental> rentals, String name) {
        super(rentals, name);
    }

    @Override
    String getHeader() {
        return "Rental Record for " + name + "\n";
    }

    @Override
    String getRenterPointString(int frequentRenterPoints) {
        return "You earned " + frequentRenterPoints + " frequent renter points";
    }

    @Override
    String getTotalAmountString(double totalAmount) {
        return "Amount owed is " + totalAmount + "\n";
    }

    @Override
    String getRenterInfoString(Rental rental, double thisAmount) {
        return "\t" + rental.getMovie().getTitle() + "\t" +
                thisAmount + "\n";
    }
}
