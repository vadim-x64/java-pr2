package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TransactionCSVReader {
    public List<Transaction> readTransactionCSV(String filePath) {
        List<Transaction> transactions = new ArrayList<>();

        try {
            URL url = new URL(filePath);

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    String[] values = line.split(",");
                    Transaction transaction = new Transaction(values[0], Double.parseDouble(values[1]), values[2]);
                    transactions.add(transaction);
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return transactions;
    }
}