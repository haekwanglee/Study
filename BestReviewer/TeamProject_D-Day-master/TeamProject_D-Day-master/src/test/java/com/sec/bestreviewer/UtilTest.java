package com.sec.bestreviewer;

import com.sec.bestreviewer.store.Employee;
import com.sec.bestreviewer.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class UtilTest {

    @Test
    void PairTest() {
        Pair sourcePair = Pair.create("name", "KYUMOK KIM");
        Pair samePair = Pair.create("name", "KYUMOK KIM");
        Pair differentPair = Pair.create("careerLevel", "CL2");
        Employee employee = new Employee("18050301", "KYUMOK KIM", "CL2", "010-9777-6055", "19980906", "EX");

        assertFalse(sourcePair.equals(employee));
        assertTrue(sourcePair.equals(sourcePair));
        assertTrue(sourcePair.equals(samePair));
        assertFalse(sourcePair.equals(differentPair));
    }
}


