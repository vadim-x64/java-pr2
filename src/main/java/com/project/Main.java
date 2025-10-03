package com.project;

import com.project.models.Transaction;
import com.project.services.TransactionAnalyzer;
import com.project.services.TransactionCSVReader;
import com.project.services.TransactionParser;
import com.project.services.TransactionReportGenerator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<String> lines = TransactionCSVReader.readLines(filePath);
        TransactionParser parser = new TransactionParser();
        List<Transaction> transactions = parser.parsing(lines);

        TransactionReportGenerator.printAllTransactions(transactions);
        TransactionReportGenerator.printTotalBalance(transactions);
        TransactionReportGenerator.printTransactionsByMonth(transactions, "01-2024");

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        TransactionReportGenerator.printTopExpensesReport(topExpenses);
        TransactionReportGenerator.printMinMaxExpensesReport(transactions, "01-01-2024", "31-01-2024");
        TransactionReportGenerator.printExpensesByCategoryReport(transactions, 1000.0);
        TransactionReportGenerator.printExpensesByMonthReport(transactions, 5000.0);
    }
}