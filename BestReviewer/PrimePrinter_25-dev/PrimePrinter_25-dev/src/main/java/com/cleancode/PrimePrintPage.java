package com.cleancode;

public class PrimePrintPage {
    private final int linesPerPage;
    private final int columns;
    private final int numberOfPrimes;

    private int pageNumber;
    private int pageOffset;

    public PrimePrintPage() {
        this.linesPerPage = PrintData.LINES_PER_PAGE;
        this.columns = PrintData.COLUMN_TOTAL;
        this.numberOfPrimes = PrintData.NUMBER_OF_PRIMES;
    }

    public void print(int[] numberGroup) {
        printPageInit();
        while (pageOffset <= numberOfPrimes) {
            printPageHeader(pageNumber);
            printContent(pageOffset, numberGroup);
            printFooter();
            printPageNext();
        }
    }

    private void printPageInit() {
        pageNumber = PrintData.PAGE_NUMBER_INIT;
        pageOffset = PrintData.PAGE_OFFSET_INIT;
    }

    private void printPageNext() {
        pageNumber++;
        pageOffset += linesPerPage * columns;
    }

    private void printPageHeader(int pageNumber) {
        System.out.print("The First ");
        System.out.print(Integer.toString(numberOfPrimes));
        System.out.print(" Prime Numbers === Page ");
        System.out.print(Integer.toString(pageNumber));
        System.out.println("\n");
    }

    private void printContent(int pageOffset, int[] numberGroup) {
        int rowOffset;
        for (rowOffset = pageOffset; rowOffset <= (pageOffset + linesPerPage - 1); rowOffset++) {
            printColumn(numberGroup, rowOffset);
            System.out.println();
        }
    }

    private void printFooter() {
        System.out.println("\f");
    }

    private void printColumn(int[] numberGroup, int rowOffset) {
        for (int column = 0; column <= columns - 1; column++) {
            if (rowOffset + column * linesPerPage <= numberOfPrimes) {
                System.out.printf("%10d", numberGroup[rowOffset + column * linesPerPage]);
            }
        }
    }
}
