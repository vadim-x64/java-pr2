package com.project.reader;

import com.project.services.TransactionCSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

class TransactionCSVReaderTest {

    @Test
    public void testReadLines() {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        TransactionCSVReader reader = new TransactionCSVReader();
        List<String> lines = reader.readLines(filePath);

        Assertions.assertFalse(lines.isEmpty(), "Файл CSV не повинен бути порожнім.");
        Assertions.assertTrue(lines.size() > 1, "CSV повинен містити заголовок та дані.");
        String header = lines.get(0);
        Assertions.assertTrue(header.contains(","), "Заголовок повинен містити коми (формат CSV).");

        if (lines.size() > 1) {
            String firstDataLine = lines.get(1);
            String[] parts = firstDataLine.split(",");
            Assertions.assertTrue(parts.length >= 3, "Рядок даних повинен містити мінімум 3 поля (дата, сума, опис).");
        }
    }
}