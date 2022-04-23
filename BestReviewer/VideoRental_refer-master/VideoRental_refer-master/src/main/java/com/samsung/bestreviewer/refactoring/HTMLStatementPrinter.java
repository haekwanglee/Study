package com.samsung.bestreviewer.refactoring;

import java.util.List;

public class HTMLStatementPrinter extends Printer {

    public HTMLStatementPrinter(List<Rental> rentals, String name) {
        super(rentals, name);
    }

    @Override
    String getHeader() {
        return "<table><tr><th colspan=3 align=left><b>Rentals for <b></th><th><em>" + name + "</em></th></tr>\n";
    }

    @Override
    String getRenterPointString(int frequentRenterPoints) {
        return "<tr><td colspan=4><b>You earned <EM>" +
                frequentRenterPoints +
                "</EM> frequent " + "renter points</b></td></tr></table>";
    }

    @Override
    String getTotalAmountString(double totalAmount) {
        return "<tr><td colspan=4><b>Amount owed is <EM>" + totalAmount + "</b></td></tr>\n";
    }

    @Override
    String getRenterInfoString(Rental rental, double thisAmount) {
        return "<tr><td> </td><td>"+ rental.getMovie().getTitle() + "</td><td> </td><td>" +
                thisAmount + "</td></tr>\n";
    }
}
