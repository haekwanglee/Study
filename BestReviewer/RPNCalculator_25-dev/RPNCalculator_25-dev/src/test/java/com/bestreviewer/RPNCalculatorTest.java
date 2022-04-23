package com.bestreviewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RPNCalculatorTest {

    RPNCalculator rpnCalculator = new RPNCalculator();

    @BeforeEach
    public void setup() {
    }

    @Test
    @DisplayName("1 2 + => 1 + 2 = 3")
    public void test1() {
        int result = rpnCalculator.calculate("1 2 +");
        assertEquals(3,result);
    }

    @Test
    @DisplayName("1 2 – => 1 - 2 = -1")
    public void test2() {
        int result = rpnCalculator.calculate("1 2 -");
        assertEquals(-1,result);
    }

    @Test
    @DisplayName("2 3 * => 2 * 3 = 6")
    public void test3() {
        int result = rpnCalculator.calculate("2 3 *");
        assertEquals(6,result);
    }

    @Test
    @DisplayName("20 5 / => 20 / 5 = 4")
    public void test4() {
        int result = rpnCalculator.calculate("20 5 /");
        assertEquals(4,result);
    }

    @Test
    @DisplayName("20 0 / => Divided by Zero Exception")
    public void test5() {
        Exception exception = assertThrows(ArithmeticException.class, () ->{
            rpnCalculator.calculate("20 0 /");
        });
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    @DisplayName("4 2 + 3 * => (4 + 2) * 3 = 18")
    public void test6() {
        int result = rpnCalculator.calculate("4 2 + 3 *");
        assertEquals(18,result);
    }

    @Test
    @DisplayName("3 5 8 * 7 + * => ((5 * 8) + 7) * 3 = 141")
    public void test7() {
        int result = rpnCalculator.calculate("3 5 8 * 7 + *");
        assertEquals(141,result);
    }

    @Test
    @DisplayName("9 SQRT => √9 = 3 Math.sqrt(9) 사용")
    public void test8() {
        int result = rpnCalculator.calculate("9 SQRT");
        assertEquals(3,result);
    }

    @Test
    @DisplayName("4 5 MAX 1 2 MAX * => MAX(4, 5) * MAX(1, 2) = 10")
    public void test9() {
        int result = rpnCalculator.calculate("4 5 MAX 1 2 MAX *");
        assertEquals(10,result);
    }

    @Test
    @DisplayName("5 3 9 2 4 1 MAX => MAX(5, 3, 9, 2, 4, 1) = 9")
    public void test10() {
        int result = rpnCalculator.calculate("5 3 9 2 4 1 MAX");
        assertEquals(9,result);
    }
}