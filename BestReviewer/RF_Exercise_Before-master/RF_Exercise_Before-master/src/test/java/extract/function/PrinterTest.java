package extract.function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrinterTest {

    @Test
    public void testPrintOwning() {
        Printer printer = new Printer();
        Order buzz = new Order(1);
        Order cellphone = new Order(2);
        Order notebook = new Order(3);
        printer.orders.add(buzz);
        printer.orders.add(cellphone);
        printer.orders.add(notebook);

        String expected = "*****************************\n"
                + "****** Customer totals ******\n"
                + "*****************************\n"
                + "name: Digital City\n"
                + "amount: 6.0";
        assertEquals(expected, printer.printOwing("Digital City"));
    }

}