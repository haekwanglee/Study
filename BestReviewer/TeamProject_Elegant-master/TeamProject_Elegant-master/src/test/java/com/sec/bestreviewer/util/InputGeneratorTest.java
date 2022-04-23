package com.sec.bestreviewer.util;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InputGeneratorTest {

    @Disabled
    @ParameterizedTest
    @ValueSource(ints = {30, 50, 100, 1000, 10000, 100000})
    void testAddQueryGenerator(int size) {
        String fileName = String.format("./src/test/java/com/sec/bestreviewer/sample_input_%d.txt", size);
        InputGenerator addQueryGenerator = new InputGenerator();
        addQueryGenerator.generateInputFile(fileName, size);

        File file = new File(fileName);
        assertTrue(file.exists());
        if (file.delete()) {
            assertFalse(file.exists());
        }
    }

}