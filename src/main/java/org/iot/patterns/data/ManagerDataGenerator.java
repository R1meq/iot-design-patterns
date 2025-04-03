package org.iot.patterns.data;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.iot.patterns.common.CsvFileConstants.FILE_NAME;

@Log4j2
public class ManagerDataGenerator {
    public static final List<String> COMBINED_CSV_HEADERS;
    public static final int NUMBER_OF_ROWS = 1000;

    static {
        List<String> headers = new ArrayList<>();
        headers.addAll(UserData.HEADERS);
        headers.addAll(MobilePlanData.HEADERS);
        headers.addAll(OrderData.HEADERS);
        headers.addAll(PaymentData.HEADERS);
        headers.addAll(SubscriptionData.HEADERS);
        COMBINED_CSV_HEADERS = Collections.unmodifiableList(headers);
    }

    public static void main(String[] args) {
        generateCSV(FILE_NAME, NUMBER_OF_ROWS);
        log.info("CSV file generated successfully: {}", FILE_NAME);
    }

    public static void generateCSV(String filePath, int numberOfRows) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.join(",", COMBINED_CSV_HEADERS));
            writer.newLine();
            for (int i = 0; i < numberOfRows; i++) {
                List<String> rowData = new ArrayList<>();
                rowData.addAll(UserData.generate());
                rowData.addAll(MobilePlanData.generate());
                rowData.addAll(OrderData.generate());
                rowData.addAll(PaymentData.generate());
                rowData.addAll(SubscriptionData.generate());
                writer.write(String.join(",", rowData));
                if (i != numberOfRows - 1) {
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            log.error("Error occurred while generating CSV file", e);
        }
    }

}
