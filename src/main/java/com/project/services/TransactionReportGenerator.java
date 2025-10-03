package com.project.services;

import com.project.models.Transaction;
import java.util.List;
import java.util.Map;

public abstract class TransactionReportGenerator {

    public static void printAllTransactions(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public static void printTotalBalance(List<Transaction> transactions) {
        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        int count = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");

        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void printMinMaxExpensesReport(List<Transaction> transactions, String startDate, String endDate) {
        System.out.println("\nАналіз витрат за період з " + startDate + " по " + endDate);

        Transaction[] minMax = TransactionAnalyzer.findMinMaxExpensesInPeriod(transactions, startDate, endDate);
        Transaction maxExpense = minMax[0];
        Transaction minExpense = minMax[1];

        if (maxExpense != null) {
            System.out.println("Найбільша витрата: " + maxExpense.getDescription() +
                    " - " + Math.abs(maxExpense.getAmount()) + " грн (" + maxExpense.getDate() + ")");
        } else {
            System.out.println("Найбільша витрата: не знайдено.");
        }

        if (minExpense != null) {
            System.out.println("Найменша витрата: " + minExpense.getDescription() +
                    " - " + Math.abs(minExpense.getAmount()) + " грн (" + minExpense.getDate() + ")");
        } else {
            System.out.println("Найменша витрата: не знайдено.");
        }
    }

    public static void printExpensesByCategoryReport(List<Transaction> transactions, double scale) {
        System.out.println("\nВитрати по категоріях");
        System.out.println("(кожна * = " + scale + " грн)\n");

        Map<String, Double> categoryExpenses = TransactionAnalyzer.calculateExpensesByCategory(transactions);

        for (Map.Entry<String, Double> entry : categoryExpenses.entrySet()) {
            String category = entry.getKey();
            double amount = entry.getValue();
            int stars = (int) Math.round(amount / scale);
            StringBuilder visualization = new StringBuilder();

            for (int i = 0; i < stars; i++) {
                visualization.append("*");
            }

            System.out.printf("%-20s %8.2f грн | %s\n", category, amount, visualization);
        }
    }

    public static void printExpensesByMonthReport(List<Transaction> transactions, double scale) {
        System.out.println("\nВитрати по місяцях");
        System.out.println("(кожна * = " + scale + " грн)\n");

        Map<String, Double> monthExpenses = TransactionAnalyzer.calculateExpensesByMonth(transactions);

        for (Map.Entry<String, Double> entry : monthExpenses.entrySet()) {
            String month = entry.getKey();
            double amount = entry.getValue();
            int stars = (int) Math.round(amount / scale);
            StringBuilder visualization = new StringBuilder();

            for (int i = 0; i < stars; i++) {
                visualization.append("*");
            }

            System.out.printf("%s: %8.2f грн | %s\n", month, amount, visualization);
        }
    }
}