package com.samsung.bestreviewer.refactoring.statement;

import com.samsung.bestreviewer.refactoring.model.Rental;

public class StatementPrinterHtml extends StatementPrinter{
    public StatementPrinterHtml(StatementUpdater statementUpdater) {
        super(statementUpdater);
    }

    @Override
    public void printHeader() {
        printResult.append(getPrintHead("<table><tr><th colspan=3 align=left><b>Rentals for <b></th><th><em>", "</em></th></tr>\n"));
    }


    @Override
    protected String getPrintFigures(Rental rental, double currentAmount) {
        String figures = "";
        figures += "<tr><td> </td><td>"+ rental.getMovie().getTitle() + "</td><td> </td><td>" +
                String.valueOf(currentAmount) + "</td></tr>\n";
        return figures;
    }

    @Override
    protected String getPrintFooter(double totalAmount, int frequentRenterPoints) {
        String footer = "";
        footer += "<tr><td colspan=4><b>Amount owed is <EM>" + String.valueOf(totalAmount) + "</b></td></tr>\n";
        footer += "<tr><td colspan=4><b>You earned <EM>" +
                String.valueOf(frequentRenterPoints) +
                "</EM> frequent " + "renter points</b></td></tr></table>";
        return footer;
    }
}
