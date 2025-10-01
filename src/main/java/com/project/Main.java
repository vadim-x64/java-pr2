package com.project;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        TransactionCSVReader reader = new TransactionCSVReader();
        TransactionParser parser = new TransactionParser();

        List<String> lines = reader.readLines(filePath);
        List<Transaction> transactions = parser.parsing(lines);

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);
        double totalBalance = analyzer.calculateTotalBalance();

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }

        System.out.println("Загальний баланс: " + totalBalance);
    }
}