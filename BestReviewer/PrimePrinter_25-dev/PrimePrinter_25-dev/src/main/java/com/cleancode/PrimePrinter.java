package com.cleancode;

public class PrimePrinter {
    public static void main(String[] args) {
        int[] primes = new PrimeGenerator(new KnuthData()).get();

        PrimePrintPage printer = new PrimePrintPage();
        printer.print(primes);
    }
}

