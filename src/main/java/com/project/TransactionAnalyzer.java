package com.project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionAnalyzer {
    private final List<Transaction> transactions;
    private DateTimeFormatter dateFormatter;

    public TransactionAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
        this.dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public double calculateTotalBalance() {
        double balance = 0.0;

        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }

        return balance;
    }

    public int countTransactionsByMonth(String monthYear) {
        int count = 0;

        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));

            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }

        return count;
    }

    public List<Transaction> findTopExpenses() {
        return transactions.stream()
                // фільтрує по умові
                .filter(t -> t.getAmount() < 0)
                // сортує за сумою
                .sorted(Comparator.comparingDouble(Transaction::getAmount))
                // тільки 10 елементів
                .limit(10)
                // перетворює потік назад у список
                .collect(Collectors.toList());
    }
}