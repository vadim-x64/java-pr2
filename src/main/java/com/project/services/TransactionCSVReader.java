package com.project.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionCSVReader {

    public static List<String> readLines(String filePath) {
        List<String> lines = new ArrayList<>();

        try {
            URL url = new URL(filePath);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return lines;
    }
}