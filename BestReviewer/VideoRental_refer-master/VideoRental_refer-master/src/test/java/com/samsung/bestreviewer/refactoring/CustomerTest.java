package com.samsung.bestreviewer.refactoring;

import org.approvaltests.Approvals;
import org.approvaltests.namer.NamedEnvironment;
import org.approvaltests.namer.NamerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    final static Customer superCustomer = new Customer("SuperCustomer");
    final static Customer superHtmlCustomer = new Customer("SuperHtmlCustomer");

    @ParameterizedTest
    @CsvSource({
            // Test label, priceCode, daysRented
            "NEW_RELEASE_1, 1, 1",
            "NEW_RELEASE_2, 1, 2",
            "NEW_RELEASE_3, 1, 3",
            "NEW_RELEASE_4, 1, 4",
            "NEW_RELEASE_5, 1, 5",
            "REGULAR_1, 0, 1",
            "REGULAR_2, 0, 2",
            "REGULAR_3, 0, 3",
            "REGULAR_4, 0, 4",
            "REGULAR_5, 0, 5",
            "CHILDREN_1, 2, 1",
            "CHILDREN_2, 2, 2",
            "CHILDREN_3, 2, 3",
            "CHILDREN_4, 2, 4",
            "CHILDREN_5, 2, 5",
            "TOTAL, -1, -1"
    })
    public void customerApprovalTest(String movieName, int priceCode, int daysRented) {
        try (NamedEnvironment en = NamerFactory.withParameters(movieName))
        {
            if (!movieName.equals("TOTAL")) {
                Movie movie = new Movie(movieName, priceCode);
                Rental rental = new Rental(movie, daysRented);

                Customer customer = new Customer("TestCustomer");
                customer.addRental(rental);
                Approvals.verify(customer.statement());
                superCustomer.addRental(rental);
            } else {
                Approvals.verify(superCustomer.statement());
            }
        }
    }

    @ParameterizedTest
    @CsvSource({
            // Test label, priceCode, daysRented
            "NEW_RELEASE_1, 1, 1",
            "NEW_RELEASE_2, 1, 2",
            "NEW_RELEASE_3, 1, 3",
            "NEW_RELEASE_4, 1, 4",
            "NEW_RELEASE_5, 1, 5",
            "REGULAR_1, 0, 1",
            "REGULAR_2, 0, 2",
            "REGULAR_3, 0, 3",
            "REGULAR_4, 0, 4",
            "REGULAR_5, 0, 5",
            "CHILDREN_1, 2, 1",
            "CHILDREN_2, 2, 2",
            "CHILDREN_3, 2, 3",
            "CHILDREN_4, 2, 4",
            "CHILDREN_5, 2, 5",
            "TOTAL, -1, -1"
    })
    public void customerHtmlApprovalTest(String movieName, int priceCode, int daysRented) {
        try (NamedEnvironment en = NamerFactory.withParameters(movieName))
        {
            if (!movieName.equals("TOTAL")) {
                Movie movie = new Movie(movieName, priceCode);
                Rental rental = new Rental(movie, daysRented);

                Customer customer = new Customer("TestCustomer");
                customer.addRental(rental);
                Approvals.verify(customer.htmlStatement());
                superHtmlCustomer.addRental(rental);
            } else {
                Approvals.verify(superHtmlCustomer.htmlStatement());
            }
        }
    }

    @ParameterizedTest
    @CsvSource({
            // daysRented, expectedAmount, expectedPoint
            "1, 5, 5",
            "2, 10, 10",
            "3, 17.5, 15",
            "4, 25, 20",
            "5, 32.5, 25"
    })
    public void bestsellerTest(int daysRented, double expectedAmount, int expectedPoint) {
        Movie movie = new Movie("TestMovie", 3);
        Rental rental = new Rental(movie, daysRented);

        Customer customer = new Customer("TestCustomer");
        customer.addRental(rental);
        assertEquals(expectedAmount, rental.getAmount());
        assertEquals(expectedPoint, rental.getFrequentRenterPoints());
    }
}
