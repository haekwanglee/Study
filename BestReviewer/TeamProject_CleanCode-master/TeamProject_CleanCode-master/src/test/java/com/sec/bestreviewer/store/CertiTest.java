package com.sec.bestreviewer.store;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.sec.bestreviewer.store.Certi.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CertiTest {

    @Test
    void testOrdinal() {
        assertTrue(ADV.ordinal() < PRO.ordinal());
        assertTrue(PRO.ordinal() < EX.ordinal());
    }

    @Test
    void testSort() {
        List<Certi> certiList = Arrays.asList(PRO, EX, ADV, PRO, EX, ADV);
        Collections.sort(certiList);
        assertEquals(Arrays.asList(ADV, ADV, PRO, PRO, EX, EX), certiList);
    }
}
