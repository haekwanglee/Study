package com.samsung.bestreviewer.refactoring.statement;

import com.samsung.bestreviewer.refactoring.model.Rental;

public class StatementPrinterOrigin extends StatementPrinter {
    public StatementPrinterOrigin(StatementUpdater statementUpdater) {
        super(statementUpdater);
    }

    @Override
    public void printHeader() {
        printResult.append(getPrintHead("Rental Record for ", "\n"));
    }


    @Override
    protected String getPrintFigures(Rental rental, double currentAmount) {
        String figures = "";
        figures += "\t" + rental.getMovie().getTitle() + "\t" +
                String.valueOf(currentAmount) + "\n";
        return figures;
    }

    @Override
    protected String getPrintFooter(double totalAmount, int frequentRenterPoints) {
        String footer = "";
        footer += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        footer += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return footer;
    }
}
