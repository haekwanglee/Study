package com.cleancode;

public class KnuthData {
    static final int ORD_MAX = 30;
    static final int CANDIDATE_INIT = 1;
    static final int ORD_INIT = 2;
    static final int SQAURE_INIT = 9;

    int ord = ORD_INIT;
    int square = SQAURE_INIT;
    int multiples[] = new int[ORD_MAX + 1];
    int candidate = CANDIDATE_INIT;
}

