package com.samsung.bestreviewer.refactoring;

import com.samsung.bestreviewer.refactoring.model.Customer;
import com.samsung.bestreviewer.refactoring.model.Movie;
import com.samsung.bestreviewer.refactoring.model.Rental;
import com.samsung.bestreviewer.refactoring.statement.StatementUpdater;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class CustomerTest {

    @Test
    @DisplayName("Test using approvals.verify()")
    public void goldenMasterTest() {
        Customer testCustomer = new Customer("test_customer");
        makeRental(testCustomer);

        StatementUpdater statementUpdater = new StatementUpdater(testCustomer);
        statementUpdater.updateStatement();
        Approvals.verify(statementUpdater.getStatementPrinterOrigin().getPrintResult());
    }

    @Test
    @DisplayName("Test using approvals.verify() :HTML")
    public void goldenMasterTestHtml() {
        Customer testCustomer = new Customer("test_customer");
        makeRental(testCustomer);

        StatementUpdater statementUpdater = new StatementUpdater(testCustomer);
        statementUpdater.updateStatement();
        Approvals.verify(statementUpdater.getStatementPrinterHtml().getPrintResult());
    }

    private void makeRental(Customer customer) {
        List<List<Object>> goldenMasterTestParams = Arrays.asList(
                Arrays.asList("test_title", Movie.REGULAR, 1),
                Arrays.asList("test_title", Movie.REGULAR, 2),
                Arrays.asList("test_title", Movie.REGULAR, 3),
                Arrays.asList("test_title", Movie.REGULAR, 4),
                Arrays.asList("test_title", Movie.CHILDREN, 1),
                Arrays.asList("test_title", Movie.CHILDREN, 2),
                Arrays.asList("test_title", Movie.CHILDREN, 3),
                Arrays.asList("test_title", Movie.CHILDREN, 4),
                Arrays.asList("test_title", Movie.NEW_RELEASE, 1),
                Arrays.asList("test_title", Movie.NEW_RELEASE, 2),
                Arrays.asList("test_title", Movie.NEW_RELEASE, 3),
                Arrays.asList("test_title", Movie.NEW_RELEASE, 4),
                Arrays.asList("test_title", Movie.BEST_SELLER, 1),
                Arrays.asList("test_title", Movie.BEST_SELLER, 2),
                Arrays.asList("test_title", Movie.BEST_SELLER, 3),
                Arrays.asList("test_title", Movie.BEST_SELLER, 4)
        );

        Movie testMovie = null;
        Rental testRental = null;
        for (List<Object> testParamList: goldenMasterTestParams) {
            testMovie = new Movie((String)testParamList.get(0), (int)testParamList.get(1));
            testRental = new Rental(testMovie, (int)testParamList.get(2));
            customer.addRental(testRental);
        }
        return;
    }
}
