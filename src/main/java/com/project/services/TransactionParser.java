package com.project.services;

import com.project.models.Transaction;

import java.util.ArrayList;
import java.util.List;

// бере список рядків і перетворює їх у Transaction
public class TransactionParser {
    public List<Transaction> parsing(List<String> lines) {
        List<Transaction> transactions = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String[] values = lines.get(i).split(",");

            Transaction transaction = new Transaction(
                    values[0],
                    Double.parseDouble(values[1]),
                    values[2]
            );

            transactions.add(transaction);
        }

        return transactions;
    }
}