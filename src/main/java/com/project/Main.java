package com.project;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        TransactionCSVReader transactionCSVReader = new TransactionCSVReader();
        List<Transaction> transactions = transactionCSVReader.readTransactionCSV(filePath);

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}