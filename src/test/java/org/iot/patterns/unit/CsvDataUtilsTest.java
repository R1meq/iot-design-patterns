package org.iot.patterns.unit;

import org.iot.patterns.data.ManagerDataGenerator;
import org.iot.patterns.entity.enums.Type;
import org.iot.patterns.utils.CsvDataUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.nio.file.Path;
import java.util.List;

import static org.iot.patterns.data.ManagerDataGenerator.NUMBER_OF_ROWS;
import static org.iot.patterns.common.TestConstants.TEST_FILE_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CsvDataUtilsTest {

    @TempDir
    private static Path tempDir;
    private static String csvFilePathString;

    @BeforeAll
    static void setUp() {
        csvFilePathString = tempDir.resolve(TEST_FILE_NAME).toString();
        ManagerDataGenerator.generateCSV(csvFilePathString, NUMBER_OF_ROWS);
    }

    @ParameterizedTest
    @EnumSource(Type.class)
    void should_ReadCorrectColumnCount_When_ReadRowDataByType(Type type) {
        List<List<String>> data = CsvDataUtils.readRowDataByType(type, csvFilePathString);

        assertFalse(data.isEmpty());
        assertEquals(type.getExpectedColumnCount(), data.get(0).size());
    }
}
