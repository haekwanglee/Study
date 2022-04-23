package com.sec.bestreviewer.util;

import java.util.Objects;

public class Pair<F, S> {
    public final F fieldName;
    public final S value;

    private Pair(F fieldName, S value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair<?, ?> p = (Pair<?, ?>) o;
        return Objects.equals(p.fieldName, fieldName) && Objects.equals(p.value, value);
    }

    @Override
    public int hashCode() {
        return (fieldName == null ? 0 : fieldName.hashCode()) ^ (value == null ? 0 : value.hashCode());
    }

    @Override
    public String toString() {
        return "Pair{" + String.valueOf(fieldName) + " " + String.valueOf(value) + "}";
    }

    public static <A, B> Pair <A, B> create(A a, B b) {
        return new Pair<A, B>(a, b);
    }
}