package com.project.services;

import com.project.models.Transaction;

import java.util.List;
import java.util.Map;

public class TransactionReportGenerator {
    private final TransactionAnalyzer analyzer;
    private final List<Transaction> transactions;

    public TransactionReportGenerator(TransactionAnalyzer analyzer, List<Transaction> transactions) {
        this.analyzer = analyzer;
        this.transactions = transactions;
    }

    public void printAllTransactions() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public void printTotalBalance() {
        double totalBalance = analyzer.calculateTotalBalance();
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public void printTransactionsByMonth(String monthYear) {
        int count = analyzer.countTransactionsByMonth(monthYear);
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");

        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    // звіт про мін./макс. витрати за період
    public void printMinMaxExpensesReport(String startDate, String endDate) {
        System.out.println("\nАналіз витрат за період з " + startDate + " по " + endDate);

        Transaction[] minMax = analyzer.findMinMaxExpensesInPeriod(startDate, endDate);
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

    // текстовий звіт з візуалізацією по категоріях
    public void printExpensesByCategoryReport(double scale) {
        System.out.println("\nВитрати по категоріях");
        System.out.println("(кожна * = " + scale + " грн)\n");

        Map<String, Double> categoryExpenses = analyzer.calculateExpensesByCategory();

        for (Map.Entry<String, Double> entry : categoryExpenses.entrySet()) {
            String category = entry.getKey();
            double amount = entry.getValue();
            int stars = (int) Math.round(amount / scale);
            String visualization = "";

            for (int i = 0; i < stars; i++) {
                visualization += "*";
            }

            System.out.printf("%-20s %8.2f грн | %s\n", category, amount, visualization);
        }
    }

    // текстовий звіт з візуалізацією по місяцях
    public void printExpensesByMonthReport(double scale) {
        System.out.println("\nВитрати по місяцях");
        System.out.println("(кожна * = " + scale + " грн)\n");

        Map<String, Double> monthExpenses = analyzer.calculateExpensesByMonth();

        for (Map.Entry<String, Double> entry : monthExpenses.entrySet()) {
            String month = entry.getKey();
            double amount = entry.getValue();
            int stars = (int) Math.round(amount / scale);
            String visualization = "";

            for (int i = 0; i < stars; i++) {
                visualization += "*";
            }

            System.out.printf("%s: %8.2f грн | %s\n", month, amount, visualization);
        }
    }
}