package org.iot.patterns.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.iot.patterns.entity.enums.Type;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.iot.patterns.common.CsvFileConstants.FILE_NAME;

@Log4j2
@UtilityClass
public class CsvDataUtils {
    public static List<List<String>> readRowDataByType(Type type) {
        return readRowDataByType(type, FILE_NAME);
    }

    public static List<List<String>> readRowDataByType(Type type, String filePath) {
        List<List<String>> generatedData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            int start = type.getStartIndex();
            int end = type.getEndIndex() + 1;
            for (CSVRecord csvRecord : parser) {
                List<String> rowData = IntStream.range(start, end)
                        .mapToObj(csvRecord::get)
                        .toList();
                generatedData.add(rowData);
            }
        } catch (IOException e) {
            log.error("Error occurred while reading CSV file", e);
        }
        return generatedData;
    }
}