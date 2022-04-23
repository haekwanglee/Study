package com.samsung.bestreviewer.refactoring;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        return new StatementPrinter(rentals, getName()).print();
    }

    public String htmlStatement() {
        return new HTMLStatementPrinter(rentals, getName()).print();
    }
}
