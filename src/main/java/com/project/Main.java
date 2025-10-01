package com.project;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        TransactionReader reader = new TransactionReader();
        TransactionParser parser = new TransactionParser();

        List<String> lines = reader.readLines(filePath);
        List<Transaction> transactions = parser.parsing(lines);

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}