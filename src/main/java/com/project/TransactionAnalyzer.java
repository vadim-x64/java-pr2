package com.project;

import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public class TransactionAnalyzer {
    private List<Transaction> transactions;

    public double calculateTotalBalance() {
        double balance = 0.0;

        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }

        return balance;
    }
}