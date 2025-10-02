package com.project;

import java.util.List;

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
}