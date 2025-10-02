package com.project.services;

import com.project.models.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    // найбільша та найменша витрата за період
    public Transaction[] findMinMaxExpensesInPeriod(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, dateFormatter);
        LocalDate end = LocalDate.parse(endDate, dateFormatter);
        Transaction minExpense = null;
        Transaction maxExpense = null;

        for (Transaction transaction : transactions) {
            LocalDate transDate = LocalDate.parse(transaction.getDate(), dateFormatter);

            if ((transDate.isEqual(start) || transDate.isAfter(start)) && (transDate.isEqual(end) || transDate.isBefore(end)) && transaction.getAmount() < 0) {

                if (minExpense == null || transaction.getAmount() > minExpense.getAmount()) {
                    minExpense = transaction;
                }

                if (maxExpense == null || transaction.getAmount() < maxExpense.getAmount()) {
                    maxExpense = transaction;
                }
            }
        }

        return new Transaction[]{maxExpense, minExpense};
    }

    public Map<String, Double> calculateExpensesByCategory() {
        Map<String, Double> categoryExpenses = new HashMap<>();

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                String category = transaction.getDescription();
                double currentSum = categoryExpenses.getOrDefault(category, 0.0);
                categoryExpenses.put(category, currentSum + Math.abs(transaction.getAmount()));
            }
        }

        return categoryExpenses;
    }

    // рахує витрати по місяцях
    public Map<String, Double> calculateExpensesByMonth() {
        Map<String, Double> monthExpenses = new HashMap<>();

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
                String monthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
                double currentSum = monthExpenses.getOrDefault(monthYear, 0.0);
                monthExpenses.put(monthYear, currentSum + Math.abs(transaction.getAmount()));
            }
        }

        return monthExpenses;
    }
}