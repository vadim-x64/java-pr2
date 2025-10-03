package com.project.services;

import com.project.models.Transaction;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public abstract class TransactionAnalyzer {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0.0;

        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }

        return balance;
    }

    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
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

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparingDouble(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public static Transaction[] findMinMaxExpensesInPeriod(List<Transaction> transactions, String startDate, String endDate) {
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

    public static Map<String, Double> calculateExpensesByCategory(List<Transaction> transactions) {
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

    public static Map<String, Double> calculateExpensesByMonth(List<Transaction> transactions) {
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