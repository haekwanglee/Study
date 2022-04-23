package com.sec.bestreviewer.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PairTest {
    @Test
    public void testEquals() {
        Pair<String, String> pair = Pair.create("first", "second");
        assertTrue(Pair.create("first", "second").equals(pair));
    }

    @Test
    public void testEqualsNotInstance() {
        Pair<String, String> pair = null;
        assertFalse(Pair.create("first", "second").equals(pair));
    }

    @Test
    public void testHashCode() {
        Pair<String, String> pair = Pair.create("first", "second");
        assertNotEquals(pair.hashCode(), Pair.create("first", "second".hashCode()));
    }

    @Test
    public void testToString() {
        Pair<String, String> pair = Pair.create("first", "second");
        assertEquals(pair.toString(), "Pair{first second}");
    }
}