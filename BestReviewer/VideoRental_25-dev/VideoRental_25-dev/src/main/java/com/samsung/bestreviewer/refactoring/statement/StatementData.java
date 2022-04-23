package com.samsung.bestreviewer.refactoring.statement;

import com.samsung.bestreviewer.refactoring.model.Rental;

import java.util.List;

public class StatementData {
    private double totalAmount = 0;
    private double currentAmount = 0;
    private int frequentRenterPoints = 0;
    private List<Rental> rentals;

    public StatementData(List<Rental> rentals) {
        this.totalAmount = 0;
        this.currentAmount = 0;
        this.frequentRenterPoints = 0;
        this.rentals = rentals;
    }

    public void init() {
        this.totalAmount = 0;
        this.currentAmount = 0;
        this.frequentRenterPoints = 0;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }
    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }
    public void setFrequentRenterPoints(int frequentRenterPoints) {
        this.frequentRenterPoints = frequentRenterPoints;
    }

    public List<Rental> getRentals() {
        return rentals;
    }
}
