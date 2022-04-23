package com.sec.bestreviewer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class PrinterTest {

    private static final String CMD = "CMD";
    private static final List<String> TEST_RESULT = Arrays.asList("t", "e", "s", "t", "1", "2", "3");

    private Printer printer;

    @BeforeEach
    void setUp() {
        printer = new Printer();
    }

    @Test
    void printResult() {
        Assertions.assertEquals("CMD,7\n", printer.printResult(CMD, TEST_RESULT));
    }

    @Test
    void printResultDetail() {
        Assertions.assertEquals("CMD,t\nCMD,e\nCMD,s\nCMD,t\nCMD,1\n", printer.printResultDetail(CMD, TEST_RESULT));
    }

}