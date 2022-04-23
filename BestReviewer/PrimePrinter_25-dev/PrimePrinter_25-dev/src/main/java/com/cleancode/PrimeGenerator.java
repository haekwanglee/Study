package com.cleancode;

public class PrimeGenerator {
    private final int numberOfPrimes;
    private final int ordMax;

    private KnuthData knuth;

    private int primeIndex;
    private int[] primes;

    public PrimeGenerator(KnuthData knuth) {
        this.knuth = knuth;
        this.numberOfPrimes = PrintData.NUMBER_OF_PRIMES;
        this.ordMax = KnuthData.ORD_MAX;
    }

    private void init() {
        knuth.ord = KnuthData.ORD_INIT;
        knuth.square = KnuthData.SQAURE_INIT;
        knuth.multiples = new int[ordMax+1];
        knuth.candidate = KnuthData.CANDIDATE_INIT;
        primeIndex = PrintData.PRIME_INDEX_INIT;

        primes = new int[numberOfPrimes+1];
        primes[1] = 2;
    }

    public int[] get() {
        init();
        while (primeIndex < numberOfPrimes) {
            calculateCandidate();
            primeIndex++;
            primes[primeIndex] = knuth.candidate;
        }
        return primes;
    }

    private void calculateCandidate() {
        boolean possiblyPrime;
        do {
            knuth.candidate += 2;
            if (knuth.candidate == knuth.square) {
                knuth.ord++;
                knuth.square = primes[knuth.ord]* primes[knuth.ord];
                knuth.multiples[knuth.ord-1] = knuth.candidate;
            }
            possiblyPrime = isPossiblyPrime();
        } while (!possiblyPrime);
    }

    private boolean isPossiblyPrime() {
        boolean possiblyPrime = true;
        int i = 2;
        while (i < knuth.ord && possiblyPrime) {
            calculateMultiples(i);
            if (knuth.multiples[i] == knuth.candidate) {
                possiblyPrime = false;
            }
            i++;
        }
        return possiblyPrime;
    }

    private void calculateMultiples(int i) {
        while (knuth.multiples[i] < knuth.candidate) {
            knuth.multiples[i] += primes[i] + primes[i];
        }
    }
}
