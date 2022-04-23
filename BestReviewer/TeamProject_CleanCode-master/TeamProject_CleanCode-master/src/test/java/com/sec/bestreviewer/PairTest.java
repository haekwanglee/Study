package com.sec.bestreviewer;

import com.sec.bestreviewer.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PairTest {

    private final Object FIRST = "First";
    private final Object SECOND = "Second";
    private final Object SECOND_DASH = "Second-";

    @Test
    void testEquals() {
        Pair pair1 = Pair.create(FIRST, SECOND);
        Pair pair2 = Pair.create(FIRST, SECOND_DASH);
        assertEquals(pair1, pair1);
        assertNotEquals(pair1, pair2);
    }

    @Test
    void testHashCode() {
        Pair pair = Pair.create(FIRST, SECOND);
        assertEquals(FIRST.hashCode() ^ SECOND.hashCode(), pair.hashCode());
        assertNotEquals(FIRST.hashCode() ^ SECOND_DASH.hashCode(), pair.hashCode());
    }

    @Test
    void testToString() {
        Pair pair = Pair.create(FIRST, SECOND);
        assertEquals("Pair{First Second}", pair.toString());
    }
}
