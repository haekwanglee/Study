package com.samsung.bestreviewer.refactoring.model;

import com.samsung.bestreviewer.refactoring.statement.StatementData;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String name;
    private Vector rentals = new Vector();
    private int frequentRenterPoints = 0;

    public Customer(String name) {
        this.name = name;
        frequentRenterPoints = 0;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public Vector getRental() {
        return rentals;
    }

    public int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }

    public void addFrequentRenterPoints(int frequentRenterPoints) {
        this.frequentRenterPoints += frequentRenterPoints;
    }

}

