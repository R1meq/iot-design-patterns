package org.iot.patterns.unit;

import org.iot.patterns.data.ManagerDataGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.iot.patterns.data.ManagerDataGenerator.COMBINED_CSV_HEADERS;
import static org.iot.patterns.data.ManagerDataGenerator.NUMBER_OF_ROWS;
import static org.iot.patterns.common.TestConstants.TEST_FILE_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class ManagerDataGeneratorTest {
    
    @TempDir
    private static Path tempDir;
    private static Path csvFilePath;

    @BeforeAll
    static void setUp() {
        csvFilePath = tempDir.resolve(TEST_FILE_NAME);
        ManagerDataGenerator.generateCSV(csvFilePath.toString(), NUMBER_OF_ROWS);
    }

    @Test
    void should_CreateFileWithCorrectNumberOfRows_When_GenerateCSVIsCalled() throws IOException {
        assertTrue(Files.exists(csvFilePath));
        assertEquals(NUMBER_OF_ROWS + 1, Files.readAllLines(csvFilePath).size());
    }

    @Test
    void should_ContainCorrectHeaders_When_GenerateCSVIsCalled() throws IOException {
        String headerLine = Files.readAllLines(csvFilePath).get(0);
        assertEquals(String.join(",", COMBINED_CSV_HEADERS), headerLine);
    }

    @Test
    void should_HaveSameNumberOfFieldsAsHeaders_When_CSVIsGenerated() throws IOException {
        String[] dataFields = Files.readAllLines(csvFilePath).get(1).split(",");
        assertEquals(COMBINED_CSV_HEADERS.size(), dataFields.length);
    }

}
