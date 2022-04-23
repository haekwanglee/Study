package com.samsung.bestreviewer.refactoring.statement;

import com.samsung.bestreviewer.refactoring.model.Rental;

public abstract class StatementPrinter {
    StatementUpdater statementUpdater;
    StringBuilder printResult;

    public StatementPrinter(StatementUpdater statementUpdater) {
        this.statementUpdater = statementUpdater;
        printResult = new StringBuilder();
    }

    public abstract void printHeader();


    public void printFigures(Rental rental) {
        StatementData statementData = statementUpdater.getStatementData();
        printResult.append(getPrintFigures(rental, statementData.getCurrentAmount()));
    }

    public void printFooter() {
        StatementData statementData = statementUpdater.getStatementData();
        printResult.append(getPrintFooter(statementData.getTotalAmount(), statementData.getFrequentRenterPoints()));
    }

    protected String getPrintHead(String headStart, String headEnd) {
        return headStart + statementUpdater.getCustomer().getName() + headEnd;
    }

    protected abstract String getPrintFigures(Rental rental, double currentAmount);
    protected abstract String getPrintFooter(double totalAmount, int frequentRenterPoints);

    public String getPrintResult() {
        return printResult.toString();
    }
}
