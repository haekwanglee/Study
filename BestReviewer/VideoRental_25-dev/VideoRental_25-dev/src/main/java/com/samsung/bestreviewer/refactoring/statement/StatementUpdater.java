package com.samsung.bestreviewer.refactoring.statement;

import com.samsung.bestreviewer.refactoring.model.Customer;
import com.samsung.bestreviewer.refactoring.model.Rental;

public class StatementUpdater {

    private Customer customer;
    private StatementData statementData;
    private StatementPrinter statementPrinterOrigin;
    private StatementPrinter statementPrinterHtml;

    public StatementUpdater(Customer customer) {
        this.customer = customer;
        statementData = new StatementData(customer.getRental());
        statementPrinterOrigin = new StatementPrinterOrigin(this);
        statementPrinterHtml = new StatementPrinterHtml(this);
    }

    public Customer getCustomer() {
        return customer;
    }

    public StatementData getStatementData() {
        return statementData;
    }

    public StatementPrinter getStatementPrinterOrigin() {
        return statementPrinterOrigin;
    }

    public StatementPrinter getStatementPrinterHtml() {
        return statementPrinterHtml;
    }

    public void updateStatement() {
        initStatementData();

        printHeader();

        for (Rental rental: statementData.getRentals()) {
            initCurrentAmount();
            updateAmountAndPoints(rental);
            printFigures(rental);
        }

        printFooter();

        customer.addFrequentRenterPoints(statementData.getFrequentRenterPoints());
    }

    private void printFooter() {
        statementPrinterOrigin.printFooter();
        statementPrinterHtml.printFooter();
    }

    private void printFigures(Rental rental) {
        statementPrinterOrigin.printFigures(rental);
        statementPrinterHtml.printFigures(rental);
    }

    private void printHeader() {
        statementPrinterOrigin.printHeader();
        statementPrinterHtml.printHeader();
    }

    private void initStatementData() {
        statementData.init();
    }

    private void initCurrentAmount() {
        statementData.setCurrentAmount(0);
    }

    private void updateAmountAndPoints(Rental rental) {
        updateCurrentAmount(rental);
        updateFrequentRenterPoints(rental);
        updateTotalAmount();
    }

    private void updateCurrentAmount(Rental rental) {
        double currentAmount = statementData.getCurrentAmount();
        int daysRented = rental.getDaysRented();
        currentAmount += rental.getMovie().getCalculateMovie().getAmount(daysRented);

        statementData.setCurrentAmount(currentAmount);
    }

    private void updateFrequentRenterPoints(Rental rental) {
        int daysRented = rental.getDaysRented();
        int frequentRenterPoints = statementData.getFrequentRenterPoints();
        frequentRenterPoints += rental.getMovie().getCalculateMovie().getFrequentRenterPoints(daysRented);
        frequentRenterPoints += rental.getMovie().getCalculateMovie().getFrequentBonusPoints(daysRented);

        statementData.setFrequentRenterPoints(frequentRenterPoints);
    }

    private void updateTotalAmount() {
        double totalAmount = statementData.getTotalAmount();
        statementData.setTotalAmount(totalAmount + statementData.getCurrentAmount());
    }

}

