import org.approvaltests.Approvals;
import org.approvaltests.namer.NamedEnvironment;
import org.approvaltests.namer.NamerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StatementPrinterTest {

    Play hamlet = new Play("Hamlet", "tragedy");
    Play asYouLikeIt = new Play("As You Like It", "comedy");
    Play othello = new Play("Othello", "tragedy");
    Play henry5 = new Play("Henry V", "history");

    Map<String, Play> plays = new HashMap<String, Play>() {{
            put("hamlet",  hamlet);
            put("as-like", asYouLikeIt);
            put("othello", othello);
            put("henry-v",  henry5);
    }};


    @Test
    void testStatement() {
        Invoice invoice = new Invoice("BigCo", Arrays.asList(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        String result = statementPrinter.print(invoice, plays);

        Approvals.verify(result);
    }
    

    @Test
    void testWrongPlayType() {
        Invoice invoice = new Invoice("BigCo", Arrays.asList(
                new Performance("henry-v", 53)));

        StatementPrinter statementPrinter = new StatementPrinter();
        Assertions.assertThrows(Error.class, () ->
            statementPrinter.print(invoice, plays)
        );
    }

    @Disabled
    @Test
    void testNewPlayType(){
        Invoice invoice = new Invoice("BigCo", Arrays.asList(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        String result = statementPrinter.print(invoice, plays);

        Approvals.verify(result);
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {"tragedy","comedy"})
    void testChangePlayType(String playTYpe){
        asYouLikeIt.type = playTYpe ;
        Invoice invoice = new Invoice("BigCo", Arrays.asList(
                new Performance("as-like", 35)));
        try (NamedEnvironment en = NamerFactory.withParameters(playTYpe))
        {
            Approvals.verify(new StatementPrinter().print(invoice, plays));
        }
    }
}
